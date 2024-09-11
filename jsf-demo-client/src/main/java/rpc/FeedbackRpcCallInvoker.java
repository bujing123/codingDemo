package rpc;

import com.alibaba.fastjson.JSON;
import com.jd.ump.profiler.CallerInfo;
import com.jd.ump.profiler.proxy.Profiler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 描述：支持可降级处理或返回NULL的RPC调用器
 * @param <T> RPC层返回的结果
 * @param <R> 最RPC进行转换后的结果
 *
 * @author hubo
 */
@Slf4j
public class FeedbackRpcCallInvoker<T, R> implements RpcCallInvoker<T, R>{

    private final FeedbackRpcCall<T, R> rpcCall;

    private List<RpcCallListener<T, R>> listeners;

    public FeedbackRpcCallInvoker(FeedbackRpcCall<T, R> rpcCall) {
        this.rpcCall = rpcCall;
        listeners = new ArrayList<>();
    }

    public R invoke() {

        T response;

        CallerInfo info = Profiler.registerInfo(rpcCall.methodName(), false, true);

        try {

            response = rpcCall.call();
            if (log.isInfoEnabled()) {
                log.info("invoke {} method response {}", rpcCall.methodName(), JSON.toJSONString(response));
            }

            if (listeners != null) {
                listeners.forEach(listener -> listener.onSuccess(response));
            }

        } catch (Exception e) {

            Profiler.functionError(info);

            log.error(e.getMessage(), e);

            log.error("rpc invoke " + rpcCall.methodName() + "error! will be invoke feedback method!");

            if (listeners != null) {
                listeners.forEach(listener -> listener.onError(e));
            }

            R result = rpcCall.feedback();

            if (listeners != null) {
                listeners.forEach(listener -> listener.onFeedback(result));
            }

            return result; //直接返回降级方法

        } finally {
            Profiler.registerInfoEnd(info);
        }

        return Optional.ofNullable(response)
                .filter(this::filter)
                .map(this::map)
                .orElseGet(this::feedback);
    }

    @Override
    public RpcCall<T, R> getRpcCall() {
        return rpcCall;
    }

    @Override
    public List<RpcCallListener<T, R>> getListeners() {
        return listeners;
    }

    private R feedback() {
        R r = rpcCall.feedback();
        if (listeners != null) {
            listeners.forEach(listener -> listener.onFeedback(r));
        }
        return r;
    }

    @Override
    public void addListener(RpcCallListener<T, R> listener) {
        if(listener != null) {
            listeners.add(listener);
        }
    }
}

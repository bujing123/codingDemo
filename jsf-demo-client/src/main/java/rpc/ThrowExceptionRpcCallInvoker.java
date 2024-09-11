package rpc;


import com.alibaba.fastjson.JSON;
import com.jd.mrd.b.common.exception.BusinessException;
import com.jd.mrd.b.common.exception.RpcInvocationException;
import com.jd.ump.profiler.CallerInfo;
import com.jd.ump.profiler.proxy.Profiler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 描述：支持抛出异常的RPC调用器
 * @param <T> RPC层返回的结果
 * @param <R> 最RPC进行转换后的结果
 *
 * @author hubo
 */
@Slf4j
public class ThrowExceptionRpcCallInvoker<T, R> implements RpcCallInvoker<T, R>{


    private final ThrowExceptionRpcCall<T, R> rpcCall;

    private List<RpcCallListener<T, R>> listeners;

    public ThrowExceptionRpcCallInvoker(ThrowExceptionRpcCall<T, R> rpcCall) {
        this.rpcCall = rpcCall;
        listeners = new ArrayList<>();
    }

    public R invoke() throws BusinessException {
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

            log.error("rpc invoke " + rpcCall.methodName() + "error!", e);

            if (listeners != null) {
                listeners.forEach(listener -> listener.onError(e));
            }

            throw new RpcInvocationException(rpcCall.methodName(), e.getMessage(), e);

        } finally {
            Profiler.registerInfoEnd(info);
        }

        return Optional.ofNullable(response)
                .filter(this::filter)
                .map(this::map)
                .orElseThrow(() -> {
                    BusinessException be = rpcCall.throwException(response);
                    if(listeners != null) {
                        listeners.forEach(listener -> listener.onBusinessException(be));
                    }
                    return be;
                });
    }

    @Override
    public RpcCall<T, R> getRpcCall() {
        return rpcCall;
    }

    @Override
    public List<RpcCallListener<T, R>> getListeners() {
        return listeners;
    }

    @Override
    public void addListener(RpcCallListener<T, R> listener) {
        if(listener != null) {
            listeners.add(listener);
        }
    }

}

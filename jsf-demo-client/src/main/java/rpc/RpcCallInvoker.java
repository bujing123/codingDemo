package rpc;


import com.jd.mrd.b.common.exception.BusinessException;

import java.util.List;

/**
 * 描述：Rpc Call 通用调用类，主要封装了RPC调用监控、转换、过滤、降级或抛出异常流程的封装。
 *
 * @author hubo
 */
public interface RpcCallInvoker<T, R> {


    /**
     * 方法调用，需要调用{@link RpcCall}中的相关方法完成整个RPC调用，最终得到需要的返回值
     * @return 返回值
     * @throws BusinessException 业务异常
     */
    R invoke() throws BusinessException;

    /**
     * 返回{@link RpcCall}
     * @return rpcCall
     */
    RpcCall<T, R> getRpcCall();

    /**
     * 获取所有{@link RpcCallListener}
     * @return listeners
     */
    List<RpcCallListener<T, R>> getListeners();

    /**
     * 添加{@link RpcCallListener}到listener集合中
     * @param listener 监听器
     */
    void addListener(RpcCallListener<T, R> listener);


    /**
     * 对RPC层调用的结果进行过滤
     * @param t 结果
     * @return 过滤后的结果
     */
    default boolean filter(T t) {
        boolean result = getRpcCall().filter(t);
        if (getListeners() != null) {
            getListeners().forEach(listener -> listener.onFilter(t));
        }
        return result;
    }

    /**
     * 对RPC层调用的结果进行转换，T->R 和{@link java.util.function.Function} 方法很像
     * @param t 转换前的结果
     * @return 转换后的结果
     */
    default R map(T t) {
        R r = getRpcCall().convert(t);
        if (getListeners() != null) {
            getListeners().forEach(listener -> listener.onConvert(t, r));
        }
        return r;
    }

}

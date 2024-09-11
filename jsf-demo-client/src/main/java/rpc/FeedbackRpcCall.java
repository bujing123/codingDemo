package rpc;

/**
 * 描述：支持可降级的RPC调用
 *
 * @author hubo
 */
public interface FeedbackRpcCall<T, R> extends RpcCall<T, R>{

    /**
     * 降级方法
     * @return 降级返回值
     */
    R feedback();
}

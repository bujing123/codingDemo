package rpc;


import com.jd.mrd.b.common.exception.BusinessException;

/**
 * 描述：RPC调用失败会抛出业务异常
 *
 * @author hubo
 */
public interface ThrowExceptionRpcCall<T, R> extends RpcCall<T, R> {

    /**
     * 抛出业务异常，rpc调用失败（非JSF 框架、网络等）
     * @return 业务异常
     */
    BusinessException throwException(T response);

}

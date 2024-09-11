package rpc;

/**
 * 描述：RPC调用框架
 *
 * @author hubo
 */
public interface RpcCall<T, R> {

    /**
     * RPC 方法调用
     * @return 返回RPC方法返回值
     */
    T call() throws Exception;

    /**
     * 调用RPC的方法名
     * @return 方法名
     */
    String methodName();

    /**
     * 返回值过滤，用于过滤状态及数据是否为NULL
     * @param response 返回值
     * @return 是否被过滤
     */
    boolean filter(T response);

    /**
     * 返回值转换
     * @param response 返回值
     * @return 转换后的返回值
     */
    R convert(T response);

}

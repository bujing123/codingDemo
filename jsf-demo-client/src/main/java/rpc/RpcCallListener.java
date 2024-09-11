package rpc;



public interface RpcCallListener<T, R> {

    /**
     * Rpc调用成功后会被调用，注意：T t有可能为空
     * @param t
     */
    void onSuccess(T t);

    void onError(Throwable e);

    void onFilter(T t);

    void onConvert(T t, R r);

    void onFeedback(R r);

    void onBusinessException(Exception be);

}

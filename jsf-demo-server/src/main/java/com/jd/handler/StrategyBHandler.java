package com.jd.handler;

public class StrategyBHandler extends AbstractHandler{

    /**
     * 业务逻辑B
     * @param key
     * @return
     */
    @Override
    public String logicB(String key) {
        //业务逻辑B
        return "进入业务逻辑B";
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        StrategyFactory.register("strategyB",this);
    }
}

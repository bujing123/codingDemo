package com.jd.handler;

import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractHandler implements InitializingBean {

    public void logicA(String key){
        throw new UnsupportedOperationException();
    }

    public String logicB(String key){
        throw new UnsupportedOperationException();
    }
}

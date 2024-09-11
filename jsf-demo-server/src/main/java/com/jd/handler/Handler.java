package com.jd.handler;

import org.springframework.beans.factory.InitializingBean;

public interface Handler extends InitializingBean {

    public void logic(String key);
}

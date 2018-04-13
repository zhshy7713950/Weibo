package net.zsy.weibo.data.async;

import net.zsy.weibo.bean.WbErrorBean;

public class WbException extends RuntimeException {

    private final int code;
    private final String message;
    private final String request;
    private final WbErrorBean wbErrorBean;

    public WbException(WbErrorBean errorBean){
        super(errorBean.getError());
        code = errorBean.getError_code();
        message = errorBean.getError();
        request = errorBean.getRequest();
        wbErrorBean = errorBean;
    }

    public int getCode(){
        return code;
    }

    public String getRequest(){
        return request;
    }

    public WbErrorBean getWbErrorBean() {
        return wbErrorBean;
    }
}

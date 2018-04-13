package net.zsy.weibo.bean;

public class WbErrorBean {


    /**
     * error : applications over the unaudited use restrictions!
     * error_code : 21321
     * request : /2/users/show.json
     */

    private String error;
    private int error_code;
    private String request;

    public WbErrorBean(String error, int error_code, String request) {
        this.error = error;
        this.error_code = error_code;
        this.request = request;
    }

    public WbErrorBean() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}

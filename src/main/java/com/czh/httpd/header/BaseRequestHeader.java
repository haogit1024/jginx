package com.czh.httpd.header;

/**
 * @author czh
 * http请求头类
 */
public abstract class BaseRequestHeader extends BaseHttpHeader{
    protected String method;
    protected String url;

    public BaseRequestHeader() {
    }

    public BaseRequestHeader(String headerString) {
        super(headerString);
    }

    /**
     * 获取http请求头
     * @return GET / POST / null
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置http请求方式
     * @param method GET
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return 请求的资源
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置请求的资源
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}

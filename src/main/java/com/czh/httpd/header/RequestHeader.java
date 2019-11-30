package com.czh.httpd.header;

/**
 * @author czh
 * TODO 改为模板模式, 所有请求头和相应头都继承这个类
 */
public interface RequestHeader {

    /**
     * 设置http请求方式
     * @param method GET
     */
    void settMethod(String method);

    /**
     * 获取http请求头
     * @return GET / POST / null
     */
    String getMethod();

    /**
     * 设置请求的资源
     * @param url
     */
    void setUrl(String url);

    /**
     * @return 请求的资源
     */
    String getUrl();

    /**
     * 设置http版本
     * @param httpVersion HTTP/1.1
     */
    void setHttpVersion(String httpVersion);

    /**
     * @return http版本号
     */
    String getHttpVersion();

    /**
     * 设置请求头
     * @param key
     * @param value
     */
    void setHeader(String key, String value);

    /**
     * 根据key获取头部的一个值
     * @param key
     * @return
     */
    String getHeader(String key);

    /**
     * 设置玩参数后返回一个请求头
     * @return
     */
    String build();
}

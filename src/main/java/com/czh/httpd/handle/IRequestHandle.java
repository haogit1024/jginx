package com.czh.httpd.handle;

/**
 * @author czh
 * 处理请求类
 */
public interface IRequestHandle {
    /**
     * 设置请求(请求体和请求头)
     * @param request
     * @throws IllegalAccessError 请求头格式错误
     */
    void setRequest(String request) throws IllegalAccessError;

    /**
     * 根据请求返回相应的响应
     * @return
     */
    String getResponse();
}

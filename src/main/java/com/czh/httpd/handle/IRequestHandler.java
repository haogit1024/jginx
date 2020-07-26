package com.czh.httpd.handle;

import com.czh.httpd.header.BaseRequestHeader;
import com.czh.httpd.response.Response;

/**
 * // TODO 改成抽象类
 * @author czh
 * 处理请求类
 */
public interface IRequestHandler {

    /**
     * TODO 改为由外部解析request
     * 设置请求(请求体和请求头)
     * @param request
     * @throws IllegalAccessError 请求头格式错误
     */
    void setRequest(String request) throws IllegalAccessError;

    /**
     * 设置请求头和请求体
     * @param requestHeader
     * @param requestContent
     */
    void setRequest(BaseRequestHeader requestHeader, String requestContent);

    /**
     * 根据请求返回相应的响应
     * @return
     */
    Response getResponse();
}

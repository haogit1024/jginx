package com.czh.httpd.handle;

import com.czh.httpd.entity.Server;
import com.czh.httpd.header.BaseRequestHeader;
import com.czh.httpd.response.Response;

/**
 * @author czh
 * 处理请求类
 */
public interface IRequestHandler {
    void setServer(Server server);

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

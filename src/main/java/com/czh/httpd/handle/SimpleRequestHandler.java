package com.czh.httpd.handle;

import com.czh.httpd.entity.Server;
import com.czh.httpd.header.BaseRequestHeader;
import com.czh.httpd.response.Response;
import com.czh.httpd.response.ResponseFactory;

import java.io.IOException;

/**
 * @author czh
 * 请求处理类
 */
public class SimpleRequestHandler implements IRequestHandler {
    private BaseRequestHeader requestHeader;
    private String requestContent;
    private Server server;

    @Override
    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public void setRequest(BaseRequestHeader requestHeader, String requestContent) {
        this.requestHeader = requestHeader;
        this.requestContent = requestContent;
    }

    @Override
    public Response getResponse() {
        try {
            return ResponseFactory.getResponseByUrl(requestHeader.getUrl(), requestHeader.getCookie(), server, requestHeader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取资源文件权限错误" + e.getMessage());
        }
    }
}

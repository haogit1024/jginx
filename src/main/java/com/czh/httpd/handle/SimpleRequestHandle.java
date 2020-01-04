package com.czh.httpd.handle;

import com.czh.httpd.App;
import com.czh.httpd.header.BaseRequestHeader;
import com.czh.httpd.header.SimpleRequestHeader;
import com.czh.httpd.response.Response;
import com.czh.httpd.response.ResponseFactory;

import java.io.IOException;

/**
 * @author czh
 * 请求处理类
 */
public class SimpleRequestHandle implements IRequestHandle {
    private String request;
    private BaseRequestHeader requestHeader;
    private String requestContent;

    @Override
    public void setRequest(String request) throws IllegalAccessError {
        this.request = request;
    }

    @Override
    public Response getResponse() {
        String[] reqArray = request.split(App.CRLF + App.CRLF);
        if (reqArray.length == 1) {
            requestHeader = new SimpleRequestHeader(reqArray[0]);
        } else if (reqArray.length == 2) {
            requestHeader = new SimpleRequestHeader(reqArray[0]);
            requestContent = reqArray[1];
        } else {
            String errMsg = "request格式错误: " + request;
            return ResponseFactory.getErrorResponse(errMsg, requestHeader.getCookie());
        }
//        System.out.println("requestHeader:");
//        System.out.println(requestHeader);
        try {
            return ResponseFactory.getResponseByUrl(requestHeader.getUrl(), requestHeader.getCookie(), requestHeader);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("读取资源文件权限错误" + e.getMessage());
        }
    }
}

package com.czh.httpd.handle;

import com.czh.httpd.App;
import com.czh.httpd.header.BaseRequestHeader;
import com.czh.httpd.header.SimpleRequestHeader;
import com.czh.httpd.response.Response;
import com.czh.httpd.response.ResponseFactory;

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
        System.out.println("request:");
        System.out.println(request);
        this.request = request;
    }

    @Override
    public String getResponse() {
        String[] reqArray = request.split(App.CRLF + App.CRLF);
        if (reqArray.length == 1) {
            requestHeader = new SimpleRequestHeader(reqArray[0]);
        } else if (reqArray.length == 2) {
            requestHeader = new SimpleRequestHeader(reqArray[0]);
            requestContent = reqArray[1];
        } else {
            String errMsg = "request格式错误: " + request;
            Response errorResponse = ResponseFactory.getErrorResponse(errMsg, requestHeader.getCookie());
            return errorResponse.build();
        }
        System.out.println("requestHeader:");
        System.out.println(requestHeader);
        Response response = ResponseFactory.getResponseByUrl(requestHeader.getUrl(), requestHeader.getCookie());
        return response.build();
    }
}

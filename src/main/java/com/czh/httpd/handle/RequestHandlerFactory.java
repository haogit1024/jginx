package com.czh.httpd.handle;

import com.czh.httpd.constant.CommonConstants;
import com.czh.httpd.entity.Server;
import com.czh.httpd.header.BaseRequestHeader;
import com.czh.httpd.header.SimpleRequestHeader;

/**
 * @author czh
 * @date 2020/07/25
 */
public class RequestHandlerFactory {
    public static IRequestHandler getNewHandler(String request, Server server) {
        BaseRequestHeader requestHeader;
        String requestContent;
        String[] reqArray = request.split(CommonConstants.Symbol.CRLF + CommonConstants.Symbol.CRLF);
        IRequestHandler requestHandler;
        if (reqArray.length == 1) {
            // 没有请求体
            requestHeader = new SimpleRequestHeader(reqArray[0]);
            requestHandler = new SimpleRequestHandler();
            requestHandler.setRequest(requestHeader, "");
            requestHandler.setServer(server);
        } else if (reqArray.length == 2) {
            // 有请求体
            requestHeader = new SimpleRequestHeader(reqArray[0]);
            requestContent = reqArray[1];
            requestHandler = new SimpleRequestHandler();
            requestHandler.setRequest(requestHeader, requestContent);
            requestHandler.setServer(server);
        } else {
            String errMsg = "request格式错误: " + request;
            requestHandler = new ErrorRequestHandler(errMsg);
        }
        return requestHandler;
    }

    public static IRequestHandler getErrorHandler(String request, String errMsg) {
        return new ErrorRequestHandler(errMsg);
    }
}

package com.czh.httpd.handle;

import com.czh.httpd.App;
import com.czh.httpd.header.BaseRequestHeader;
import com.czh.httpd.header.SimpleRequestHeader;

/**
 * @author czh
 * @date 2020/07/25
 */
public class RequestHandlerFactory {
    public static IRequestHandler getNewHandler(String request) {
        BaseRequestHeader requestHeader;
        String requestContent;
        String[] reqArray = request.split(App.CRLF + App.CRLF);
        IRequestHandler requestHandler;
        if (reqArray.length == 1) {
            requestHeader = new SimpleRequestHeader(reqArray[0]);
            requestHandler = new SimpleRequestHandler();
            requestHandler.setRequest(requestHeader, "");
        } else if (reqArray.length == 2) {
            requestHeader = new SimpleRequestHeader(reqArray[0]);
            requestContent = reqArray[1];
            requestHandler = new SimpleRequestHandler();
            requestHandler.setRequest(requestHeader, requestContent);
        } else {
            String errMsg = "request格式错误: " + request;
//            return ResponseFactory.getErrorResponse(errMsg, requestHeader.getCookie());
            requestHandler = new ErrorRequestHandler(errMsg);
        }
        return requestHandler;
    }
}

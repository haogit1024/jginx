package com.czh.httpd.response;

import com.czh.httpd.App;
import com.czh.httpd.header.BaseResponseHeader;

/**
 * http相应类
 */
public class Response {
    protected BaseResponseHeader responseHeader;
    protected String responseContent;

    public Response() {
    }

    /**
     *
     * @param responseHeader   响应头
     * @param responseContent  响应体
     */
    public Response(BaseResponseHeader responseHeader, String responseContent) {
        this.responseHeader = responseHeader;
        this.responseContent = responseContent;
    }

    public BaseResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(BaseResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public String build() {
        return this.responseHeader + App.CRLF + responseContent;
    }

    @Override
    public String toString() {
        return this.build();
    }
}

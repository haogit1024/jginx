package com.czh.httpd.response;

import com.czh.httpd.App;
import com.czh.httpd.constant.CommonConstants;
import com.czh.httpd.header.BaseResponseHeader;

/**
 * http相应类
 */
public class Response {
    protected BaseResponseHeader responseHeader;
    protected byte[] responseContent;

    public Response() {
    }

    /**
     *
     * @param responseHeader   响应头
     * @param responseContent  响应体
     */
    public Response(BaseResponseHeader responseHeader, byte[] responseContent) {
        this.responseHeader = responseHeader;
        this.responseContent = responseContent;
    }

    public BaseResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(BaseResponseHeader responseHeader) {
        this.responseHeader = responseHeader;
    }

    public byte[] getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(byte[] responseContent) {
        this.responseContent = responseContent;
    }

    public String build() {
        return this.responseHeader + CommonConstants.Symbol.CRLF + responseContent;
    }

    @Override
    public String toString() {
        return this.build();
    }
}

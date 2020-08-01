package com.czh.httpd.header;

import com.czh.httpd.enums.HttpStatus;

/**
 * @author czh
 * http相应头类
 */
public abstract class BaseResponseHeader extends BaseHttpHeader {
//    protected String httpStatus;
//    protected String statusName;

    protected HttpStatus httpStatus;

    public BaseResponseHeader() {
    }

    public BaseResponseHeader(String headerString) {
        super(headerString);
    }

    /**
     * 获取http状态码
     * @return
     */
//    public String getHttpStatus() {
//        return httpStatus;
//    }

    /**
     * 设置http状态码
     * @param httpStatus
     */
//    public void setHttpStatus(String httpStatus) {
//        this.httpStatus = httpStatus;
//    }

    /**
     * 获取http状态描述
     * @return
     */
//    public String getStatusName() {
//        return statusName;
//    }

    /**
     * 设置http状态描述
     * @param statusName
     */
//    public void setStatusName(String statusName) {
//        this.statusName = statusName;
//    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}

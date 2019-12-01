package com.czh.httpd.header;

/**
 * @author czh
 * http相应头类
 */
public abstract class BaseResponseHeader extends BaseHttpHeader {
    protected String httpStatus;
    protected String statusName;

    public BaseResponseHeader() {
    }

    public BaseResponseHeader(String headerString) {
        super(headerString);
    }

    /**
     * 获取http状态码
     * @return
     */
    public String getHttpStatus() {
        return httpStatus;
    }

    /**
     * 设置http状态码
     * @param httpStatus
     */
    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    /**
     * 获取http状态描述
     * @return
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * 设置http状态描述
     * @param statusName
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}

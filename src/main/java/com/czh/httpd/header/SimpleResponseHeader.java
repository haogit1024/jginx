package com.czh.httpd.header;

import com.czh.httpd.App;

/**
 * @author czh
 */
public class SimpleResponseHeader extends BaseHttpHeader {
    private String httpStatus;
    private String statusName;

    public SimpleResponseHeader() {
    }

    public SimpleResponseHeader(String responseHeaderString) {

    }

    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public void setFirstLine(String line) throws IllegalArgumentException {
        String[] statusLineArray = line.split(App.SPACE);
        int statusLineLength = statusLineArray.length;
        if (statusLineLength != 3) {
            throw new IllegalArgumentException("相应状态行格式错误, " + line);
        }
        this.httpVersion = statusLineArray[0];
        this.httpStatus = statusLineArray[1];
        this.statusName = statusLineArray[2];
    }

    @Override
    public String getFirstLine() {
        return this.httpVersion + App.SPACE + this.httpStatus + App.SPACE + this.statusName;
    }
}

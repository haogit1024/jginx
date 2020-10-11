package com.czh.httpd.header;

import com.czh.httpd.constant.CommonConstants;

/**
 * @author czh
 * 封装http请求头类
 */
public class SimpleRequestHeader extends BaseRequestHeader {

    public SimpleRequestHeader() {
    }

    /**
     * 根据http请求头生成类
     * @param requestHeaderString http请求头
     */
    public SimpleRequestHeader(String requestHeaderString) {
        super(requestHeaderString);
    }

    @Override
    public void setFirstLine(String line) throws IllegalArgumentException {
        String[] httpLineArray = line.split(CommonConstants.Symbol.SPACE);
        int httpLineLength = httpLineArray.length;
        if ( httpLineLength != 3) {
            throw new IllegalArgumentException("http行格式错误");
        }
        this.method = httpLineArray[0];
        this.url = httpLineArray[1];
        super.httpVersion = httpLineArray[2];
    }

    @Override
    public String getFirstLine() {
        return this.method + CommonConstants.Symbol.SPACE + this.url + CommonConstants.Symbol.SPACE + this.httpVersion;
    }

    @Override
    public String toString() {
        return this.build();
    }
}

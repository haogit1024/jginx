package com.czh.httpd.header;

import com.czh.httpd.App;
import com.czh.httpd.exception.HeaderFormatException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author czh
 * 封装http请求头类
 */
public class SimpleRequestHeader extends BaseHttpHeader {
    private String method = "GET";
    private String url = "/";

    public SimpleRequestHeader() {
    }

    /**
     * 根据http请求头生成类
     * @param requestHeaderString http请求头
     * @exception HeaderFormatException 请求头格式
     */
    public SimpleRequestHeader(String requestHeaderString) throws IllegalArgumentException {
        if (StringUtils.isBlank(requestHeaderString)) {
            throw new HeaderFormatException("请求头不能为空");
        }
        String[] headerArray = requestHeaderString.split(App.CRLF);
        int headerLineLength = headerArray.length;
        // http请求头第一行
        String httpLine = headerArray[0];
        this.setFirstLine(httpLine);

        // 处理第二行开始的http header键值对
        for (int i = 1; i < headerLineLength; i++) {
            String headerLine = headerArray[i];
            String[] headerLineArray = headerLine.split(App.HEADER_SEPARATOR);
            if (headerLineArray.length != 2) {
                throw new IllegalArgumentException("解析http首部错误: " + headerLine);
            }
            header.put(headerLineArray[0], headerLineArray[1]);
        }
    }

    /**
     * 设置http请求方式
     * @param method GET
     */
    public void settMethod(String method) {
        this.method = method;
    }

    /**
     * 获取http请求头
     * @return GET / POST / null
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置请求的资源
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return 请求的资源
     */
    public String getUrl() {
        return url;
    }

    @Override
    public void setFirstLine(String line) throws IllegalArgumentException {
        String[] httpLineArray = line.split(App.SPACE);
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
        return this.method + App.SPACE + this.url + App.SPACE + this.httpVersion + App.CRLF;
    }

    @Override
    public String toString() {
        return this.build();
    }
}

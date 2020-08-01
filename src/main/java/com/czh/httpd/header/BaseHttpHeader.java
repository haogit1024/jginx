package com.czh.httpd.header;

import com.czh.httpd.App;
import com.czh.httpd.constant.CommonConstants;
import com.czh.httpd.exception.HeaderFormatException;
import com.czh.httpd.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author czh
 * http首部
 */
public abstract class BaseHttpHeader {
    protected Map<String, String> header = new HashMap<>();
    protected String httpVersion;

    public BaseHttpHeader() {
    }

    /**
     * 根据http首部生成类
     * @param headerString http首部
     * @exception HeaderFormatException 首部格式错误
     */
    public BaseHttpHeader(String headerString) {
        if (StringUtils.isBlank(headerString)) {
            throw new HeaderFormatException("header不能为空");
        }
        String[] headerArray = headerString.split(CommonConstants.Symbol.CRLF);
        // http请求头第一行
        String httpLine = headerArray[0];
        this.setFirstLine(httpLine);
        this.parseHeader(headerArray);
    }

    /**
     * 处理第二行开始的http header键值对
     * @param headerArray http请求/相应头, 用换行符切分后的数组
     */
    protected void parseHeader(String[] headerArray) {
        int headerLineLength = headerArray.length;
        for (int i = 1; i < headerLineLength; i++) {
            String headerLine = headerArray[i];
            String[] headerLineArray = headerLine.split(CommonConstants.Symbol.HEADER_SEPARATOR);
            if (headerLineArray.length != 2) {
                throw new IllegalArgumentException("解析http首部错误: " + headerLine);
            }
            this.header.put(headerLineArray[0], headerLineArray[1]);
        }
    }

    /**
     * 设置http首部第一行, 如果是request header就是http行, 如果是response header则是状态行
     * @param line 首行
     * @throws IllegalArgumentException 首行参数格式不正确
     */
    public abstract void setFirstLine(String line) throws IllegalArgumentException;

    /**
     * 取http首部第一行, 如果是request header就是http行, 如果是response header则是状态行
     * @return 首行
     */
    public abstract String getFirstLine();

    /**
     * @return http版本号
     */
    public String getHttpVersion() {
        return httpVersion;
    }

    /**
     * 设置http版本
     * @param httpVersion HTTP/1.1
     */
    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    /**
     * 设置请求头
     * @param key
     * @param value
     */
    public void setHeader(String key, String value) {
        if (StringUtils.isNotBlank(value)) {
            header.put(key, value);
        }
    }

    /**
     * 根据key获取头部的一个值
     * @param key
     * @return
     */
    public String getHeader(String key) {
        return header.get(key);
    }

    /**
     * 获取cookie
     * @return
     */
    public String getCookie() {
        return header.get("Cookie");
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getFirstLine()).append(CommonConstants.Symbol.CRLF);
        header.forEach((k, v) -> sb.append(k)
                .append(CommonConstants.Symbol.HEADER_SEPARATOR)
                .append(v)
                .append(CommonConstants.Symbol.CRLF));
        sb.append(CommonConstants.Symbol.CRLF);
        return sb.toString();
    }
//    String demo = """
//        GET / HTTP/1.1
//        Host: localhost:8098
//        User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0
//        Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
//        Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
//        Accept-Encoding: gzip, deflate
//        Connection: keep-alive
//        Cookie: Webstorm-d7242ebb=49e9c29e-7d46-4097-a9b3-7ed6d90c94b8
//        Upgrade-Insecure-Requests: 1
//        Cache-Control: max-age=0
//        """;
//    String demo = """
//        HTTP/1.1 200 OK
//        Cache-Control: private, max-age=0, no-cache
//        Content-Length: 43
//        Content-Type: image/gif
//        Date: Sat, 30 Nov 2019 14:34:30 GMT
//        Pragma: no-cache
//        Server: apache
//        Strict-Transport-Security: max-age=172800
//        X-Content-Type-Options: nosniff
//        """;
}

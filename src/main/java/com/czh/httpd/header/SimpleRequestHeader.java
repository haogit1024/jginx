package com.czh.httpd.header;

import com.czh.httpd.App;
import com.czh.httpd.exception.HeaderFormatException;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author czh
 * 封装http请求头类
 */
public class SimpleRequestHeader implements RequestHeader {

    private String method = "GET";
    private String url = "/";
    private String httpVersion = "HTTP/1/1";
    private Map<String, String> header = new HashMap<>();

    public SimpleRequestHeader() {
    }

    /**
     * 根据http请求头生成类
     * @param requestHeaderString http请求头
     * @exception HeaderFormatException 请求头格式
     */
    public SimpleRequestHeader(String requestHeaderString) throws HeaderFormatException {
        if (StringUtils.isBlank(requestHeaderString)) {
            throw new HeaderFormatException("请求头不能为空");
        }
        String[] headerArray = requestHeaderString.split(App.CRLF);
        int headerLineLength = headerArray.length;
        // http请求头第一行
        String httpLine = headerArray[0];
        String[] httpLineArray = httpLine.split(App.SPACE);
        int httpLineLength = httpLineArray.length;
        if ( httpLineLength != 3) {
            throw new HeaderFormatException("http行格式错误");
        }
        this.method = httpLineArray[0];
        this.url = httpLineArray[1];
        this.httpVersion = httpLineArray[2];

        // 处理第二行开始的http header键值对
        for (int i = 1; i < headerLineLength; i++) {
            String headerLine = headerArray[i];
            String[] headerLineArray = headerLine.split(App.HEADER_SEPARATOR);
            if (headerLineArray.length != 2) {
                throw new HeaderFormatException("解析http首部错误: " + headerLine);
            }
            header.put(headerLineArray[0], headerLineArray[1]);
        }
    }

    @Override
    public void settMethod(String method) {
        this.method = method;
    }

    @Override
    public String getMethod() {
        return method;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
    }

    @Override
    public String getHttpVersion() {
        return httpVersion;
    }

    @Override
    public void setHeader(String key, String value) {
        header.put(key, value);
    }

    @Override
    public String getHeader(String key) {
        return header.get(key);
    }

    //    String demo = """
//            GET / HTTP/1.1
//            Host: localhost:8098
//            User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0
//            Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
//            Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
//            Accept-Encoding: gzip, deflate
//            Connection: keep-alive
//            Cookie: Webstorm-d7242ebb=49e9c29e-7d46-4097-a9b3-7ed6d90c94b8
//            Upgrade-Insecure-Requests: 1
//            Cache-Control: max-age=0
//            """;

    @Override
    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.method).append(App.SPACE).append(this.url).append(App.SPACE).append(this.httpVersion).append(App.CRLF);
        header.forEach((k, v) -> sb.append(k).append(App.HEADER_SEPARATOR).append(v).append(App.CRLF));
        sb.append(App.CRLF);
        return sb.toString();
    }

    @Override
    public String toString() {
        return this.build();
    }
}

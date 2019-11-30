package com.czh.httpd.header;

import com.czh.httpd.App;

import java.util.HashMap;
import java.util.Map;

/**
 * @author czh
 * http首部
 */
public abstract class BaseHttpHeader {
    protected Map<String, String> header = new HashMap<>();
    protected String httpVersion;

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
        header.put(key, value);
    }

    /**
     * 根据key获取头部的一个值
     * @param key
     * @return
     */
    public String getHeader(String key) {
        return header.get(key);
    }

    public String build() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getFirstLine());
        header.forEach((k, v) -> sb.append(k).append(App.HEADER_SEPARATOR).append(v).append(App.CRLF));
        sb.append(App.CRLF);
        return sb.toString();
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
}

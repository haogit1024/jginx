package com.czh.httpd.response;

import java.io.IOException;

/**
 * @author chenzh
 * @deprecated
 */
public interface IResponse {
    /**
     * 获取响应头
     * @return Http响应头
     * @param length 相应头长度
     */
    String getHeader(int length);

    /**
     * 获取响应体
     * @return Http响应体
     */
    String getContent() throws IOException;

    /**
     * 获取Http响应
     * @return 获取Http响应
     */
    String getResponse() throws IOException;
}

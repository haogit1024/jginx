package com.czh.httpd.util;

/**
 * @author czh
 * http header解析类
 */
public class HttpHeaderUtil{

    /**
     * 解析http首部range
     * @param range bytes 0-10/1560323
     * @return
     */
    public static int[] parseRange(String range) {
       String[] headerArr =  range.split("/");
       return new int[0];
    }
}

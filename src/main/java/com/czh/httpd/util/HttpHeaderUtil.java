package com.czh.httpd.util;

/**
 * @author czh
 * http header解析类
 */
public class HttpHeaderUtil{

    /**
     * 解析http首部range
     * @param range Range bytes={start}-{end}
     * @return
     * @throws IllegalArgumentException range格式错误
     */
    public static int[] parseRequestRange(String range) throws IllegalArgumentException{
        try {
            String[] headerArr = range.split("=");
            String[] dataArray = headerArr[1].split("-");
            return new int[]{Integer.parseInt(dataArray[0]), Integer.parseInt(dataArray[1])};
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("range格式错误");
        }
    }
    /**
     * 生成http响应首部Range
     * @return bytes 0-10/1560323
     */
    public static String genResponseRange(int start, int end, int len) {
        return "bytes " + start + "-" + end + "/" + len;
    }
}

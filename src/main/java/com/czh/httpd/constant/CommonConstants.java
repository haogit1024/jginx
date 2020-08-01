package com.czh.httpd.constant;

/**
 * 通用常量类
 * @author czh
 * @date 2020/07/25
 */
public interface CommonConstants {
    class Symbol {
        /**
         * http换行符
         */
        public static final String CRLF="\r\n";

        /**
         * 空格
         */
        public static final String SPACE =" ";

        /**
         * http头分隔符
         */
        public static final String HEADER_SEPARATOR = ": ";

        /**
         * 工作目录
         */
        public static final String WORK_SPACE = "D:\\www";

        public static final String RESOURCE_DIR = "/static";
    }

    class Page {
        /**
         * 首页
         */
        public static final String INDEX  = "/static/index.html";
        /**
         * 错误页
         */
        public static final String ERROR  = "/static/error.html";
        /**
         * 404页
         */
        public static final String NOT_FOUND  = "/static/404.html";
    }

    /**
     * http 头部的 key
     */
    class HttpHeader {

    }
}

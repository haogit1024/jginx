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
     * 配置文件默认路径
     */
    class SystemConfig {
        /**
         * 默认json配置文件
         */
        public static final String DEFAULT_JSON_CONFIG_PATH = "application.json";
    }

    /**
     * http 头部的 key
     */
    class HttpHeader {

    }

    class ContentTypes {
        public final static String DOWNLOAD = "";
        public final static String TEXT = "text/html";
        public final static String VIDEO = "";
        public final static String AUDIO = "";
    }
}

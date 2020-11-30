package com.czh.httpd.entity;

import lombok.Data;

/**
 * @author chenzh
 * @date 2020/10/10
 */
@Data
public class DefaultConfig {
    /**
     * server配置文件路径
     */
    private String serverPath;

    /**
     * 最大执行线程数(不包括监听线程数)
     */
    private Integer maxTheadNum;

    /**
     * 监听关闭操作的端口(守护线程端口)
     */
    private Integer closePort;
}

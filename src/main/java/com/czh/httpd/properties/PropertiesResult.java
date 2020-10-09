package com.czh.httpd.properties;

import com.czh.httpd.util.PropertiesLoader;
import com.czh.httpd.util.StringUtils;

import java.lang.reflect.Field;

/**
 * 配置文件加载类，支持 yml 和 properties 配置文件
 * 1. 构造函数
 * 2. 读取配置文件，生产实体类方法
 * @author czh
 * @date 2020/10/4
 */
public class PropertiesResult {
    private final PropertiesLoader propertiesLoader;

    public PropertiesResult(String propertiesFilePath) {
        // 初始化 propertiesLoader
        try {
            propertiesLoader = PropertiesLoader.instance(propertiesFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            // TODO 自定义读取失败异常
            throw new RuntimeException("读取配置文件出错");
        }
    }

    /**
     * 通过 class 反射获取字段名，然后通过 propertiesLoad 获取属性设置值
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getEntity(Class<T> clazz) {
        try {
            T ret = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String key = field.getName();
                String strVal = propertiesLoader.getProperty(key);
                Class<?> fieldType = field.getType();
                if (StringUtils.isNotBlank(strVal)) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    // TODO 基本类型 或者 调用 valueOf 方法
                    field.set(ret, fieldType.cast(strVal));
                }
            }
            return ret;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T getEntity(String prefix, Class<T> clazz) {
        return null;
    }
}

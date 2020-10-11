package com.czh.httpd.util;

import com.czh.httpd.entity.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author czh
 */
public class PropertiesLoader {
    private final Properties properties;
    private static HashMap<String, PropertiesLoader> map = new HashMap<>();

    /**
     * 根据一个路径初始化，可以是resource的相对路径和绝对路径
     * 相对路径时，不能以 '/' 开头
     * @param filePath
     * @throws IOException
     */
    public PropertiesLoader(String filePath) throws IOException {
        assert (StringUtils.isNotBlank(filePath)) : "读取的配置文件路径不能为空";
        // 先判断绝对路径下是否存在, 如果不存在则读相对路径
        File file = new File(filePath);
        InputStream inputStream;
        if (file.exists()) {
            // 绝对路径
            inputStream = new FileInputStream(filePath);
        } else {
            // resource下的相对路径
            inputStream = PropertiesLoader.class.getResourceAsStream("/" + filePath);
        }
        assert inputStream != null : "配置文件不存在";
        properties = new Properties();
        properties.load(inputStream);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public <T> T loadAs(String prefix, Class<T> clazz) {
        try {
            T ret = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                String key = field.getName();
                if (StringUtils.isNotBlank(prefix)) {
                    key = prefix + "." + key;
                }
                String strVal = getProperty(key);
                Object val = strVal;
                Class<?> fieldType = field.getType();
                if (!fieldType.equals(String.class)) {
                    // 转换到字段类型
                    try {
                        Method valueOfMethod = fieldType.getMethod("valueOf", String.class);
                        try {
                            val = valueOfMethod.invoke(null, strVal);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }
                field.set(ret, val);
            }
            return ret;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            // TODO 自定义异常
        }
        return null;
    }

    public void display() {
        Set<Map.Entry<Object, Object>> set = properties.entrySet();
        set.forEach(entry -> System.out.printf("key: %s, val: %s \n", entry.getKey(), entry.getValue()));
    }

    /**
     * 生成 PropertiesLoader 的工厂方法
     * @param propertiesFilePath
     * @return
     * @throws IOException
     */
    public static PropertiesLoader instance(String propertiesFilePath) {
        if (map.get(propertiesFilePath) == null) {
            synchronized (PropertiesLoader.class) {
                if (map.get(propertiesFilePath) == null) {
                    PropertiesLoader propertiesLoader;
                    try {
                        propertiesLoader = new PropertiesLoader(propertiesFilePath);
                        map.put(propertiesFilePath, propertiesLoader);
                    } catch (IOException e) {
                        e.printStackTrace();
                        // TODO 完善错误信息
                        System.err.println();
                        System.exit(0);
                    }
                }
            }
        }
        return map.get(propertiesFilePath);
    }

    public static void main(String[] args) throws IOException {
        /*PropertiesLoader propertiesLoader = new PropertiesLoader("D:\\czhcode\\github\\java\\simple\\new_util\\src\\main\\resources\\db.properties");
        propertiesLoader.display();*/
        PropertiesLoader propertiesLoader = new PropertiesLoader("setting.properties");
        TestProperties testProperties = propertiesLoader.loadAs("server", TestProperties.class);
        System.out.println(testProperties.getPort());
        Server.Location location = new Server.Location();
    }
}

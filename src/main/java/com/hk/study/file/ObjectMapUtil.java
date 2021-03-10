package com.hk.study.file;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年03月10日 10:10
 */
@Component
public class ObjectMapUtil {

    /**
     * 注意: 实体类对象所有的属性值不能含有null, 会抛NullPointException
     *
     * @param clazz:  实体类
     * @param object: 实体类对象
     * @return
     */
    public Map<String, Object> objectToMap(Class clazz, Object object) {
        return Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toMap(name -> name.getName(), field -> {
            try {
                field.setAccessible(true);
                Object value = field.get(object);
                if (value == null) {
                    value = "";
                }
                return value;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }));
    }
}

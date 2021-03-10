package com.hk.study.string;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年02月04日 10:14
 */
public class StringTest {

    @Test
    void testContact() {
        String name = "jason";
        String age = null;
        if (StringUtils.isNotBlank(name)) {
            name = name.concat(age);
        } else {
            name = age;
        }
        System.out.println(name);
    }
}

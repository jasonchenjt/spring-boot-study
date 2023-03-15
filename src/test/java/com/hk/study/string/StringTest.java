package com.hk.study.string;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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

    @Test
    void justFileType() {
        String pdf = "COS207_50B1900445-F-1.pdf";
        List<Object> pdfs = Arrays.asList(pdf.split("\\."));
        Object pdfLast = CollUtil.getLast(pdfs);
        String word = "HKAPP_Declaration_A10363.docx";
        List<Object> words = Arrays.asList(word.split("\\."));
        Object wordLast = CollUtil.getLast(words);
        System.out.println(pdfLast);
        System.out.println(wordLast);
    }

    @Test
    void name() {
        String name = null;
        String name2 = null;
        String name3 = "Jason";
        String s = Optional.ofNullable(name).orElse(name2);
        s = Optional.ofNullable(s).orElse(name3);
        System.out.println(s);
    }

    @Test
    void removeAllTest() {
        String name = "my name is jason ";
        String s = StrUtil.removeAll(name, " ");
        System.out.println(s + "123");
    }

    @Test
    void equalTest() {
        // 享元模式:
        String name = "Jason";
        String name2 = "Jason";
        String name3 = new String("Jason");

        System.out.println(name == name2);
        System.out.println(name == name3);
    }

    @Test
    void format() {
        System.out.println(String.format("%02d", 1));
    }
}

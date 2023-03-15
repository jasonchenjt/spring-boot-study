package com.hk.study.string;

import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年07月14日 9:49
 */
public class StringJoinerTest {

    @Test
    void name() {
        StringJoiner joiner3 = new StringJoiner("','", "'", "'");
        joiner3.add("1").add("2");
        System.out.println(joiner3.toString());
    }


}

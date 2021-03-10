package com.hk.study.date;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年03月09日 9:36
 */
class LocalDateStudyTest {

    @Test
    void localDateToInstantTest() {
        LocalDateStudy localDateStudy = new LocalDateStudy();
        localDateStudy.localDateToInstant();
    }
}
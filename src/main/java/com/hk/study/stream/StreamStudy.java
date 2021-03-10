package com.hk.study.stream;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年03月10日 11:30
 */
public class StreamStudy {

    /**
     * 合拼List
     */
    public void mergeList() {
        List<String> strList = Arrays.asList("1", "2", "3");
        List<String> strList2 = Arrays.asList("4", "5", "6");

        List<String> collect = Stream.of(strList, strList2)
                .parallel()
                .flatMap(record -> record.stream())
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}

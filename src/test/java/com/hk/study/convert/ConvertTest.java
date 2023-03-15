package com.hk.study.convert;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import org.checkerframework.checker.units.qual.C;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年03月24日 17:09
 */
public class ConvertTest {

    private List<Chart> chartList = Lists.newArrayList();

    Map<String, String> targetMap = new HashMap<>();

    @BeforeEach
    public void init() {
        Chart chart_a = new Chart("fireChart", 10);
        Chart chart_b = new Chart("lifeChart", 20);
        Chart chart_c = new Chart("sandChart", 30);
        chartList = Arrays.asList(chart_a, chart_b, chart_c);
        targetMap.put("fireChart", "fire");
        targetMap.put("lifeChart", "life");
        targetMap.put("sandChart", "sand");
    }

    @Test
    void name() {
        InspectionChartConvert inspectionChartConvert = new InspectionChartConvert();
        Arrays.stream(InspectionChartConvert.class.getDeclaredFields())
                .forEach(field -> {
                    Chart chart = chartList.parallelStream()
                            .filter(record -> field.getName().trim().equalsIgnoreCase(record.getType()))
                            .map(record -> {
                                String target = targetMap.get(field.getName());
                                record.setTarget(target);
                                return record;
                            })
                            .findFirst()
                            .orElse(new Chart());
                    try {
                        field.setAccessible(true);
                        field.set(inspectionChartConvert, chart);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });

        Map<String, Object> map = new HashMap<>();
        Arrays.stream(inspectionChartConvert.getClass().getDeclaredFields())
                .filter(field -> field.getType().equals(Chart.class))
                .map(field -> {
                    try {
                        field.setAccessible(true);
                        return (Chart) field.get(inspectionChartConvert);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return new Chart();
                    }
                })
                .forEach(value -> {
                    String target = value.getTarget();
                    Integer val = value.getValue();
                    map.put(target.concat("value"), val);
                });
        map.forEach((k, v) -> {
            System.out.println(k + ":" + v);
        });
    }

    @Test
    void name3() {
        Field[] declaredFields = InspectionChartConvert.class.getDeclaredFields();
        Arrays.stream(declaredFields).map(Field::getName).forEach(System.out::println);
    }
}

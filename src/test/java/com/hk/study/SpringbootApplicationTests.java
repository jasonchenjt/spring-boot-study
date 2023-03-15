package com.hk.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.collections.Lists;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
class SpringbootApplicationTests {

    @Test
    void contextLoads() {
        Double value = Double.valueOf(9.088333);
        String[] split = String.valueOf(value).split("\\.");
        int parseInt = value.intValue();
        int parseInt1 = Integer.valueOf(split[1]) * 60;
        double pow = Math.pow(0.1, split[1].length());
        Float aFloat = Float.valueOf(String.format("%.6f", pow)) * parseInt1;
        String format = String.format("%.4f", aFloat);

        String[] strings = format.split("\\.");
        String str = String.format("%04d", Integer.valueOf(strings[1]));

        String result = parseInt + "°" + strings[0] + "." + str + "'";
        System.out.println(result);
    }

    @Test
    void name() {

        AtomicInteger i = new AtomicInteger(Integer.valueOf(0));
        List<Integer> ints = Lists.newArrayList();
        for (int i1 = 0; i1 < 100; i1++) {
            ints.add(i1);
        }

        ints.parallelStream()
                .forEach(value -> {
                    i.getAndIncrement();
                });
        System.out.println(i);
    }
}

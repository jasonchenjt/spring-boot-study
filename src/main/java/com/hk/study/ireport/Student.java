package com.hk.study.ireport;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年05月10日 15:40
 */
public class Student {

    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("年龄")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}

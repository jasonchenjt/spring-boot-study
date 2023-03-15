package com.hk.study.convert;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年03月24日 17:14
 */
public class Chart {
    private String type;
    private Integer value;
    private String target;

    public Chart(String type, Integer value) {
        this.type = type;
        this.value = value;
    }

    public Chart() {
    }

    public Chart(String target, String type) {
        this.target = target;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return "Chart{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", target='" + target + '\'' +
                '}';
    }
}

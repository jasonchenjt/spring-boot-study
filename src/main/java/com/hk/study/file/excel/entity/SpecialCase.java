package com.hk.study.file.excel.entity;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年03月08日 15:43
 */
public class SpecialCase {
    private String launchName;
    private String routeCode;
    private Double hours;

    public String getLaunchName() {
        return launchName;
    }

    public void setLaunchName(String launchName) {
        this.launchName = launchName;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public void setRouteCode(String routeCode) {
        this.routeCode = routeCode;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }
}

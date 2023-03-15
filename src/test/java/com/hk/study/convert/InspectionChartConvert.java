package com.hk.study.convert;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年03月24日 17:08
 */
public class InspectionChartConvert {

    private Chart fireChart = new Chart("fire", "f");
    private Chart lifeChart = new Chart("life", "l");
    private Chart sandChart = new Chart("sand", "s");

    public Chart getFireChart() {
        return fireChart;
    }

    public void setFireChart(Chart fireChart) {
        this.fireChart = fireChart;
    }

    public Chart getLifeChart() {
        return lifeChart;
    }

    public void setLifeChart(Chart lifeChart) {
        this.lifeChart = lifeChart;
    }

    public Chart getSandChart() {
        return sandChart;
    }

    public void setSandChart(Chart sandChart) {
        this.sandChart = sandChart;
    }

    @Override
    public String toString() {
        return "InspectionChartConvert{" +
                "fireChart=" + fireChart +
                ", lifeChart=" + lifeChart +
                ", sandChart=" + sandChart +
                '}';
    }
}

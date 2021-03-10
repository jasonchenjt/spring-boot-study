package com.hk.study.file.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.*;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2020年09月22日 15:28
 */
public class HuTullTest {

    @Test
    public void testUse() throws IOException {
        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");
        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);
        //通过工具类创建writer
//        ExcelWriter writer = ExcelUtil.getWriter("D:\\testTool2.xlsx");
//通过构造方法创建writer
        ExcelWriter writer = new ExcelWriter("D:/writeTest8.xls");
//跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();
//合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() - 1, "测试标题");
//一次性写出内容，强制输出标题
        writer.write(rows, true);
        Workbook workbook = writer.getWorkbook();
        Sheet chartSheet = workbook.createSheet("Chart");

        // 创建字节输出流
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        ByteArrayOutputStream byteArrayOut2 = new ByteArrayOutputStream();

        //如 果不使用Font,中文将显示不出来
        Font font = new Font("Arial", Font.BOLD, 10);
        // 创建数据
        Map<String, Map<String, Double>> datas = new HashMap<String, Map<String, Double>>();

        Map<String, Double> map1 = new HashMap<String, Double>();
        Map<String, Double> map2 = new HashMap<String, Double>();
        Map<String, Double> map3 = new HashMap<String, Double>();

        map1.put("type222", (double) 1000);
        map2.put("type333", (double) 1300);
        map3.put("type444", (double) 1000);

        map3.put("type445", (double) 200);
        map3.put("type446", (double) 300);
        map3.put("type447", (double) 400);


        //压入数据
        datas.put("map1", map1);
        datas.put("map2", map2);
        datas.put("map3", map3);

        String chartTitle = "Total No. of Casualty received";
        JFreeChart chart = createPort(chartTitle, datas, "type", "unit", font);
        // 读取chart信息至字节输出流
        ChartUtilities.writeChartAsPNG(byteArrayOut, chart, 600, 300);
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0, (short) 2, (short) 1, (short) 12, (short) 15);
        anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
        chartSheet.createDrawingPatriarch().createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));

        /* 第二张表格 */
        ChartUtilities.writeChartAsPNG(byteArrayOut2, chart, 2000, 300);
        HSSFClientAnchor anchor2 = new HSSFClientAnchor(0, 0, 0, 0, (short) 2, (short) 14, (short) 12, (short) 27);
        anchor2.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
        chartSheet.createDrawingPatriarch().createPicture(anchor2, workbook.addPicture(byteArrayOut2.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
//关闭writer，释放内存
        writer.close();
    }

    public static JFreeChart createPort(String title, Map<String, Map<String, Double>> datas, String type, String danwei, Font font) {
        try {
            //种类数据集
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            //获取迭代器：
            Set<Map.Entry<String, Map<String, Double>>> set1 = datas.entrySet();
            Iterator iterator1 = set1.iterator();
            Iterator iterator2;
            HashMap<String, Double> map;
            Set<Map.Entry<String, Double>> set2;
            Map.Entry entry1;
            Map.Entry entry2;

            while (iterator1.hasNext()) {
                entry1 = (Map.Entry) iterator1.next();
                map = (HashMap<String, Double>) entry1.getValue();
                set2 = map.entrySet();
                iterator2 = set2.iterator();
                while (iterator2.hasNext()) {
                    entry2 = (Map.Entry) iterator2.next();
                    ds.setValue(Double.parseDouble(entry2.getValue().toString()), entry2.getKey().toString(), entry1.getKey().toString());
                }
            }

            //创建柱状图,柱状图分水平显示和垂直显示两种
            JFreeChart chart = ChartFactory.createBarChart(title, type, danwei, ds, PlotOrientation.VERTICAL, false, false, false);

            //设置整个图片的标题字体
            chart.getTitle().setFont(font);

            //设置提示条字体
//            font = new Font("Arial", Font.BOLD, 15);
//            chart.getLegend().setItemFont(font);

            //得到绘图区
            CategoryPlot plot = (CategoryPlot) chart.getPlot();
            //得到绘图区的域轴(横轴),设置标签的字体
            plot.getDomainAxis().setLabelFont(font);

            //设置横轴标签项字体
            plot.getDomainAxis().setTickLabelFont(font);

            //设置范围轴(纵轴)字体
            plot.getRangeAxis().setLabelFont(font);

            plot.setForegroundAlpha(1.0f);
            return chart;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

package com.hk.study.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.WriteTable;
import com.hk.study.ireport.Student;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.testng.collections.Lists;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年05月12日 11:04
 */
public class EasyExcelTest {

    /**
     * 简单的导出
     */
    @Test
    void name() {
        Student student = new Student("Jason", 26);
        Student student1 = new Student("Jason", 27);
        Student student2 = new Student("Jason", 28);
        Student student3 = new Student("Jason", 29);
        List studentList = Lists.newArrayList(student, student1, student2, student3);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss").withZone(ZoneId.systemDefault());
        String fileName = "D:\\test" + dateTimeFormatter.format(Instant.now()) + ".xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, Student.class)
                .sheet("学生资料")
                .doWrite(studentList);

       /* // 写法2
        fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        // 这里 需要指定写用哪个class去写
        ExcelWriter excelWriter = null;
        try {
            excelWriter = EasyExcel.write(fileName, DemoData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("模板").build();
            excelWriter.write(data(), writeSheet);
        } finally {
            // 千万别忘记finish 会帮忙关闭流
            if (excelWriter != null) {
                excelWriter.finish();
            }
        }*/
    }


    /**
     * 复杂表头
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {
        // 文件输出位置
        OutputStream out = new FileOutputStream("d://test.xlsx");
        ExcelWriter writer = EasyExcelFactory.write(out).build();

        // 动态添加表头，适用一些表头动态变化的场景
        WriteSheet sheet1 = new WriteSheet();
        sheet1.setSheetName("商品明细");
        sheet1.setSheetNo(0);
        // 创建一个表格，用于 Sheet 中使用
        WriteTable table = new WriteTable();
        table.setTableNo(1);
        table.setHead(head());
        // 写数据
        writer.write(contentData(), sheet1, table);


        /*第二张Sheet*/
        WriteSheet sheet2 = new WriteSheet();
        sheet2.setSheetName("商品详细");
        sheet2.setSheetNo(1);

        WriteTable table2 = new WriteTable();
        table2.setTableNo(20);
        table2.setHead(head());

        writer.write(null, sheet1, table2);
        writer.write(contentData(), sheet2, table2);

        /*关闭writer流*/
        writer.finish();
        out.close();
    }

    @NotNull
    private static List<List<String>> head() {
        List<List<String>> headTitles = Lists.newArrayList();
        String basicInfo = "Harbour Patrol Section",
                skuInfo = "Working Time Sheet",
                orderInfo = "For The Month of Mar-2021",
                empty = " ";
        headTitles.add(Lists.newArrayList(basicInfo, skuInfo, orderInfo, empty, "Officer Name", "Date"));
        headTitles.add(Lists.newArrayList(basicInfo, skuInfo, orderInfo, empty, "Foo Ying Hei", "Holiday"));
        headTitles.add(Lists.newArrayList(basicInfo, skuInfo, orderInfo, empty, "Foo Ying Hei", "Duty"));
        headTitles.add(Lists.newArrayList(basicInfo, skuInfo, orderInfo, empty, "Foo Ying Hei", "Launch"));
        headTitles.add(Lists.newArrayList(basicInfo, skuInfo, orderInfo, empty, "W.E.F", "Roster Hours"));
        headTitles.add(Lists.newArrayList(basicInfo, skuInfo, orderInfo, empty, "W.E.F", "Other Hours"));
        headTitles.add(Lists.newArrayList(basicInfo, skuInfo, orderInfo, empty, "01-3月-2021", "Shift Duty Allowance Hours"));
        headTitles.add(Lists.newArrayList(basicInfo, skuInfo, orderInfo, empty, "To", "Shift Duty Allowance Hours"));
        return headTitles;
    }

//    private static List<List<String>> bottom() {
//        List<List<String>> bottomList = Lists.newArrayList();
//        String basicInfo = "基础资料", skuInfo = "商品扩展", orderInfo = "经营情况", empty = " ";
//        //第一列，1/2/3行
//        headTitles.add(Lists.newArrayList(basicInfo, basicInfo, "类别"));
//        //第二列，1/2/3行
//        headTitles.add(Lists.newArrayList(basicInfo, basicInfo, "名称"));
//        List<String> skuTitles = Lists.newArrayList("组合商品", "上一次优惠时间", "销售次数", "库存", "价格");
//        skuTitles.forEach(title -> {
//            headTitles.add(Lists.newArrayList(skuInfo, skuInfo, title));
//        });
//        List<Integer> monthList = Lists.newArrayList(5, 6);
//        //动态根据月份生成
//        List<String> orderSpeaces = Lists.newArrayList("销售额", "客流", "利润");
//        monthList.forEach(month -> {
//            orderSpeaces.forEach(title -> {
//                headTitles.add(Lists.newArrayList(orderInfo, month + "月", title));
//            });
//        });
//        //无一、二行标题
//        List<String> lastList = Lists.newArrayList("日均销售金额(元)", "月均销售金额(元)");
//        lastList.forEach(title -> {
//            headTitles.add(Lists.newArrayList(empty, empty, title));
//        });
//        return headTitles;
//    }

    private static List<List<Object>> contentData() {
        List<List<Object>> contentList = Lists.newArrayList();
        //这里一个List<Object>才代表一行数据，需要映射成每行数据填充，横向填充（把实体数据的字段设置成一个List<Object>）
        contentList.add(Lists.newArrayList("测试", "商品A", "苹果🍎"));
        contentList.add(Lists.newArrayList("测试", "商品B", "橙子🍊"));
        return contentList;
    }
}

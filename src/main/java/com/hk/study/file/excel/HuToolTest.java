package com.hk.study.file.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.hk.study.file.ObjectMapUtil;
import com.hk.study.file.excel.DTO.MonthlyCasualtyDTO;
import com.hk.study.file.excel.entity.SpecialCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 文件描述:Hutool 工具类的使用
 *
 * @author Jason.Chen
 * @date 2020年09月23日 17:03
 */
public class HuToolTest {

    @Test
    public void test() {
        String name = "my name is {}";
        name = StrUtil.format(name, "Jason");
        System.out.println(name);
    }

    @Autowired
    private ObjectMapUtil util;

    @Test
    public void testObjectToMap() {

        MonthlyCasualtyDTO monthlyCasualtyDTO = new MonthlyCasualtyDTO();
        monthlyCasualtyDTO.setSectorname("jason");
        monthlyCasualtyDTO.setCoalesce0(1);

        Map<String, Object> map = util.objectToMap(MonthlyCasualtyDTO.class, monthlyCasualtyDTO);
        map.values().forEach(System.out::println);
    }

    @Test
    public void testGen() {
        ExcelWriter writer = ExcelUtil.getWriter("D:\\test1.xls");
//        setHeaderTitle(writer);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
        List<String> titleMonth = CollUtil.newArrayList("Month", Objects.nonNull(Instant.now()) ? dateTimeFormatter.format(Instant.now()) : "");
        List<String> head = CollUtil.newArrayList("", "", "River Trade Vessel", "Local Craft", "Ocean-going vessel", "Government Launch", "Unknown/Other vessel");
        List<List<String>> rows = CollUtil.newArrayList();
        rows.add(head);
        List<List<String>> title = CollUtil.newArrayList();
        title.add(titleMonth);


        writer.merge(6, "Monthly Casualty Report", false);
        writer.write(title);
        writer.passCurrentRow();
        writer.passCurrentRow();
        writer.write(rows, true);

        writer.merge(3, 4, 0, 0, "Type of incident", true);
        writer.merge(3, 4, 1, 1, "No. of Cases", true);
        writer.merge(3, 3, 2, 6, "Number of vessels involved", true);

//        writer.merge(4, 5, 2, 2, "River Trade Vessel", false);
//        writer.merge(4, 5, 3, 3, "Local Craft", false);
//        writer.merge(4, 5, 4, 4, "Ocean-going vessel", false);
//        writer.merge(4, 5, 5, 5, "Government Launch", false);
//        writer.merge(4, 5, 6, 6, "Unknown/Other vessel", false);

//        writer.write(records, false);
//        writer.flush(outputStream);
        writer.close();
    }


    /**
     * 生成复杂的title
     */
    @Test
    public void testGenTitle() {
        ExcelWriter writer = ExcelUtil.getWriter("D:\\testTitle4.xls");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());

        List<String> title = CollUtil.newArrayList("Working Time  Sheet");
        List<String> titleMonth = CollUtil.newArrayList("Month", dateTimeFormatter.format(Instant.now()), "", "W.E.F", "", "", "", "Days");
        List<String> titleOfficer = CollUtil.newArrayList("Officer Name", "", "", "Base Line", "Weekday (7hrs)", "", "", "Hours(A)");
        List<String> titleRank = CollUtil.newArrayList("Rank", "", "", "", "Saturday (4hrs)", "", "", "Hours(B)");
        List<String> titleDuty = CollUtil.newArrayList("Duty", "", "", "", "Conditional Working hours of", "", "", "Hours(C)");
        List<String> titleDayman = CollUtil.newArrayList("Dayman", "D,TF,C,OC,OPS1,OPS2", "", "Last Month", "Working hour balance(+/-) on", "", "", "Hours(D)");
        List<String> titleStagger = CollUtil.newArrayList("Stagger", "1, N23,28,73,74,77", "", "This Month", "Actual working hours of the month", "", "", "Hours(E)");
        List<String> title24H = CollUtil.newArrayList("24 H", "M,N", "", "", "Total working hours (D+E)", "", "", "Hours(F)");
        List<String> titleLeave = CollUtil.newArrayList("Leave", "L,L(AM),L(PM)", "", "Next Month", "Balance carry forward (F-C)", "", "", "Hours");
        List<List<String>> titleRows = CollUtil.newArrayList(title, titleMonth, titleOfficer, titleRank, titleDuty, titleDayman, titleStagger, title24H, titleLeave);

        /* 添加Title*/
        writer.write(titleRows);
        writer.merge(2, 2, 1, 2, "Officer name", false);
        writer.merge(3, 3, 1, 2, "rank code", false);
        writer.merge(4, 4, 0, 2, "Duty", false);
        writer.merge(5, 5, 1, 2, "D,TF,C,OC,OPS1,OPS2", false);
        writer.merge(6, 6, 1, 2, "1, N23,28,73,74,77", false);
        writer.merge(7, 7, 1, 2, "M,N", false);
        writer.merge(8, 8, 1, 2, "L,L(AM),L(PM)", false);
        writer.autoSizeColumnAll();
        writer.close();
    }

    /**
     * 生成复杂的title
     */
    @Test
    public void testChangeStyle() {
        ExcelWriter writer = ExcelUtil.getWriter("D:\\excelStyle.xls");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());

        List<String> title = CollUtil.newArrayList("Working Time Sheet");
        List<String> titleMonth = CollUtil.newArrayList("Month", dateTimeFormatter.format(Instant.now()), "", "W.E.F", "", "", "", "Days");
        List<List<String>> titleRows = CollUtil.newArrayList(title, titleMonth);

        /* 添加Title*/
        writer.write(titleRows);
        writer.autoSizeColumnAll();
        writer.close();
    }

    /**
     *
     */
    @Test
    public void testReadExcel() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("excel/".concat("special-case.xlsx"));
        InputStream fis = classPathResource.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(fis);
        List<SpecialCase> all = reader.readAll(SpecialCase.class);
        System.out.println(all);
    }

}

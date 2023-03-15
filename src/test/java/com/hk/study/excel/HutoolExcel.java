package com.hk.study.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年06月17日 11:49
 */
public class HutoolExcel {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hhmmss").withZone(ZoneId.systemDefault());

    @Test
    void name() {
        List<Integer> row1 = CollUtil.newArrayList(1, 2, 3);
        List<Integer> row2 = CollUtil.newArrayList(11, 22, 33);

        List<List<Integer>> rows = CollUtil.newArrayList(row1, row2);

        ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest".concat(dateTimeFormatter.format(Instant.now())).concat(".xlsx"));
        writer.write(rows, false);
        Cell cell = writer.getCell(1, 2);
        System.out.println(cell);
//        cell.setCellFormula("sum(A1:A2)");
        writer.close();
    }

    @Test
    void name2() {
        Integer a = null;
        Integer b = 0;
        b += a;
        System.out.println(b);
    }
}

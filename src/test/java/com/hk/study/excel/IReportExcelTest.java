package com.hk.study.excel;

import com.hk.study.ireport.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.export.*;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.testng.collections.Lists;

import java.io.File;
import java.io.InputStream;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年05月10日 15:43
 */
public class IReportExcelTest {

    @Test
    void name() throws Exception {
        Map<String, Object> map = new HashMap<>();

        JasperReport jasperReport = JasperCompileManager.compileReport("src/main/java/com/hk/study/ireport/test.jrxml");
//        JasperPrint jasperPrint = this.fill("src/main/java/com/hk/study/ireport/test.jrxml", studentList, map);
        List<Object> studentList = Lists.newArrayList();
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(studentList);//数据源
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, ds);//填充


        /* 文件输出位置 */
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss").withZone(ZoneId.systemDefault());
        File destFile = new File("F:\\student" + dateTimeFormatter.format(Instant.now()) + ".xls");

        SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
        configuration.setOnePagePerSheet(false);
        configuration.setDetectCellType(Boolean.TRUE);// 检查单元格格式
        configuration.setIgnoreCellBorder(false);
        SimpleExporterInputItem simpleExporterInputItem = new SimpleExporterInputItem(jasperPrint);
        SimpleExporterInputItem simpleExporterInputItem2 = new SimpleExporterInputItem(jasperPrint);
        List<ExporterInputItem> simpleExporterInputItems = Lists.newArrayList(simpleExporterInputItem, simpleExporterInputItem2);

        JRXlsExporter exporter = new JRXlsExporter();
//        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterInput(new SimpleExporterInput(simpleExporterInputItems));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFile));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
    }

    private JasperPrint fill(String reportUrl, List dataList, Map<String, Object> parameterMap) throws Exception {
      /*  ClassPathResource classPathResource = new ClassPathResource(reportUrl);
        InputStream inputStream = classPathResource.getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);*/
        JasperReport jasperReport = JasperCompileManager.compileReport(reportUrl);
        JRDataSource datasource = new JRMapCollectionDataSource(dataList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameterMap, datasource);

        /* 参数设置*/
        for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
            if (entry.getValue() instanceof InputStream) {
                ((InputStream) entry.getValue()).close();
            }
        }

        return jasperPrint;
    }
}

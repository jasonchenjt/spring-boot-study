package com.hk.study.file.pdf;

import com.google.common.collect.Lists;
import com.hk.study.file.IReportEntity;
import com.hk.study.file.ObjectMapUtil;
import net.sf.jasperreports.engine.JasperExportManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
 * @date 2020年10月20日 14:15
 */
public class IReportTest {

    @Autowired
    private IReportEntity reportEntity;

    @Autowired
    private ObjectMapUtil objectMapUtil;

    @Test
    public void testGenPdf() throws Exception {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM").withZone(ZoneId.systemDefault());
        /* report所需要的的参数*/
        Map<String, Object> parameterMap = new HashMap<>();
        parameterMap.put("month", dateTimeFormatter.format(Instant.now()));
        /* Ireport所在的位置*/
        String reportUrl = "C:\\Users\\1\\Desktop\\IPreport_test\\testIReport.jrxml";
//        String reportUrl = "src/main/java/org/md/erps/report/iReport/monthly/testIReport.jrxml";
        /* SQL 查询的结果,必须转换成map的方式*/
    /*    List<Map<String, String>> resultData = vesselTypeRepository.findAll().stream().map(record -> {
            record.setIsvis(null);
            return objectMapUtil.objectToMap(VMSSVesselType.class, record);
        }).collect(Collectors.toList());*/
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id", "10");
        dataMap.put("type", "Jason");

        Map<String, Object> dataMap2 = new HashMap<>();
        dataMap.put("id", "20");
        dataMap.put("type", "Jason20");
        List<Map<String, Object>> resultData = Lists.newArrayList(dataMap, dataMap2);


        reportEntity.setReportUrl(reportUrl);
        reportEntity.setParameterMap(parameterMap);
        reportEntity.setDataList(resultData);

        /* 输出文件*/
        OutputStream fileOut = new FileOutputStream("D://IReport_pdf3.pdf");

        /* 将pdf文件写入stream中*/
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(reportEntity.fill(), baos);
        reportEntity.downloadPdf(baos, fileOut);
    }
}

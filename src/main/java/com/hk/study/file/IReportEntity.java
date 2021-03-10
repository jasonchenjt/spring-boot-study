package com.hk.study.file;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年03月10日 10:14
 */
@Component
public class IReportEntity {

    /**
     * 传给IReport 的参数
     */
    private Map<String, Object> parameterMap;

    /**
     * IReport 的路径
     */
    private String reportUrl;

    /**
     * IReport显示的数据
     */
    private List dataList;

    public void setParameterMap(Map<String, Object> parameterMap) {
        this.parameterMap = parameterMap;
    }

    public void setReportUrl(String reportUrl) {
        this.reportUrl = reportUrl;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public IReportEntity(Map<String, Object> parameterMap, String reportUrl, List dataList) {
        this.parameterMap = parameterMap;
        this.reportUrl = reportUrl;
        this.dataList = dataList;
    }

    public IReportEntity() {
        super();
    }

    /**
     * @return
     * @throws Exception
     */
    public JasperPrint fill() throws Exception {
//        ClassPathResource classPathResource = new ClassPathResource(reportUrl);
//        InputStream inputStream = classPathResource.getInputStream();
//        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
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

    /**
     * @param outputStream
     * @param fieOut
     * @throws IOException
     */
    public void downloadPdf(ByteArrayOutputStream outputStream, OutputStream fieOut) throws IOException {
        try {

            PdfReader reader = new PdfReader(outputStream.toByteArray());
            int pageCount = reader.getNumberOfPages();
            Rectangle size = reader.getPageSizeWithRotation(1);
            Document document = new Document(size);
            document.setPageSize(PageSize.A4);

            PdfWriter writer = PdfWriter.getInstance(document, fieOut);
            document.open();

            PdfContentByte content = writer.getDirectContent();
            for (int i = 0; i < pageCount; i++) {
                document.newPage();
                PdfImportedPage page = writer.getImportedPage(reader, i + 1);
                content.addTemplate(page, 0, 0);
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}

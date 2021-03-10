package com.hk.study.file.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;
import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.io.*;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2021年03月04日 10:43
 */
public class PdfToHtml {
    @Test
    public void PdfToHtml() {

        String res = "请改为读取文件";
        System.out.println(res);
        byte[] bytes = Base64Utils.decodeFromString(res);

        StringWriter stringWriter = new StringWriter();

        String outputPath = "D:/test1.html";
        PDDocument document = null;
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outputPath)), "UTF-8"));) {
            //加载PDF文档
            document = PDDocument.load(bytes);
            PDFDomTree pdfDomTree = new PDFDomTree();
            pdfDomTree.writeText(document, stringWriter);
            System.out.println(stringWriter.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testCallLocalLicensingDoc() {
        String res = "请改为读取文件";
        byte[] bytes = Base64Utils.decodeFromString(res);
        String outputPath = "D:/test1.html";
        PDDocument document = null;
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outputPath)), "UTF-8"));) {
            //加载PDF文档
            document = PDDocument.load(bytes);
            PDFDomTree pdfDomTree = new PDFDomTree();
            pdfDomTree.writeText(document, out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void downloadLicensingDocFile() throws IOException {
        String res = "请改为读取文件";
        byte[] bytes = Base64Utils.decodeFromString(res);

        String outputPath = "D:/licenseDoc.pdf";
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputPath));
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}

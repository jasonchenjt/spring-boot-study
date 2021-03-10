package com.hk.study.file.word;

import cn.hutool.core.collection.CollUtil;
import fr.opensagres.poi.xwpf.converter.core.FileImageExtractor;
import fr.opensagres.poi.xwpf.converter.xhtml.Base64EmbedImgManager;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLConverter;
import fr.opensagres.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 文件描述
 *
 * @author Jason.Chen
 * @date 2020年09月03日 9:42
 */
public class WordToHtml {

    @Test
    public void Word2007ToHtml() throws IOException {
        String filepath = "D:/";
        String fileName = "test.docx";
        String htmlName = "test.html";
        final String file = filepath + fileName;
        File f = new File(file);
        if (!f.exists()) {
            System.out.println("Sorry File does not Exists!");
        } else {
            if (f.getName().endsWith(".docx") || f.getName().endsWith(".DOCX")) {

                // 1) 加载word文档生成 XWPFDocument对象
                InputStream in = new FileInputStream(f);
                XWPFDocument document = new XWPFDocument(in);

//                文件方式导出
                OutputStream outputStream = new FileOutputStream(new File(filepath.concat(htmlName)));
                //也可以使用字符数组流获取解析的内容
//                StringBuilderOutputStream outputStream = new StringBuilderOutputStream();
                XHTMLConverter.getInstance().convert(document, outputStream, null);
                String content = outputStream.toString();
                System.out.println(content);
                outputStream.close();
            } else {
                System.out.println("Enter only MS Office 2007+ files");
            }
        }
    }

    /**
     * /**
     * 2003版本word转换成html
     *
     * @throws IOException
     * @throws TransformerException
     * @throws ParserConfigurationException
     */
    @Test
    public void Word2003ToHtml() throws IOException, TransformerException, ParserConfigurationException {
        String filepath = "D:/";
        String fileName = "test.doc";
        String htmlName = "test.html";
        final String file = filepath + fileName;
        InputStream input = new FileInputStream(new File(file));
        HWPFDocument wordDocument = new HWPFDocument(input);
        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());

        //解析word文档
        wordToHtmlConverter.processDocument(wordDocument);
        Document htmlDocument = wordToHtmlConverter.getDocument();

//        File htmlFile = new File(filepath + htmlName);
//        OutputStream outStream = new FileOutputStream(htmlFile);

        //也可以使用字符数组流获取解析的内容
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        OutputStream outStream = new BufferedOutputStream(baos);

        DOMSource domSource = new DOMSource(htmlDocument);
        StreamResult streamResult = new StreamResult(outStream);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer serializer = factory.newTransformer();
        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
        serializer.setOutputProperty(OutputKeys.METHOD, "html");

        serializer.transform(domSource, streamResult);

        //也可以使用字符数组流获取解析的内容
        String content = baos.toString();
        System.out.println(content);
        baos.close();
        outStream.close();
    }


    /**
     * 以创建图片文件的方式保存word中的图片
     *
     * @throws IOException
     */
    @Test
    public void WordToHtml() throws IOException {
        String fileByte = "修改读取文件的方式";
        byte[] bytes = Base64Utils.decodeFromString(fileByte);
        InputStream in = new ByteArrayInputStream(bytes);
        XWPFDocument document = new XWPFDocument(in);

//            String outputPath = "D:/wordToHtml.html";
        OutputStream outputStream = new ByteArrayOutputStream();
        XHTMLOptions options = XHTMLOptions.create();
        // 存放图片的文件夹
        options.setExtractor(new FileImageExtractor(new File("D:/imges")));
        // html中图片的路径

        XHTMLConverter.getInstance().convert(document, outputStream, null);
        String content = outputStream.toString();
        System.out.println(content);

        outputStream.close();
        document.close();
    }


    /**
     * Word To Html,图片Base64转码
     * Base64Utils.decodeFromString:将以加密的String转化成普通的String
     */
    @Test
    void wordToHtml() {
        String fileByte = "修改读取文件的方式";
        byte[] bytes = Base64Utils.decodeFromString(fileByte);
        InputStream in = new ByteArrayInputStream(bytes);
//            OutputStream outputStream = new ByteArrayOutputStream();
        OutputStream outputStream = null;
        XHTMLOptions options = XHTMLOptions.create();
        options.setImageManager(new Base64EmbedImgManager());
        XWPFDocument document = null;
        try {
            outputStream = new FileOutputStream(new File("D:/".concat("fileName")));
            document = new XWPFDocument(in);
            XHTMLConverter.getInstance().convert(document, outputStream, options);
            String content = outputStream.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (document != null) {
                    document.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

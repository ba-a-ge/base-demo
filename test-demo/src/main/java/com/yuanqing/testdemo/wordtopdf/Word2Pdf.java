package com.yuanqing.testdemo.wordtopdf;

import java.io.*;

import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Word2Pdf {
    public static void docxToPdf(XWPFDocument document, String outUrl) throws Exception {
        OutputStream outStream = getOutFileStream(outUrl);
        PdfOptions options = PdfOptions.create();

        PdfConverter.getInstance().convert(document, outStream, options);
    }

    protected static OutputStream getOutFileStream(String outputFilePath) throws IOException {
        File outFile = new File(outputFilePath);
        try {
            outFile.getParentFile().mkdirs();
        } catch (NullPointerException e) {
        }
        outFile.createNewFile();
        FileOutputStream oStream = new FileOutputStream(outFile);
        return oStream;
    }


    public static void main(String[] args) {


        try {
            InputStream in_stream = new FileInputStream("D:\\poi\\test1.docx");// 文件流
            XWPFDocument doc = new XWPFDocument(in_stream);

            Word2Pdf.docxToPdf(doc, "D:\\poi\\temp3.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
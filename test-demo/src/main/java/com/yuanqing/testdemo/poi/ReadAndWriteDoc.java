package com.yuanqing.testdemo.poi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;


import org.apache.poi.POIXMLDocument;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.usermodel.*;

public class ReadAndWriteDoc {

    public static void readwriteWord1(String filePath, Map<String, String> map) {
        File file = new File(filePath);
        try (FileInputStream fin = new FileInputStream(file.getAbsolutePath())) {
            System.out.println("这是.doc文件，------开始解析---------");
            POIFSFileSystem pfs = new POIFSFileSystem(fin);
            HWPFDocument doc = new HWPFDocument(pfs);
            //得到文档的读取范围
            Range range = doc.getRange();


            /**
             * 获取doc文件表格内容
             */
            TableIterator tableIterator = new TableIterator(range);
            while (tableIterator.hasNext()) {
                Table table = tableIterator.next();
                for (int i = 1; i < table.numRows(); i++) {
                    TableRow row = table.getRow(i);
                    int rowSize = row.numCells();

                    for (int s = 0; s < rowSize; s++) {
                        String trim = row.getCell(s).text().trim();
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            if (trim.equals(entry.getKey())) {
                                row.getCell(s).replaceText(entry.getKey(), entry.getValue());
                            }
                        }
                    }
                }
            }

            for (Map.Entry<String, String> entry : map.entrySet()) {
                range.replaceText(entry.getKey(), entry.getValue());
            }

            FileOutputStream fos = new FileOutputStream("D:\\poi\\temp1.doc", true);
            doc.write(fos);
            fos.flush();
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    /**
     * 实现对word读取和修改操作(word2003.doc)
     */
    public static void readwriteWord10(String filePath, Map<String, String> map) {
        try {

            //读取word模板
            HWPFDocument hdt = null;

            FileInputStream fis = new FileInputStream(new File(filePath));
            try {
                hdt = new HWPFDocument(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 读取word文本内容
            Range bodyRange = hdt.getRange();
            // 替换文本内容
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bodyRange.replaceText(entry.getKey(), entry.getValue());
            }

            FileOutputStream fos = new FileOutputStream("D:\\poi\\temp1.doc", true);
            hdt.write(fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 实现对word读取和修改操作(word2007.docx)
     */
    public static void readwriteWord2(String filePath, Map<String, String> map) {
        try {
            OPCPackage pack = POIXMLDocument.openPackage(filePath);
            XWPFDocument doc = new XWPFDocument(pack);


            ////获取word文档内表格
            Iterator<XWPFTable> it = doc.getTablesIterator();
            // 设置需要读取的表格  set是设置需要读取的第几个表格，total是文件中表格的总数
            while (it.hasNext()) {
                XWPFTable table = it.next();
                //读取每一行数据
                for (int i = 0; i < table.getNumberOfRows(); i++) {
                    XWPFTableRow row = table.getRow(i);
                    //读取每一列数据
                    List<XWPFTableCell> cells = row.getTableCells();
                    for (XWPFTableCell cell : cells) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            if (cell.getText().equals(entry.getKey())) {
                                cell.removeParagraph(0);
                                cell.setText(entry.getValue());
                            }
                        }
                    }
                }
            }


            ///获取word文档文字内容
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            System.out.println(paragraphs.size());
            for (XWPFParagraph tmp : paragraphs) {
                System.out.println(tmp.getParagraphText());
                List<XWPFRun> runs = tmp.getRuns();
                for (XWPFRun aa : runs) {
                    System.out.println("XWPFRun-Text:" + aa.getText(0));
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        System.out.println(aa.getText(0));
                        if (aa.getText(0) != null && aa.getText(0).contains(entry.getKey())) {
                            aa.setText(entry.getValue(), 0);
                        }
                    }

                }
            }


            FileOutputStream fos = new FileOutputStream("D:\\poi\\temp1.docx", true);
            doc.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 读取word模板并替换变量
     */
    public static HWPFDocument readwriteWord3(String srcPath, Map<String, String> map) {
        try {
            // 读取word模板
            FileInputStream fis = new FileInputStream(new File(srcPath));
            HWPFDocument doc = new HWPFDocument(fis);
            // 读取word文本内容
            Range bodyRange = doc.getRange();
            // 替换文本内容
            for (Map.Entry<String, String> entry : map.entrySet()) {
                bodyRange.replaceText(entry.getKey(), entry.getValue());
            }
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("${userName}", "呵呵哒");
        map.put("${idCode}", "415413198701020311");


        String filePath = "D:\\poi\\test1.doc";
        readwriteWord1(filePath, map);

//        String filePath = "D:\\poi\\test1.docx";
//        readwriteWord2(filePath, map);


//        readwriteWord3(filePath, map);

    }
}
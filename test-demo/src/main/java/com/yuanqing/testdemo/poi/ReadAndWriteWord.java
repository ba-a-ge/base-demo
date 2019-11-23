package com.yuanqing.testdemo.poi;

import java.io.FileOutputStream;
import java.util.*;


import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.*;

public class ReadAndWriteWord {

    /**
     * 实现对word读取和修改操作(word2007.docx)
     */
    public static void readwriteWord(String filePath,String newFilePath, Map<String, String> map) {
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


            FileOutputStream fos = new FileOutputStream(newFilePath, true);
            doc.write(fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("${userName}", "zzc");
        map.put("${idCode}", "sdfsdf");

        String filePath = "D:\\poi\\test1.docx";
        String filePath2 = "D:\\poi\\test44.docx";
        readwriteWord(filePath,filePath2, map);

    }
}
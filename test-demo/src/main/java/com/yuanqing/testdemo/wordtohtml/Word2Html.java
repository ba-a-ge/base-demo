package com.yuanqing.testdemo.wordtohtml;

import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;

public class Word2Html {


    /**
     * @return
     */
    public int Word2007ToHtml(String tempContextUrl) {
        int rv = 0;
        try {
            String path = "D:\\poi\\test1.docx";
            //word路径
//            String wordPath = path.substring(0, path.indexOf("upload")+6);
            String wordPath = "D:\\poi\\";
            //word文件名
//            String wordName = path.substring(path.lastIndexOf(File.separator)+1,path.lastIndexOf("."));
            String wordName = "test1";
            //后缀
            String suffix = "docx";
            //生成html路径
            String htmlPath = wordPath + File.separator + System.currentTimeMillis() + "_show" + File.separator;
            //生成html文件名
            String htmlName = System.currentTimeMillis() + ".html";
            //图片路径
            String imagePath = htmlPath + "image" + File.separator;

            //判断html文件是否存在
            File htmlFile = new File(htmlPath + htmlName);

            //word文件
            File wordFile = new File(wordPath + File.separator + wordName + "." + suffix);

            // 1) 加载word文档生成 XWPFDocument对象
            InputStream in = new FileInputStream(wordFile);
            XWPFDocument document = new XWPFDocument(in);

            // 2) 解析 XHTML配置 (这里设置IURIResolver来设置图片存放的目录)
            File imgFolder = new File(imagePath);
            XHTMLOptions options = XHTMLOptions.create();
            options.setExtractor(new FileImageExtractor(imgFolder));
            //html中图片的路径 相对路径
            options.URIResolver(new BasicURIResolver("image"));
            options.setIgnoreStylesIfUnused(false);
            options.setFragment(true);

            // 3) 将 XWPFDocument转换成XHTML
            //生成html文件上级文件夹
            File folder = new File(htmlPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            OutputStream out = new FileOutputStream(htmlFile);
            XHTMLConverter.getInstance().convert(document, out, options);

            // 4) 转换为项目访问路径
            String absolutePath = htmlFile.getAbsolutePath();
//            htmlPath = tempContextUrl + absolutePath.substring(absolutePath.indexOf("upload"));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return rv;
        } catch (Exception e) {
            e.printStackTrace();
            return rv;
        }
        rv = 1;
        return rv;
    }

    public static void main(String[] args) {
        Word2Html wordToImage = new Word2Html();
        wordToImage.Word2007ToHtml("ssss");
    }
}

package com.example.firstdemo.technologystack;


import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.Sides;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PrintService3 {


    public void print(String printerName,String filePath) {


        //1.得到一个文件的输入流
        FileInputStream psStream = null;
        try {
            psStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("获取PDF流失败" + e.getMessage());
        }
        if (psStream == null) {
            return;
        }

        //这是要打印文件的格式，如果是PDF文档要设为自动识别
        DocFlavor fileFormat = DocFlavor.INPUT_STREAM.AUTOSENSE;
//        DocFlavor fileFormat = DocFlavor.INPUT_STREAM.JPEG;
        //2.得到要打印的文档类DOC
        Doc myDoc = new SimpleDoc(psStream, fileFormat, null);
        //3.生成一个打印属性设置对象
        PrintRequestAttributeSet aSet = new HashPrintRequestAttributeSet();
        aSet.add(new Copies(1));//Copies-打印份数5份
        aSet.add(MediaSizeName.ISO_A4);//A4纸张
        aSet.add(Sides.DUPLEX);//双面打印
        //4.关键一步，得到当前机器上所有已经安装的打印机
//传入的参数是文档格式跟打印属性，只有支持这个格式与属性的打印机才会被找到
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
        if (services.length > 0) {
            for (PrintService service : services
            ) {
                //5.用打印服务生成一个文档打印任务，这里用的是第一个服务
                //也可以进行筛选，services[i].getName()可以得到打印机名称，可用名称进行比较得到自己想要的打印机
                if (printerName.equals(service.getName())) {
                    DocPrintJob job = service.createPrintJob();

                    try {
                        //6.最后一步，执行打印文档任务，传入的参数是Doc文档类，与属性(5份，双面,A4)
                        job.print(myDoc, aSet);//成功后电脑会提示已有文档添加到打印队列
                    } catch (PrintException pe) {
                    }
                }

            }

        }
    }
}


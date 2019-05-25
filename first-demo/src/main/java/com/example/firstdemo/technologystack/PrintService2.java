package com.example.firstdemo.technologystack;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;

public class PrintService2 {


    public void print(String printerName, File filePath) {
        try {
            PDDocument load = PDDocument.load(filePath);


            PrinterJob job = PrinterJob.getPrinterJob();

            PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);
            if (services.length > 0) {
                for (PrintService service : services
                ) {
                    if (printerName.equals(service.getName())) {
                        job.setPrintService(service);
                        break;
                    }

                }

            }


            PageFormat pf = new PageFormat();
            pf.setOrientation(PageFormat.LANDSCAPE); // LANDSCAPE表示竖打;PORTRAIT表示横打;REVERSE_LANDSCAPE表示打印空白
            // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。

            Paper p = new Paper();
            p.setSize(101, 81); // Warranty Paper Size,A4 590, 840
            p.setImageableArea(10, 10, p.getWidth(), p.getHeight()); // Print Area

            pf.setPaper(p);

            PDFPageable pdfPageable = new PDFPageable(load);
            Printable printable = pdfPageable.getPrintable(0);

            job.setPrintable(printable, pf);
            job.setPageable(pdfPageable);

//            if (job.printDialog()) {
            job.print();
//            }

        } catch (IOException | PrinterException e) {
            e.printStackTrace();
        }
    }


}

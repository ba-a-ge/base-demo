package com.example.firstdemo.technologystack;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PrintService3Test {

    @Test
    public void test3() {
//        File filePath = new File("D:\\test.jpg");
        File filePath = new File("D:\\test4.pdf");
//        File filePath = new File("D:\\test4.pdf");


        String str = "Microsoft Print to PDF";
//        String str = "DASCOM DL-218E";
//        String str = "Lexmark Universal v2 XL";
//        String str = "Lexmark Universal v2 XL (Copy 1)";

        PrintService4 printService2 = new PrintService4();

        try {
            printService2.print(filePath, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
//        File filePath = new File("D:\\test.jpg");
//        File filePath = new File("D:\\test2.pdf");
        File filePath = new File("D:\\test5.pdf");


        String str = "Microsoft Print to PDF";
//        String str = "DASCOM DL-218E";
//        String str = "Lexmark Universal v2 XL";
//        String str = "Lexmark Universal v2 XL (Copy 1)";

        PrintService2 printService2 = new PrintService2();

        printService2.print(str, filePath);
    }

    @Test
    public void test1() {
        String filePath = "D:\\test.jpg";
//        String filePath = "D:\\test3.pdf";


        String str = "DASCOM DL-218E";
//        String str = "Lexmark Universal v2 XL";
//        String str = "Lexmark Universal v2 XL (Copy 1)";
        PrintService3 printService3 = new PrintService3();

        printService3.print(str, filePath);
    }

}
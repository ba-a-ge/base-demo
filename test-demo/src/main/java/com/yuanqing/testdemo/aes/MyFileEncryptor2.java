package com.yuanqing.testdemo.aes;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MyFileEncryptor2 {
    @SuppressWarnings("static-access")
    //文件加密的实现方法
    public static byte[] encryptFile(String fileName) {

        byte[] bytes = null;
        FileInputStream fis = null;
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        try {
            fis = new FileInputStream(fileName);


            //秘钥自动生成
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            Key key = keyGenerator.generateKey();


            byte[] keyValue = key.getEncoded();

            swapStream.write(keyValue);//记录输入的加密密码的消息摘要

            SecretKeySpec encryKey = new SecretKeySpec(keyValue, "AES");//加密秘钥

            byte[] ivValue = new byte[16];
            Random random = new Random(System.currentTimeMillis());
            random.nextBytes(ivValue);
            IvParameterSpec iv = new IvParameterSpec(ivValue);//获取系统时间作为IV

            swapStream.write("MyFileEncryptor".getBytes());//文件标识符

            swapStream.write(ivValue);    //记录IV
            Cipher cipher = Cipher.getInstance("AES/CFB/PKCS5Padding");
            cipher.init(cipher.ENCRYPT_MODE, encryKey, iv);

            CipherInputStream cis = new CipherInputStream(fis, cipher);

            byte[] buffer = new byte[1024];
            int n = 0;
            while ((n = cis.read(buffer)) != -1) {
                swapStream.write(buffer, 0, n);
            }
            bytes = swapStream.toByteArray();
            cis.close();

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IOException | InvalidKeyException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        return bytes;

    }

    @SuppressWarnings("static-access")
    //文件解密的实现代码
    /**
     *
     */
    public static void decryptedFile(byte[] bytes, String decryptedFileName) {

        try {
            FileInputStream fis = byteToFile(bytes, "test2.txt");

            FileOutputStream fos = new FileOutputStream(decryptedFileName);

            byte[] fileIdentifier = new byte[15];

            byte[] keyValue = new byte[16];

            fis.read(keyValue);//读记录的文件加密密码的消息摘要
            fis.read(fileIdentifier);

            if (new String(fileIdentifier).equals("MyFileEncryptor")) {

                SecretKeySpec key = new SecretKeySpec(keyValue, "AES");
                byte[] ivValue = new byte[16];
                fis.read(ivValue);//获取IV值
                IvParameterSpec iv = new IvParameterSpec(ivValue);
                Cipher cipher = Cipher.getInstance("AES/CFB/PKCS5Padding");
                cipher.init(cipher.DECRYPT_MODE, key, iv);
                CipherInputStream cis = new CipherInputStream(fis, cipher);


                byte[] buffer = new byte[1024];
                int n = 0;
                while ((n = cis.read(buffer)) != -1) {
                    fos.write(buffer, 0, n);
                }


                cis.close();
                fos.close();
                JOptionPane.showMessageDialog(null, "解密成功");
            } else {
                JOptionPane.showMessageDialog(null, "文件不是我加密的，爱找谁着谁去");
            }
        } catch (InvalidKeyException | HeadlessException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException | IOException e) {
            e.printStackTrace();
        }
    }


    public static FileInputStream byteToFile(byte[] bytes, String fileName) {
        File file = new File(fileName);
        FileInputStream fileInputStream = null;
        try {
            OutputStream output = new FileOutputStream(file);
            BufferedOutputStream bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.write(bytes);
            fileInputStream = new FileInputStream(file);
            file.deleteOnExit();
            return fileInputStream;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInputStream;
    }

    public static void main(String[] args) {
        try {
            byte[] bytes = MyFileEncryptor2.encryptFile("C:\\Users\\debug\\Desktop\\aes加密\\before.txt");
            MyFileEncryptor2.decryptedFile(bytes, "C:\\Users\\debug\\Desktop\\aes加密\\after2.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
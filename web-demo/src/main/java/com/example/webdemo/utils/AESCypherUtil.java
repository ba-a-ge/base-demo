package com.example.webdemo.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.spec.KeySpec;


@Component
public class AESCypherUtil {

    private static String salt = "ssshhhhhhhhhhh!!!!";

    /**
     * 加密
     *
     * @param bytes
     * @param secret 密钥
     * @return
     */
    public static byte[] encrypt(byte[] bytes, String secret) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return cipher.doFinal(bytes);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }


    /**
     * 解密
     *
     * @param bytes
     * @param secret 密钥
     * @return
     */
    public static byte[] decrypt(byte[] bytes, String secret) {
        try {
            byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secret.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
            return cipher.doFinal(bytes);

        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }


    public static byte[] readFileToBytes(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }

    /**
     * 根据byte数组，生成文件
     */
    public static void writeBytesToFile(byte[] bfile, String fileName) {
        InputStream bos = null;
        FileOutputStream fos = null;
        File file = null;

        try {
            file = new File(fileName);
            fos = new FileOutputStream(file);
            InputStream is = new ByteArrayInputStream(bfile);


            byte[] buffer = new byte[1024];
            int n = 0;
            while ((n = is.read(buffer)) != -1) {
                fos.write(buffer, 0, n);
            }

            byte[] buff = new byte[1024];
            int bytesRead;
            while (-1 != (bytesRead = is.read(buff, 0, buff.length))) {
                fos.write(buff, 0, bytesRead);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {

        System.out.println();

//        String filePath = "C:\\Users\\debug\\Desktop\\aes加密\\before.pdf";
//        String outfile1 = "C:\\Users\\debug\\Desktop\\aes加密\\do";
//        String outfile = "C:\\Users\\debug\\Desktop\\aes加密\\out.pdf";

        try {
            String secretKey = "111";
            //获取文件字节码
//            byte[] bytes = readFileToBytes(filePath);
            String str = "aaa";
            //加密
            byte[] encrypt = encrypt(str.getBytes("UTF-8"), secretKey);

            String string = new Base64().encodeToString(encrypt);
            System.out.println(string);

            byte[] byte2 = new Base64().decode(string);
            //解密
            byte[] decrypt = decrypt(byte2, secretKey);

            System.out.println("解密后转String UTF-8:" + new String(decrypt, "UTF-8"));
            //输出文件至指定位置
//            writeBytesToFile(decrypt, outfile);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }


}
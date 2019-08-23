package com.example.webdemo.controller;


import com.example.webdemo.utils.AESCypherUtil;
import com.example.webdemo.utils.JasyptUtils;
import com.example.webdemo.utils.OracleConnTest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Connection;


@Slf4j
@Controller
@RequestMapping("/client")
public class AesPageTestController {




    @RequestMapping("/encryptData")
    public String encryptPwd() {
        return "database/database";
    }

    @PostMapping("doEncrypt")
    @ResponseBody
    public boolean doEncrypt(
            @RequestParam("ip") String ip
            , @RequestParam("tableName") String tableName
            , @RequestParam("code") String code
            , @RequestParam("password") String password
    ) {
        String key = "grqj";
        Connection connection = OracleConnTest.getConnection(ip, tableName, code, password);

        if (connection != null) {
            log.info(
                    "数据库用户名密文：" + JasyptUtils.encryptPwd(key, code) + "\n\t"
                            + "数据库密码密文：" + JasyptUtils.encryptPwd(key, password) + "\n\t"
                            + "数据库ip：" + ip + "\n\t"
                            + "数据库tableName：" + tableName + "\n\t"
            );
            return true;
        } else {
            return false;
        }

    }



    ///**************************************////

    @RequestMapping("/aesTest")
    public String aesTest() {
        return "database/aesTest";
    }


    @PostMapping("aesDoEncrypt")
    @ResponseBody
    public String aesDoEncrypt(
            @RequestParam("encryptText") String encryptText
    ) {

        //加密
        String str = "未转码成功";
        try {
            byte[] encrypt = AESCypherUtil.encrypt(encryptText.getBytes("UTF-8"), "8888");
            str = new Base64().encodeToString(encrypt);
            System.out.println("aes加密:" + encryptText + "\t ---> \t" + str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    @PostMapping("aesDoDecrypt")
    @ResponseBody
    public String aesDoDecrypt(
            @RequestParam("decryptText") String decryptText
    ) {

        String str = "未转码成功";
        try {

            byte[] byte2 = new Base64().decode(decryptText);
            byte[] encrypt = AESCypherUtil.decrypt(byte2, "8888");

            str = new String(encrypt, "UTF-8");
            System.out.println("aes解密:" + decryptText + "\t ---> \t" + str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }


}

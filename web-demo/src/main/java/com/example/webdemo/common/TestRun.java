package com.example.webdemo.common;

import com.example.webdemo.utils.AESCypherUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;

@Slf4j
@Service
public class TestRun {

    private static String testStr = "1yi一壹:-+!@#$%^&*";

    @PostConstruct
    public void test() throws UnsupportedEncodingException {

        byte[] encrypt = AESCypherUtil.encrypt(testStr.getBytes("UTF-8"), "8888");
        String encryptStr = new Base64().encodeToString(encrypt);
        log.info("aes加密:" + testStr + "\t ---> \t" + encryptStr);

        byte[] byte2 = new Base64().decode(encryptStr);
        byte[] decrypt = AESCypherUtil.decrypt(byte2, "8888");

        String decryptStr = new String(decrypt, "UTF-8");
        log.info("aes解密:" + encryptStr + "\t ---> \t" + decryptStr);


    }

}

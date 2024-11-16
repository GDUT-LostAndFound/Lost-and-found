package com.example.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密工具类，用于用户密码，应该用非对称加密（不可逆）
 *
 * @author DF.
 * @date 2023-05-20 22:21:76
 */
public class EncodeUtil {

    /**
     * 加密模式
     */
    private static final Integer ENCRYPT = 1;

    /**
     * 解密模式
     */
    private static final Integer DECRYPT = 2;

    /**
     * 加密方式
     */
    private static final String DES = "DES";

    /**
     * 密钥(只能8个字节)
     */
    private static final String KEY = "ShuZi123";

    /**
     * 加密
     */
    public static String encrypt(String pwd){
        try {
            
            Cipher cipher =  init(ENCRYPT);
            if(cipher != null){
                //加密
                byte[] cipherPwd = cipher.doFinal(pwd.getBytes());
                //解码,防止乱码
                String encode = Base64.encodeBase64String(cipherPwd);
                //再进行一次MD5加密
                return DigestUtils.md5DigestAsHex(encode.getBytes());
            } else {
                return null;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 初始化加密工具
     */
    private static Cipher init(Integer mode){
        try {

            // DES/ECB/NoPadding不使用填充(必须满足字节的8倍),DES = DES/ECB/PKCS5Padding 为使用填充模式
            Cipher cipher = Cipher.getInstance(DES);
            // mode:1加密,2解密
            cipher.init(mode, new SecretKeySpec(KEY.getBytes(), DES));   //密钥，使用的盐和加密算法，如果使用的是CBC模式还要第三个参数“向量”来初始化密文
            return cipher;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

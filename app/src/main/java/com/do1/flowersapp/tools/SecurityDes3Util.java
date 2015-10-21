package com.do1.flowersapp.tools;
import java.security.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


/**
 * Copyright ? 广州市道一信息技术有限公司
 * All rights reserved.
 */
public class SecurityDes3Util {
    // 密钥  
    private final static String secretKey = "890oiujkioj@lx100$#365#$";  
    // 向量  
    private final static String iv = "01234567";  
    // 加解密统一使用的编码方式  
    private final static String encoding = "utf-8";

	/** 
     * 3DES加密 
     *  
     * @param plainText 普通文本 
     * @return 
     * @throws Exception  
     */  
    public static String encode(String plainText) throws Exception { 
        Key deskey = null;  
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");  
        deskey = keyfactory.generateSecret(spec);  
  
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
        byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));  
        return Base64.encode(encryptData);  
    } 
  
    /** 
     * 3DES解密 
     *  
     * @param encryptText 加密文本 
     * @return 
     * @throws Exception 
     */  
    public static String decode(String encryptText) throws Exception {  
        Key deskey = null;  
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());  
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");  
        deskey = keyfactory.generateSecret(spec);  
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");  
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);  
        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));  
        return new String(decryptData, encoding);  
    }

    /** 3DES加密
     * @param map
     * @return
     */
    public static Map<String, String> decode(Map<String, Object> map) {
        Map<String, String> securityMap = new HashMap<>();
        Iterator<Map.Entry<String,Object>> iterator = map.entrySet().iterator();
        StringBuilder keys = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<String,Object> entry = iterator.next();
            keys.append(entry.getValue());
            try {
                securityMap.put(entry.getKey(),encode(entry.getValue().toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        securityMap.put("digest",SecurityUtil.encryptToMD5(keys.toString()).toLowerCase());
        return securityMap;
    }
}

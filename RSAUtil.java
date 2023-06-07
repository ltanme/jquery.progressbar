package com.baidu.proactive.service.common.utils;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {

   private static final String TAG = "RSAUtilV2";

   private static String cipherMode = "RSA/ECB/PKCS1Padding";//算法/模式/补码方式

   /**
    * 公钥生成
    *
    * @param key
    * @return
    * @throws Exception
    */
   private static PublicKey generatePublicKey(byte[] key) throws Exception {
      X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return keyFactory.generatePublic(keySpec);
   }

   /**
    * 秘钥生成
    *
    * @param key
    * @return
    * @throws Exception
    */
   private static PrivateKey generatePrivateKey(byte[] key) throws Exception {
      PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return keyFactory.generatePrivate(keySpec);
   }

   /**
    * 用公钥对字符串进行加密
    *
    * @param data 原文
    */
   public static byte[] encryptWithPublicKey(byte[] data, byte[] key) throws Exception {
      Cipher cp = Cipher.getInstance(cipherMode);
      cp.init(Cipher.ENCRYPT_MODE, generatePublicKey(key));
      return cp.doFinal(data);
   }

   /**
    * 私钥解密
    *
    * @param data 待解密数据
    * @param key  密钥
    */
   public static byte[] decryptWithPrivateKey(byte[] data, byte[] key) throws Exception {
      Cipher cp = Cipher.getInstance(cipherMode);
      cp.init(Cipher.DECRYPT_MODE, generatePrivateKey(key));
      byte[] arr = cp.doFinal(data);
      return arr;
   }

}

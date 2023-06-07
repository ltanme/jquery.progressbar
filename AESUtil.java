package com.baidu.proactive.service.common.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESUtil {

   private static String cipherMode = "AES/CBC/PKCS5Padding";//算法/模式/补码方式

   /**
    * 生成秘钥
    *
    * @return
    */
   public static byte[] generateKey() {
      byte[] key = null;
      try {
         KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
         keyGenerator.init(128);
         return keyGenerator.generateKey().getEncoded();
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      }
      return key;
   }

   /**
    * 进行加密/解密
    *
    * @param data 原始数据
    * @param key  密钥
    */
   private static byte[] encrypt(byte[] data, byte[] key) {
      byte[] bytes = null;
      try {
         SecretKey secretKey = new SecretKeySpec(key, "AES");
         //AES 是加密方式、CBC 是工作模式、PKCS5Padding 填充方式
         Cipher cipher = Cipher.getInstance(cipherMode);
         cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[cipher.getBlockSize()]));
         bytes = cipher.doFinal(data);
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      } catch (NoSuchPaddingException e) {
         e.printStackTrace();
      } catch (InvalidAlgorithmParameterException e) {
         e.printStackTrace();
      } catch (InvalidKeyException e) {
         e.printStackTrace();
      } catch (BadPaddingException e) {
         e.printStackTrace();
      } catch (IllegalBlockSizeException e) {
         e.printStackTrace();
      }
      return bytes;
   }

   /**
    * 进行加密/解密
    *
    * @param data 原始数据
    * @param key  密钥
    */
   public static String encrypt(String data, byte[] key) {
      try {
         return Base64Util.encodeToString(encrypt(data.getBytes("utf-8"), key));
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      return null;
   }

   /**
    * 进行加密/解密
    *
    * @param data 原始数据
    * @param key  密钥
    */
   private static byte[] decrypt(byte[] data, byte[] key) {
      byte[] bytes = null;
      try {
         SecretKey secretKey = new SecretKeySpec(key, "AES");
         //AES 是加密方式、CBC 是工作模式、PKCS5Padding 填充方式
         Cipher cipher = Cipher.getInstance(cipherMode);
         cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(new byte[cipher.getBlockSize()]));
         bytes = cipher.doFinal(data);
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      } catch (NoSuchPaddingException e) {
         e.printStackTrace();
      } catch (InvalidAlgorithmParameterException e) {
         e.printStackTrace();
      } catch (InvalidKeyException e) {
         e.printStackTrace();
      } catch (BadPaddingException e) {
         e.printStackTrace();
      } catch (IllegalBlockSizeException e) {
         e.printStackTrace();
      }
      return bytes;
   }

   /**
    * 进行加密/解密
    *
    * @param data 原始数据
    * @param key  密钥
    */
   public static String decrypt(String data, byte[] key) {
      byte[] base64decodeData = Base64Util.decode(data);
      byte[] decryptData = decrypt(base64decodeData, key);
      try {
         return new String(decryptData, "utf-8");
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      return null;
   }
}

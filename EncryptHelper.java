package com.baidu.proactive.service.common.helper;

import java.util.HashMap;

import com.baidu.proactive.service.common.utils.AESUtil;
import com.baidu.proactive.service.common.utils.Base64Util;
import com.baidu.proactive.service.common.utils.RSAUtil;

public class EncryptHelper {

   private static final String TAG = "EncryptHelper";

   private static final String DEFAULT_PUBLIC_KEY = "my public key ";
   public static final String ENCRYPT_KEY_KEY = "key";
   public static final String ENCRYPT_CONTENT_KEY = "content";

   public static HashMap<String, String> doEncrypt(String content, String publicKey) {

      if (content == null || content.isEmpty()) {
         return null;
      }

      if (publicKey == null || publicKey.isEmpty()) {

         publicKey = DEFAULT_PUBLIC_KEY;
      }

      // 1. 生产 随机的AES密钥
      byte[] aesKey = AESUtil.generateKey();

      // 2. 使用RSA算法+RSA公钥，将AES密钥加密
      String encryptAesKey = null;
      try {
         byte[] encryptAesKeyByte = RSAUtil.encryptWithPublicKey(aesKey, Base64Util.decodeNoWrap(publicKey));
         encryptAesKey = Base64Util.encodeToString(encryptAesKeyByte);
      } catch (Exception e) {
         e.printStackTrace();
      }

      // 3. 使用AES将传输内容加密
      String encryptData = null;
      try {
         encryptData = AESUtil.encrypt(content, aesKey);
      } catch (Exception e) {
         e.printStackTrace();
      }

      HashMap<String, String> resultMap = new HashMap<>();
      resultMap.put(ENCRYPT_KEY_KEY, encryptAesKey);
      resultMap.put(ENCRYPT_CONTENT_KEY, encryptData);
      return resultMap;
   }
}

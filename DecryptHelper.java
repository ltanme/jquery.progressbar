package com.baidu.proactive.service.common.helper;

import com.baidu.proactive.service.common.utils.AESUtil;
import com.baidu.proactive.service.common.utils.Base64Util;
import com.baidu.proactive.service.common.utils.RSAUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecryptHelper {

   private static final String TAG = "DecryptHelper";

   private static final String RSA_PRIVATE_KEY = "my rsa prive key";


   public static String decrypt(String content, String encryptAesKey) {

      if (content == null || content.isEmpty()) {
         return null;
      }

      if (encryptAesKey == null || encryptAesKey.isEmpty()) {
         return null;
      }

      byte[] decryptAesKey = null;
      try {
         decryptAesKey = RSAUtil.decryptWithPrivateKey(Base64Util.decode(encryptAesKey), Base64Util.decodeNoWrap(RSA_PRIVATE_KEY));
      } catch (Exception e) {
         e.printStackTrace();
      }
      log.info(TAG + "decrypt: decryptAesKey = :{}" ,decryptAesKey);

      String decryptData = null;
      try {
         decryptData = AESUtil.decrypt(content, decryptAesKey);
      } catch (Exception e) {
         e.printStackTrace();
      }
      log.info(TAG + "decrypt: decryptData = :{}" ,decryptData);

      return decryptData;
   }
}

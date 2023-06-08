package com.coocaa.proactive.service.common.utils;

import com.loopj.android.http.Base64;

public class Base64Util {

   public static String encodeToString(byte[] input) {
      return Base64.encodeToString(input, Base64.NO_WRAP | Base64.URL_SAFE);
   }

   public static byte[] decode(String str) {
      return Base64.decode(str, Base64.URL_SAFE);
   }

   public static byte[] decodeNoWrap(String str) {
      return Base64.decode(str, Base64.NO_WRAP);
   }
}

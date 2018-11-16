package cn.mylava.seckill.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * comment:
 *
 * @author: lipengfei
 * @date: 22/08/2018
 * @company: (C) Copyright 58BTC 2018
 * @since: JDK 1.8
 * @description:
 */
public class MD5Util {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String MD5Input(String inputPass) {
        String str = ""+salt.charAt(0)+salt.charAt(1)+inputPass+salt.charAt(6)+salt.charAt(7);
        System.out.println(str);
        return md5(str);
    }

    public static String MD5Form(String formPass, String salt) {
        String str = salt.charAt(0)+salt.charAt(1)+formPass+salt.charAt(6)+salt.charAt(7);
        return md5(str);
    }

    public static String MD5DB(String inputPass,String salt) {
        return MD5Form(MD5Input(inputPass), salt);
    }

  /*   public static void main(String[] args) {

         System.out.println(MD5Input("123456"));
         System.out.println("c8210e78361a27157ac2dc309e6cabf8");
         System.out.println(MD5Form("c8210e78361a27157ac2dc309e6cabf8",salt));
         System.out.println(MD5DB("123456", salt));
    }*/
}

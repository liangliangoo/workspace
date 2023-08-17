package com.xiaoxiong.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

/**
 * @Description:
 * @Author: dingqiankun
 * @Date: 2018/7/16 下午2:40
 * @Version: V
 */
public class MD5Util {

    public static String encode(String str) {
        try {
            byte[] bytes = null;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
            bytes = md.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < bytes.length; ++i) {
                int v = bytes[i] & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } catch (GeneralSecurityException var7) {
            return "";
        } catch (UnsupportedEncodingException var8) {
            throw new RuntimeException(var8);
        }
    }


    public static String encode(byte[] data) {
        try {
            byte[] bytes = null;
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data);
            bytes = md.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < bytes.length; ++i) {
                int v = bytes[i] & 255;
                String hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } catch (GeneralSecurityException var7) {
            return "";
        }
    }

    private final static String[] strHex = {"0", "1", "2", "3", "4", "5",
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String getMD5File(byte[] data) {
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(data);
            for (int i = 0; i < b.length; i++) {
                int d = b[i];
                if (d < 0) {
                    d += 256;
                }
                int d1 = d / 16;
                int d2 = d % 16;
                sb.append(strHex[d1] + strHex[d2]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * base64加密
     *
     * @param data
     * @param key  base64 加密的key
     * @return
     */
    public static String encodeByBase64(String data, String key) {
        try {
            final Key dataKey = new SecretKeySpec(decodeBase64(key),
                    "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, dataKey);
            byte[] encryptData = cipher.doFinal(data.getBytes());
            return encodeBase64String(encryptData).replaceAll("\r",
                    "").replaceAll("\n", "");
        } catch (Exception e) {

        }
        return null;
    }

}

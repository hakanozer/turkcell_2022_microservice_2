package com.works.util;

import com.google.crypto.tink.subtle.AesGcmJce;

public class TinkEncDec {

    static String key128Bit = "!A%D*G-KaPdSgVkY";
    public static String encrypt(  String plainText, String associatedData ) {
        String stringEncrypt = "";
        try {

            AesGcmJce aesGcmJce = new AesGcmJce(key128Bit.getBytes());
            byte[] byteEncrypt = aesGcmJce.encrypt(plainText.getBytes(), associatedData.getBytes());
            stringEncrypt = new String(byteEncrypt, "ISO-8859-1");

        } catch (Exception e) {
            System.err.println("encrypt Error :" + e);
        }
        return stringEncrypt;
    }


    public static String decrypt(  String cipherText, String associatedData ) {
        String stringDecrypt = "";
        try {

            byte[] convertEncode = cipherText.getBytes("ISO-8859-1");
            AesGcmJce aesGcmJce = new AesGcmJce(key128Bit.getBytes());
            byte[] descBytes = aesGcmJce.decrypt(convertEncode, associatedData.getBytes());
            stringDecrypt = new String(descBytes);

        } catch (Exception e) {
            System.err.println("decrypt Error : " + e);
        }
        return stringDecrypt;
    }

}

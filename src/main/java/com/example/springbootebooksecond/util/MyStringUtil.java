package com.example.springbootebooksecond.util;

import java.util.Base64;

public class MyStringUtil {

    public static String bytesToBase64(byte[] bytes, String fileExtension) {
        String contentType = "image/jpeg"; // default to jpeg
        if (fileExtension.equalsIgnoreCase("png")) {
            contentType = "image/png";
        } else if (fileExtension.equalsIgnoreCase("jfif") || fileExtension.equalsIgnoreCase("jpg")) {
            contentType = "image/jfif";
        }
        return "data:" + contentType + ";base64," + Base64.getEncoder().encodeToString(bytes);
    }
}

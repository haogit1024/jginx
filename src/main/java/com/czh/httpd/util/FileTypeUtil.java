package com.czh.httpd.util;

public class FileTypeUtil {
    public static String getContentType(String fileName) {
        String[] array = fileName.split(".");
        if (array.length != 2) {
            return "download";
        }
        String ext = "." + array[1];
        if (ext.equals(".text") || ext.equals(".css")) {
            return "text";
        } else if (ext.equals(".java") || ext.equals(".html") || ext.equals(".js") || ext.equals(".php") || ext.equals(".xml") || ext.equals(".c")) {
//            return "code";
            return "text";
        } else if (ext.equals(".mp4") || ext.equals(".avi") || ext.equals(".rmvb") || ext.equals(".wmv") || ext.equals(".mov") || ext.equals(".flv")) {
            return "movie";
        } else if (ext.equals(".jpg") || ext.equals(".jpeg") || ext.equals(".png") || ext.equals(".gif") || ext.equals(".PNG")) {
            return "image";
        } else if (ext.equals(".mp3")) {
            return "audio";
        } else if (ext.equals(".xls") || ext.equals(".xlsx") || ext.equals("csv")) {
//            return "excel";
            return "download";
        } else if (ext.equals(".docx") || ext.equals(".doc")) {
//            return "word";
            return "download";
        } else if (ext.equals(".pdf")) {
//            return "pdf";
            return "download";
        } else if (ext.equals(".rar") || ext.equals(".zip") || ext.equals(".7z") || ext.equals("tar.gz")) {
//            return "zip";
            return "download";
        } else if (ext.equals(".ppt")) {
//            return "powerpoint";
            return "download";
        }
//        return ext.substring(1,ext.length());
        return "file";
    }
}

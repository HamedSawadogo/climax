package com.climax.climax.services;

import com.climax.climax.exceptions.FileNotFoundException;

public class FileManager {

    public static boolean isValidFilePath(String filePath){
       return !filePath.isEmpty()||filePath.contains(".");
    }
    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        } else {
            throw new FileNotFoundException();
        }
    }
}

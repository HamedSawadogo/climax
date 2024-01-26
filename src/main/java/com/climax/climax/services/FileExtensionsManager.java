package com.climax.climax.services;

import com.climax.climax.exceptions.FileNotFoundException;

public class FileExtensionsManager {
    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        } else {
            throw new FileNotFoundException();
        }
    }
}

package com.climax.climax.services;

import com.climax.climax.exceptions.FileNotFoundException;

public class FileManager {

    /**
     * vérifier la validité d'un fichier
     * @param filePath
     * @return
     */    
    public static boolean isValidFilePath(String filePath){
       return !filePath.isEmpty()||filePath.contains(".");
    }
    /**
     * renvoie l'extension du fichier en paramètre
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        } else {
            throw new FileNotFoundException();
        }
    }
}

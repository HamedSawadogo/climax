package com.climax.climax.services;

import com.climax.climax.exceptions.FileNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {

    /**
     * Obtenir le chemin Absolu du fichier
     * @param file
     * @return
     * @throws IOException
     */
    public static String getAbsolutePath(MultipartFile file) throws IOException {
        if(!isValidFilePath(file.toString()))throw new FileNotFoundException("mauvais fichier");
        String uploadedFileName = file.getOriginalFilename();
        Path tempDir = Files.createTempDirectory("upload-dir");
        Path tempFile = tempDir.resolve(Paths.get(uploadedFileName));
        file.transferTo(tempFile);
        return tempFile.toAbsolutePath().toString();
    }
    /**
     * vérifier la validité d'un fichier
     * @param filePath
     * @return
     */    
    public static boolean isValidFilePath(String filePath){
       return !filePath.isEmpty()&&filePath.contains(".");
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

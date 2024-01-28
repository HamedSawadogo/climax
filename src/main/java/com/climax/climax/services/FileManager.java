package com.climax.climax.services;

import com.climax.climax.exceptions.FileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

@Slf4j
public class FileManager {
    /**
     * Obtenir le chemin Absolu du fichier
     * @param file
     * @return
     * @throws IOException
     */
    public static String getAbsolutePath(MultipartFile file) throws IOException {
        try {
            String uploadedFileName = file.getOriginalFilename();
            Path tempDir = Files.createTempDirectory("upload-dir");
            Path tempFile = tempDir.resolve(Paths.get(uploadedFileName));
            file.transferTo(tempFile);
            return tempFile.toAbsolutePath().toString();
        }catch (Exception e){
            throw new NoSuchFileException("Une erreur est survenue!!");
        }
    }
    /**
     * vérifier la validité d'un fichier
     * @param filePath
     * @return
     */
    public static boolean isValidFilePath(String fileName) {
       if(fileName==null)return false;
       return  fileName.contains(".");
    }
    /**
     * renvoie l'extension du fichier en paramètre
     * @param fileName
     * @return
     */
    public static String getFileExtension(String fileName) {
        log.info("Ce fichier est valide ?: "+isValidFilePath(fileName));
        if(isValidFilePath(fileName)){
            int lastDotIndex = fileName.lastIndexOf('.');
            if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
                return fileName.substring(lastDotIndex + 1);
            } else {
                throw new FileNotFoundException();
            }
        }else{throw new FileNotFoundException("ce fichier est invalide!!!");}
    }
}

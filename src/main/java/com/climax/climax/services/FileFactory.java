package com.climax.climax.services;

import com.climax.climax.exceptions.FileNotFoundException;
import com.climax.climax.model.*;

public class FileFactory {

    /**
     * Renvoie une instance de File
     * @param filePath
     * @return File
     */
    public static FileFormat createFileReader(String filePath){
        String ext=FileManager.getFileExtension(filePath);
        return switch (ext) {
            case "xml" -> new XMLFile();
            case "json" -> new JsonFile();
            case "csv" -> new CsvFile();
            case "txt"->new TextFile();
            default -> throw new FileNotFoundException("ce type de fichier n'est pas supporté");
        };
    }
}

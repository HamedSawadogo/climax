package com.climax.climax.services;

import com.climax.climax.exceptions.FileNotFoundException;
import com.climax.climax.model.CsvFile;
import com.climax.climax.model.File;
import com.climax.climax.model.JsonFile;
import com.climax.climax.model.XMLFile;

public class FileFactory {

    /**
     * Renvoie une instance de File
     * @param filePath
     * @return File
     */
    public static File createFileReader(String filePath){
        String ext=FileManager.getFileExtension(filePath);
        return switch (ext) {
            case "xml" -> new XMLFile();
            case "json" -> new JsonFile();
            case "csv" -> new CsvFile();
            default -> throw new FileNotFoundException("ce type de fichier n'est pas supporté");
        };
    }
}

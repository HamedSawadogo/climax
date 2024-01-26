package com.climax.climax.services;

import com.climax.climax.exceptions.FileNotFoundException;
import com.climax.climax.model.CsvFile;
import com.climax.climax.model.File;
import com.climax.climax.model.JsonFile;
import com.climax.climax.model.XMLFile;

public class FileFactory {

    public static File createFileReader(String filePath){
        String ext=FileExtensionsManager.getFileExtension(filePath);
        return switch (ext) {
            case "xml" -> new XMLFile();
            case "json" -> new JsonFile();
            case "csv" -> new CsvFile();
            default -> throw new FileNotFoundException();
        };
    }
}

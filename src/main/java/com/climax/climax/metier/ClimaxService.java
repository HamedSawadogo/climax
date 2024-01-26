package com.climax.climax.metier;

import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface ClimaxService {
    public  void readFile(String filePath) throws IOException, ParseException, ParserConfigurationException, SAXException;
}

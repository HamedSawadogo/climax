package com.climax.climax.model;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public interface File {
    void readFile(String filePath) throws IOException,
            ParseException,
            ParserConfigurationException,
            SAXException;
}

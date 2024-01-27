package com.climax.climax.model;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


public abstract class File {

  public abstract  List<Employee> readFile(String filePath) throws IOException,
          ParseException, ParserConfigurationException, SAXException;
}

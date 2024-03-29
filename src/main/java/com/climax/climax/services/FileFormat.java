package com.climax.climax.services;
import com.climax.climax.model.Employee;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;

/**
 * represente l'absraction de Fichier
 */
public interface FileFormat{

  /**
   * lectire de fichier
   * @param filePath
   * @return
   * @throws IOException
   * @throws ParseException
   * @throws ParserConfigurationException
   * @throws SAXException
   */
  public  Set<Employee> readFile(String filePath) throws IOException, ParseException, ParserConfigurationException, SAXException, ClassNotFoundException;
}

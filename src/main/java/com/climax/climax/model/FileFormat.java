package com.climax.climax.model;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;

/**
 * represente l'absraction de Fichier
 */
public abstract class FileFormat{

  /**
   * lectire de fichier
   * @param filePath
   * @return
   * @throws IOException
   * @throws ParseException
   * @throws ParserConfigurationException
   * @throws SAXException
   */
  public abstract  Set<Employee> readFile(String filePath) throws IOException,
          ParseException, ParserConfigurationException, SAXException, ClassNotFoundException;
}

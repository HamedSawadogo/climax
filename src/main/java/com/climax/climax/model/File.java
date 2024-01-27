package com.climax.climax.model;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * represente l'absraction de Fichier
 */
public abstract class File {

  /**
   * lectire de fichier
   * @param filePath
   * @return
   * @throws IOException
   * @throws ParseException
   * @throws ParserConfigurationException
   * @throws SAXException
   */
  public abstract  List<Employee> readFile(String filePath) throws IOException,
          ParseException, ParserConfigurationException, SAXException;
}

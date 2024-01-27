package com.climax.climax.metier;

import com.climax.climax.model.Employee;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.Set;


public interface ClimaxService {
     Set<Employee> readFile(String filePath)
             throws IOException,
             ParseException,
             ParserConfigurationException,
             SAXException, ClassNotFoundException;
}

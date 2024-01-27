package com.climax.climax.metier;
import com.climax.climax.model.Employee;
import com.climax.climax.model.File;
import lombok.Setter;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@Setter
public class ClimaxServiceImpl implements ClimaxService {

    private File file;

    @Override
    public List<Employee> readFile(String filePath) throws IOException,
            ParseException, ParserConfigurationException,
            SAXException {
       return file.readFile(filePath);
    }
}

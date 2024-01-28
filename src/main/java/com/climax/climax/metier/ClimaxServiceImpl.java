package com.climax.climax.metier;
import com.climax.climax.model.Employee;
import com.climax.climax.model.FileFormat;
import lombok.Setter;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;

@Component
@Setter
public class ClimaxServiceImpl implements ClimaxService {

    private FileFormat fileReaderFormat;


    @Override
    public Set<Employee> readFile(String filePath) throws IOException,
            ParseException, ParserConfigurationException,
            SAXException, ClassNotFoundException {
       return fileReaderFormat.readFile(filePath);
    }
}

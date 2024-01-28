package com.climax.climax;

import com.climax.climax.metier.ClimaxServiceImpl;
import com.climax.climax.model.JsonFile;
import com.climax.climax.model.TextFile;
import com.climax.climax.model.XMLFile;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
@DisplayName("test la lectures des différents formats de fichier")
public class FileFormatTest {

    private ClimaxServiceImpl climaxService;
    private static final String BASE_URL=System.getProperty("user.dir");


    @BeforeEach
    public void initClimax(){
        this.climaxService= new ClimaxServiceImpl();
    }

    @Test
    @DisplayName("test de lecture des fichiers type xml")
    public void testReadXmlFile(){
        try {
            String filePath=BASE_URL+"/file.xml";
            this.climaxService.setFileReaderFormat(new XMLFile());
            this.climaxService.readFile(filePath);
        } catch (IOException | ParseException | ParserConfigurationException | SAXException | ClassNotFoundException e) {
            fail();
        }
    }

    @Test
    @DisplayName("test de lecture des fichiers type txt")
    public void testReadTextFile(){
        try {
            String filePath=BASE_URL+"/file.txt";
            this.climaxService.setFileReaderFormat(new TextFile());
            this.climaxService.readFile(filePath);
        } catch (IOException | ParseException | ParserConfigurationException | SAXException | ClassNotFoundException e) {
            fail();
        }
    }

}

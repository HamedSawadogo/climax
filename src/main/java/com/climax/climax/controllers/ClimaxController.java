package com.climax.climax.controllers;
import com.climax.climax.dao.EmployeeDaoImpl;
import com.climax.climax.exceptions.FileNotFoundException;
import com.climax.climax.metier.ClimaxServiceImpl;
import com.climax.climax.model.Employee;
import com.climax.climax.model.FileFormat;
import com.climax.climax.services.FileManager;
import com.climax.climax.services.FileFactory;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Controller
public class ClimaxController {

    //pour l'injection de dépendences
    @Autowired
    private EmployeeDaoImpl employeeDao;

    /**
     * 
     * @param model
     * @return
     */
    @GetMapping("/")
    public String homePage(Model model){
        return "index";
    }
    /**
     * 
     * @param file
     * @return
     * @throws IOException
     */
    public String getAbsolutePath(MultipartFile file) throws IOException {
        String uploadedFileName = file.getOriginalFilename();
        Path tempDir = Files.createTempDirectory("upload-dir");
        Path tempFile = tempDir.resolve(Paths.get(uploadedFileName));
        file.transferTo(tempFile);
        return tempFile.toAbsolutePath().toString();
    }
    /**
     * @param model
     * @param file
     * @return
     */
    @PostMapping("/files/upload")
    public String uploadFile(Model model,@RequestParam("file") MultipartFile file){
        try {
            String absoluteFilePath=getAbsolutePath(file);

            System.err.println(absoluteFilePath);
            ClimaxServiceImpl climaxService=new ClimaxServiceImpl();

            //Creation d'un fichier
            FileFormat fileFactory=FileFactory.createFileReader(absoluteFilePath);
            climaxService.setFileReaderFormat(fileFactory);

            Set<Employee>employees=climaxService.readFile(absoluteFilePath);
            employeeDao.addEmployees(employees);
            model.addAttribute("employees",employeeDao.getEmployeesList());
            model.addAttribute("moyennesSalariales",employeeDao.calculateSalaryByDjob());

            model.addAttribute("file",file.getOriginalFilename());
        } catch (IOException | ParserConfigurationException | ParseException |
                 SAXException | FileNotFoundException e) {
            model.addAttribute("error",e.getMessage());
            return "index";
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return "index";
    }
}

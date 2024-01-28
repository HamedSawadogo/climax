package com.climax.climax.controllers;
import com.climax.climax.dao.EmployeeDaoImpl;
import com.climax.climax.exceptions.FileNotFoundException;
import com.climax.climax.metier.ClimaxServiceImpl;
import com.climax.climax.model.Employee;
import com.climax.climax.services.FileFormat;
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
import java.io.IOException;
import java.util.Set;

@Slf4j
@Controller
public class ClimaxController {


    @Autowired
    private EmployeeDaoImpl employeeDao;
    @Autowired
    private ClimaxServiceImpl climaxService;
    /**
     * @param model
     * @return
     */
    @GetMapping("/")
    public String homePage(Model model){
        return "index";
    }
    /**
     * @param model
     * @param file
     * @return
     */
    @PostMapping("/files/upload")
    public String uploadFile(Model model,@RequestParam("file") MultipartFile file){
        try {
            String  absoluteFilePath = FileManager.getAbsolutePath(file);
            log.info(absoluteFilePath);
            FileFormat fileFactory=FileFactory.createFileReader(absoluteFilePath);
            log.info(fileFactory.toString());
            climaxService.setFileReaderFormat(fileFactory);

            Set<Employee>employees=climaxService.readFile(absoluteFilePath);
            employeeDao.addEmployees(employees);
            model.addAttribute("employees",employeeDao.getEmployeesList());
            model.addAttribute("moyennesSalariales",employeeDao.calculateSalaryByDjob());
            model.addAttribute("file",file.getOriginalFilename());
            model.addAttribute("error",null);
            return "index";
        } catch (Exception e) {
            //Attraper de manière polymorthique l'erreur cas il ya plus de 9 cas d'erreurs possible
            model.addAttribute("error",e.getMessage());
           return  "index";
        }
        //Creation d'un fichier
    }
}

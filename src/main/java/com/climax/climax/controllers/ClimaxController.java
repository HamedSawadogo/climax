package com.climax.climax.controllers;
import com.climax.climax.dao.EmployeeDaoImpl;
import com.climax.climax.exceptions.FileNotFoundException;
import com.climax.climax.metier.ClimaxServiceImpl;
import com.climax.climax.model.Employee;
import com.climax.climax.services.FileExtensionsManager;
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
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
public class ClimaxController {

    //pour l'injection de dépendences
    @Autowired
    private EmployeeDaoImpl employeeDao;

    @GetMapping("/")
    public String homePage(Model model){
        return "home";
    }

    @PostMapping("/files/upload")
    public String uploadFile(Model model,@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            System.err.println("Empty FIle");
            model.addAttribute("error","veillez bien entrer un fichier valide");
        }
        try {
            log.info("Nom de fichier:  "+file.getOriginalFilename());
            log.info("Format:  "+ FileExtensionsManager.getFileExtension(new File(Objects.requireNonNull(file.getOriginalFilename())).getAbsolutePath()));

            File filePath=new File(Objects.requireNonNull(file.getOriginalFilename()));
            String absoluteFilePath=filePath.getAbsolutePath();
            ClimaxServiceImpl climaxService=new ClimaxServiceImpl();
            climaxService.setFile(FileFactory.createFileReader(absoluteFilePath));
            List<Employee>employees=climaxService.readFile(absoluteFilePath);
            employeeDao.addEmployees(employees);
            model.addAttribute("employees",employeeDao.getEmployeesList());

            model.addAttribute("file",file.getOriginalFilename());
        } catch (IOException | ParserConfigurationException | ParseException |
                 SAXException | FileNotFoundException e) {
            model.addAttribute("error",e.getMessage());
            return "index";
        }
        return "index";
    }
    @GetMapping("/index")
    public  String indexPage(){
        return "index";
    }
}

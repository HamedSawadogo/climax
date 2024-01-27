package com.climax.climax.controllers;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

@Controller
public class ClimaxController {

    @GetMapping("/")
    public String homePage(Model model){
        return "home";
    }

    @PostMapping("/files/upload")
    public String uploadFile(Model model,@RequestParam("file") MultipartFile file){
        File file1=new File(file.getOriginalFilename());
        System.out.println(file1.getAbsoluteFile());
        return "index";
    }
    @GetMapping("/index")
    public  String indexPage(){
        return "index";
    }
}

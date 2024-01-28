package com.climax.climax;
import com.climax.climax.metier.ClimaxServiceImpl;
import com.climax.climax.model.Employee;
import com.climax.climax.services.FileFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClimaxApplication { public static void main(String[] args) {
        SpringApplication.run(ClimaxApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
      return args -> {
          String txtFile = "C:\\Users\\Hamed\\IdeaProjects\\climax\\file.csv";
          ClimaxServiceImpl climaxService = new ClimaxServiceImpl();
          climaxService.setFileReaderFormat(FileFactory.createFileReader(txtFile));
          climaxService.readFile(txtFile);
      };
    }

}

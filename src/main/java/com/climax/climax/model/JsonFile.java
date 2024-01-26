package com.climax.climax.model;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;

public class JsonFile implements File{

    @Override
    public void readFile(String filePath) throws IOException, ParseException {

        JSONParser parser=new JSONParser();
        Object obj = parser.parse(new FileReader(filePath));
        JSONObject jsonObject = (JSONObject)obj;

        String firstName= (String)jsonObject.get("firstName");
        String  lastName=(String)jsonObject.get("lastName");
        String djob=(String)jsonObject.get("djob");
        Double salaire=(Double) jsonObject.get("salary");
        int age=Integer.parseInt(String.valueOf(jsonObject.get("age")));

        System.out.println("Salaire:  "+salaire);
        System.out.println("Djob:  "+djob);
        System.out.println("Age: "+age);
        System.out.println("Name: " +firstName);
        System.out.println("lastName:  " +lastName);

    }
}

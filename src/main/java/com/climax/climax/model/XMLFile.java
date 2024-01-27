package com.climax.climax.model;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Slf4j
public class XMLFile extends File{

    @Override
    public List<Employee> readFile(String filePath) throws IOException, ParserConfigurationException, SAXException {

        log.info("myFilePath:   "+filePath);
        DocumentBuilderFactory dBfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dBfactory.newDocumentBuilder();

        Document document = builder.parse(new java.io.File(filePath));
        document.getDocumentElement().normalize();
        Element root = document.getDocumentElement();
        log.info(root.toString());
        NodeList nList = document.getElementsByTagName("employee");

        List<Employee>employeeList=new ArrayList<>();
        for (int i = 0; i < nList.getLength(); i++)
        {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                //Print each student's detail
                Element element = (Element) node;
                Employee employee= Employee.builder()
                         .id(element.getAttribute("id"))
                         .firstName(element.getElementsByTagName("firstName").item(0).getTextContent())
                         .lastName(element.getElementsByTagName("lastName").item(0).getTextContent())
                         .age(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()))
                        .djob(element.getElementsByTagName("djob").item(0).getTextContent())
                         .salary(Double.valueOf(element.getElementsByTagName("salary").item(0).getTextContent()))
                         .build();
                employeeList.add(employee);
            }else{
                throw new FileNotFoundException("une Erreur est survenue lors de la lecture de fichier");
            }
        }
        return employeeList;
    }
}

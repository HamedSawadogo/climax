package com.climax.climax.model;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
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

        org.w3c.dom.Element root = document.getDocumentElement();
        NodeList nList = document.getElementsByTagName("employees");

        List<Employee>employees=new ArrayList<>();
        for (int i = 0; i < nList.getLength(); i++)
        {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                Element element = (Element) node;
                Employee employee= Employee.builder()
                        .firstName(element.getElementsByTagName("firstName").item(0).getTextContent())
                        .lastName(element.getElementsByTagName("lastName").item(0).getTextContent())
                        .age(Integer.parseInt(element.getElementsByTagName("age").item(0).getTextContent()))
                        .djob(element.getElementsByTagName("djob").item(0).getTextContent())
                        .salary(Double.valueOf(element.getElementsByTagName("salary").item(0).getTextContent()))
                        .build();
                employees.add(employee);
            }
        }
        return employees;
    }
}

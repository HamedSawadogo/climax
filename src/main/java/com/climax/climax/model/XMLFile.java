package com.climax.climax.model;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;


public class XMLFile implements File{

    @Override
    public void readFile(String filePath) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dBfactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dBfactory.newDocumentBuilder();
        // Fetch XML File
        Document document = builder.parse(new java.io.File(filePath));
        document.getDocumentElement().normalize();
        //Get root node
        org.w3c.dom.Element root = document.getDocumentElement();
        System.out.println(root.getNodeName());
        //Get all students
        NodeList nList = document.getElementsByTagName("student");
        System.out.println(".................................");

        for (int i = 0; i < nList.getLength(); i++)
        {
            Node node = nList.item(i);
            System.out.println();    //Just a separator
            if (node.getNodeType() == Node.ELEMENT_NODE)
            {
                //Print each student's detail
                Element element = (Element) node;
                System.out.println("Student id : "    + element.getAttribute("id"));
                System.out.println("Name : "  + element.getElementsByTagName("Name").item(0).getTextContent());
                System.out.println("Roll No : "   + element.getElementsByTagName("id").item(0).getTextContent());
                System.out.println("Location : "    + element.getElementsByTagName("location").item(0).getTextContent());
            }
        }
    }
}

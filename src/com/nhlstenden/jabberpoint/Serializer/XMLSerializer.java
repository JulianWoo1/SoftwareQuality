package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Slide;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class XMLSerializer
{
    
    
    public void save(String path) {
        
    }
    
    public void load(String path) throws ParserConfigurationException, IOException, SAXException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(path));

        traverse(document.getDocumentElement(), new SlideLoaderVisitor());        
    }

    private void traverse(Node node, XMLVisitor visitor) {
        if(node.getNodeType() == Node.ELEMENT_NODE) {
            visitor.visit((Element) node);
        }

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            traverse(children.item(i), visitor);
        }
    }
}

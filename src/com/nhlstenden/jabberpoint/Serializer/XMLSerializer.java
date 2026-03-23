package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Slide;
import com.nhlstenden.jabberpoint.SlideItem;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;

import javax.xml.crypto.dsig.Transform;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLSerializer
{
    private String title;
    private List<Slide> slides;

    public XMLSerializer()
    {
        this.slides = new ArrayList<>();
    }

    public void addSlide(Slide slide)
    {
        this.slides.add(slide);
    }

    public void save(String path) throws ParserConfigurationException, TransformerException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element root = doc.createElement("presentation");
        doc.appendChild(root);

        Element titleElement = doc.createElement("title");
        titleElement.appendChild(doc.createTextNode(this.title));
        root.appendChild(titleElement);

        for(Slide slide : this.slides)
        {
            Element slideElement = doc.createElement("slide");

            for(SlideItem slideItem : slide.getSlideItems())
            {
                Element itemElement = doc.createElement("item");
                itemElement.setAttribute("level", String.valueOf(slideItem.getLevel()));
                itemElement.appendChild(doc.createTextNode(slideItem.getText));

                slideElement.appendChild(itemElement);
            }

            root.appendChild(slideElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(path));

        transformer.transform(source, result);
    }

    public void load(String path) throws ParserConfigurationException, IOException, SAXException
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File(path));

        traverse(document.getDocumentElement(), new SlideLoaderVisitor());
    }

    private void traverse(Node node, XMLVisitor visitor)
    {
        if (node.getNodeType() == Node.ELEMENT_NODE)
        {
            visitor.visit((Element) node);
        }

        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++)
        {
            traverse(children.item(i), visitor);
        }
    }
}

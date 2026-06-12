package com.nhlstenden.jabberpoint.Serializer;

import com.nhlstenden.jabberpoint.Presentation;
import com.nhlstenden.jabberpoint.Serializer.nodes.ItemNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.Node;
import com.nhlstenden.jabberpoint.Serializer.nodes.PresentationNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.SlideNode;
import com.nhlstenden.jabberpoint.Serializer.nodes.TitleNode;
import com.nhlstenden.jabberpoint.Slide;
import com.nhlstenden.jabberpoint.SlideItem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLSerializer implements PresentationSerializer {
  private String title;
  private List<Slide> slides;

  public XMLSerializer() {
    this.slides = new ArrayList<>();
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Slide> getSlides() {
    return this.slides;
  }

  public void setSlides(List<Slide> slides) {
    this.slides = slides;
  }

  public void addSlide(Slide slide) {
    this.slides.add(slide);
  }

  public void save(String path) throws ParserConfigurationException, TransformerException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();

    Element root = doc.createElement("presentation");
    doc.appendChild(root);

    Element titleElement = doc.createElement("showtitle");
    titleElement.appendChild(doc.createTextNode(this.title));
    root.appendChild(titleElement);

    for (Slide slide : this.slides) {
      Element slideElement = doc.createElement("slide");

      Element slideTitleElement = doc.createElement("title");
      slideTitleElement.appendChild(doc.createTextNode(slide.getTitle()));
      slideElement.appendChild(slideTitleElement);

      for (SlideItem slideItem : slide.getSlideItems()) {
        Element itemElement = doc.createElement("item");
        itemElement.setAttribute("level", String.valueOf(slideItem.getLevel()));
        itemElement.setAttribute("kind", slideItem.getKind());
        itemElement.appendChild(doc.createTextNode(slideItem.getText()));
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

  @Override
  public void loadToPresentation(String path, Presentation presentation) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(new File(path));

    Node root = parse(document.getDocumentElement());

    root.accept(new PresentationBuilderVisitor(presentation, new DefaultSlideItemFactory()));
  }

  @Override
  public void saveFromPresentation(String path, Presentation presentation) throws Exception {
    this.title = presentation.getTitle();
    this.slides.clear();
    this.slides.addAll(presentation.getSlides());
    save(path);
  }

  private Node parse(Element element) {
    String tag = element.getTagName();

    switch (tag) {
      case "presentation":
        {
          PresentationNode node = new PresentationNode();

          NodeList children = element.getChildNodes();
          for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i) instanceof Element childEl) {
              Node child = parse(childEl);

              if (child instanceof TitleNode t) {
                node.title = t.text;
              } else {
                node.children.add(child);
              }
            }
          }

          return node;
        }

      case "slide":
        {
          SlideNode node = new SlideNode();

          NodeList children = element.getChildNodes();
          for (int i = 0; i < children.getLength(); i++) {
            if (children.item(i) instanceof Element childEl) {
              Node child = parse(childEl);

              if (child instanceof TitleNode t) {
                node.title = t.text;
              } else {
                node.children.add(child);
              }
            }
          }

          return node;
        }

      case "title":
      case "showtitle":
        {
          TitleNode node = new TitleNode();
          node.text = element.getTextContent();
          return node;
        }

      case "item":
        {
          ItemNode node = new ItemNode();
          node.text = element.getTextContent();
          node.level = Integer.parseInt(element.getAttribute("level"));
          node.kind = element.getAttribute("kind");
          return node;
        }

      default:
        return null;
    }
  }
}

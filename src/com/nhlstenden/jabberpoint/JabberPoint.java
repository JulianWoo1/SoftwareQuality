package com.nhlstenden.jabberpoint;

import com.nhlstenden.jabberpoint.Serializer.XMLSerializer;

public class JabberPoint
{
    public static void main(String[] args)
    {
        Presentation presentation = Presentation.getInstance();
        new WindowFrame(presentation);

        XMLSerializer loader = new XMLSerializer();

        String file = (args.length == 0) ? "demo.xml" : args[0];

        try {
            loader.loadToPresentation(file, presentation);
        } catch (Exception e) {
            System.err.println("Load error: " + e.getMessage());
        }
    }
}
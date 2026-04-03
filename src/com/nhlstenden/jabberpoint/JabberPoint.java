package com.nhlstenden.jabberpoint;

public class JabberPoint
{
    public static void main(String[] argv)
    {
        new WindowFrame();

        if(argv.length == 0)
        {
            Presentation.getInstance().loadPresentationFromXMLFile("demo.xml");
        } else
        {
            Presentation.getInstance().loadPresentationFromXMLFile(argv[0]);
        }
    }
}

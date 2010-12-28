/*
 *
 *     Copyright (C) 2010  David Fernandes
 *
 *                         Rua da Quinta Amarela, 60
 *                         4475-663 MAIA
 *                         PORTUGAL
 *
 *                         <daugfernandes@aim.com>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package s7ucm.utilities;

import java.io.File;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

/**
 *
 * @author David Fernandes
 */
public class cXMLReader {

    public DocumentBuilderFactory  docBuilderFactory;
    public DocumentBuilder         docBuilder;
    public Document                doc;
    public Element                 rootElement;

    public cXMLReader(String fileName)
    {
        
        try 
        {
            docBuilderFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docBuilderFactory.newDocumentBuilder();
            doc = docBuilder.parse (new File(fileName));

            doc.getDocumentElement ().normalize ();
            rootElement=doc.getDocumentElement();
        }

        catch (Throwable t)
        {
            t.printStackTrace ();
        }
    }

    public Node getNode(Element parent, String elementName)
    {
        NodeList nl=parent.getChildNodes();
        for(int i=0;i<nl.getLength();i++)
        {
            if(nl.item(i).getNodeName().equals(elementName))
                return nl.item(i);
        }
           return null;
    }


    public Node getNode(Node parent, String elementName)
    {
        NodeList nl=parent.getChildNodes();
        for(int i=0;i<nl.getLength();i++)
        {
            if(nl.item(i).getNodeName().equals(elementName))
                return nl.item(i);
        }
           return null;
    }

    public String getNodeValue(Element parent, String elementName)
    {
        Node nl=this.getNode(parent, elementName);
        if(nl!=null)
            return(nl.getNodeValue());
        else
            return "";
    }

    public String getNodeValue(Node parent, String elementName)
    {
        Node nl=this.getNode(parent, elementName);
        if(nl!=null)
            return(nl.getNodeValue());
        else
            return "";
    }

}

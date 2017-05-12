package org.sbx.dao;

import org.junit.Assert;
import org.junit.Test;
import org.sbx.helpers.NodeName;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class XMLManagerTest {

    @Test
    public void getDocumentObjectNotNullTest() throws ParserConfigurationException, SAXException, IOException {
        XMLManager xmlManager = new XMLManager();

        Assert.assertNotNull(xmlManager.getDocumentObject());
    }

    @Test
    public void getDocumentObjectHasOperationTagTest() throws ParserConfigurationException, SAXException, IOException {
        XMLManager xmlManager = new XMLManager();
        Document document = xmlManager.getDocumentObject();

        Assert.assertNotNull(document.getElementsByTagName(NodeName.OPERATION_TAG));
    }
}

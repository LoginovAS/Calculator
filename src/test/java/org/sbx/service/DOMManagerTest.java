package org.sbx.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sbx.helpers.NodeName;
import org.sbx.helpers.Operation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class DOMManagerTest {

    private List<Double> expectedList;
    private Document document;

    @Before
    public void setup() throws ParserConfigurationException, SAXException, IOException {
        String xml =   "<expressions>" +
                "   <expression>" +
                "       <operation OperationType=\"SUB\">" +
                "           <arg>10</arg>" +
                "           <arg>5</arg>" +
                "       </operation>" +
                "   </expression>" +
                "   <expression>" +
                "       <operation OperationType=\"SUM\">" +
                "           <arg>10</arg>" +
                "           <arg>20</arg>" +
                "       </operation>" +
                "   </expression>" +
                "</expressions>";

        expectedList = new ArrayList<Double>() {{
            add(5.0);
            add(30.0);
        }};

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(new InputSource(new StringReader(xml)));

    }

    @Test
    public void testParseResultListNotNull() {
        DOMManager domManager = new DOMManager();

        List<Double> resultList = domManager.parse(document);

        Assert.assertNotNull(resultList);

    }

    @Test
    public void testParseResultListSize() {
        DOMManager domManager = new DOMManager();

        List<Double> resultList = domManager.parse(document);

        Assert.assertEquals(2, resultList.size());
    }

    @Test
    public void testParseResultListValues() {

        DOMManager domManager = new DOMManager();

        List<Double> resultList = domManager.parse(document);

        Assert.assertArrayEquals(expectedList.toArray(), resultList.toArray());

    }

    @Test
    public void testLeftNotNull() {
        DOMManager domManager = new DOMManager();

        NodeList nodeList = document.getElementsByTagName(NodeName.OPERATION_TAG);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            Assert.assertNotNull(domManager.left(node));
        }

    }

    @Test
    public void testRightNotNull() {
        DOMManager domManager = new DOMManager();

        NodeList nodeList = document.getElementsByTagName(NodeName.OPERATION_TAG);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            Assert.assertNotNull(domManager.right(node));
        }

    }

    @Test
    public void testGetCurrentOperationSum() {
        Assert.assertEquals(Operation.SUM, DOMManager.getCurrentOperation(Operation.SUM.name()));
    }

    @Test
    public void testGetCurrentOperationSub() {
        Assert.assertEquals(Operation.SUB, DOMManager.getCurrentOperation(Operation.SUB.name()));
    }

    @Test
    public void testGetCurrentOperationMul() {
        Assert.assertEquals(Operation.MUL, DOMManager.getCurrentOperation(Operation.MUL.name()));
    }

    @Test
    public void testGetCurrentOperationDiv() {
        Assert.assertEquals(Operation.DIV, DOMManager.getCurrentOperation(Operation.DIV.name()));
    }

    @Test
    public void testGetCurrentOperationNone() {
        Assert.assertEquals(Operation.NONE, DOMManager.getCurrentOperation("Another"));
    }
}

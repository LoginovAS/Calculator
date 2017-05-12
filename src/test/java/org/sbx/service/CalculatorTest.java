package org.sbx.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sbx.helpers.NodeName;
import org.sbx.helpers.Operation;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class CalculatorTest {

    private String sumXml;
    private String subXml;
    private String mulXml;
    private String divXml;
    private String divByZeroXml;

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;

    @Before
    public void setup() throws ParserConfigurationException{
        sumXml = "<simpleCalculator>" +
                "        <expressions>" +
                "                <expression>" +
                "                        <operation OperationType=\"SUM\">" +
                "                                <arg>10</arg>" +
                "                                <arg>5</arg>" +
                "                        </operation>" +
                "                </expression>" +
                "        </expressions>" +
                "</simpleCalculator>";

        subXml = "<simpleCalculator>" +
                "        <expressions>" +
                "                <expression>" +
                "                        <operation OperationType=\"SUB\">" +
                "                                <arg>10</arg>" +
                "                                <arg>5</arg>" +
                "                        </operation>" +
                "                </expression>" +
                "        </expressions>" +
                "</simpleCalculator>";

        mulXml = "<simpleCalculator>" +
                "        <expressions>" +
                "                <expression>" +
                "                        <operation OperationType=\"MUL\">" +
                "                                <arg>10</arg>" +
                "                                <arg>5</arg>" +
                "                        </operation>" +
                "                </expression>" +
                "        </expressions>" +
                "</simpleCalculator>";

        divXml = "<simpleCalculator>" +
                "        <expressions>" +
                "                <expression>" +
                "                        <operation OperationType=\"DIV\">" +
                "                                <arg>10</arg>" +
                "                                <arg>5</arg>" +
                "                        </operation>" +
                "                </expression>" +
                "        </expressions>" +
                "</simpleCalculator>";

        divByZeroXml = "<simpleCalculator>" +
                "        <expressions>" +
                "                <expression>" +
                "                        <operation OperationType=\"DIV\">" +
                "                                <arg>10</arg>" +
                "                                <arg>0</arg>" +
                "                        </operation>" +
                "                </expression>" +
                "        </expressions>" +
                "</simpleCalculator>";

        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();

    }

    @Test
    public void testGetResultsOnSUMOperationTest() throws ParserConfigurationException, IOException, SAXException {

        Document document = builder.parse(new InputSource(new StringReader(sumXml)));

        Calculator calculator = new Calculator();

        Node node = document.getElementsByTagName(NodeName.OPERATION_TAG).item(0);

        Assert.assertEquals((Double) 15.0, calculator.getResults(Operation.SUM, node).get(0));
    }

    @Test
    public void testGetResultsOnSUBOperationTest() throws ParserConfigurationException, IOException, SAXException {

        Document document = builder.parse(new InputSource(new StringReader(subXml)));

        Calculator calculator = new Calculator();

        Node node = document.getElementsByTagName(NodeName.OPERATION_TAG).item(0);

        Assert.assertEquals((Double) 5.0, calculator.getResults(Operation.SUB, node).get(0));
    }

    @Test
    public void testGetResultsOnMULOperationTest() throws ParserConfigurationException, IOException, SAXException {

        Document document = builder.parse(new InputSource(new StringReader(mulXml)));

        Calculator calculator = new Calculator();

        Node node = document.getElementsByTagName(NodeName.OPERATION_TAG).item(0);

        Assert.assertEquals((Double) 50.0, calculator.getResults(Operation.MUL, node).get(0));
    }

    @Test
    public void testGetResultsOnDIVOperationTest() throws ParserConfigurationException, IOException, SAXException {

        Document document = builder.parse(new InputSource(new StringReader(divXml)));

        Calculator calculator = new Calculator();

        Node node = document.getElementsByTagName(NodeName.OPERATION_TAG).item(0);

        Assert.assertEquals((Double) 2.0, calculator.getResults(Operation.DIV, node).get(0));
    }

    @Test(expected = ArithmeticException.class)
    public void testGetResultsOnDIVByZeroOperationTest() throws ParserConfigurationException, IOException, SAXException {

        Document document = builder.parse(new InputSource(new StringReader(divByZeroXml)));

        Calculator calculator = new Calculator();

        Node node = document.getElementsByTagName(NodeName.OPERATION_TAG).item(0);

        calculator.getResults(Operation.DIV, node).get(0);
    }

}

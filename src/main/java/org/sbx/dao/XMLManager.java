package org.sbx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLManager {

    private static final Logger logger = LogManager.getLogger(XMLManager.class);

    public static final String XML_SCHEMA_FACTORY = "http://www.w3.org/2001/XMLSchema";
    public static final String XML_SCHEMA = "src/main/resources/Calculator.xsd";

    private String inputFileName;

    public XMLManager(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public Document getDocumentObject() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(false);
        factory.setIgnoringElementContentWhitespace(true);

        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        return documentBuilder.parse(inputFileName);
    }

    public boolean writeResultsToXML(String outputFileName) {

        // TODO

        return false;
    }

    public boolean isXMLValid(String inputFileName) throws SAXException, IOException {

        boolean isValid = false;

        SchemaFactory factory = SchemaFactory.newInstance(XML_SCHEMA_FACTORY);
        File schemaLocation = new File(XML_SCHEMA);
        Schema schema = factory.newSchema(schemaLocation);
        Validator validator = schema.newValidator();
        Source source = new StreamSource(inputFileName);

        try {
            validator.validate(source);
            isValid = true;
        } catch (SAXException | IOException ex) {
            throw ex;
        } finally {
            if (isValid) {
                logger.info("Document {} is valid", inputFileName);
            } else {
                logger.error("Document {} is not valid", inputFileName);
            }
        }

        return isValid;
    }
}

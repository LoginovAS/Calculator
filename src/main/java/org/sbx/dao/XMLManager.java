package org.sbx.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.helpers.Config;
import org.sbx.model.ObjectFactory;
import org.sbx.model.SimpleCalculator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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
import java.util.List;

public class XMLManager {

    private static final Logger logger = LogManager.getLogger(XMLManager.class);

    private static final String XML_SCHEMA_FACTORY;
    private static final String XML_SCHEMA;

    private static final String INPUT_XML;
    private static final String OUTPUT_XML;

    static {
        INPUT_XML = Config.getProperties().get(Config.INPUT_XML);
        OUTPUT_XML = Config.getProperties().get(Config.OUTPUT_XML);
        XML_SCHEMA_FACTORY = Config.getProperties().get(Config.W3C_XML_SCHEMA_ULI);
        XML_SCHEMA = Config.getProperties().get(Config.XML_SCHEMA);
    }

    public Document getDocumentObject() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setIgnoringElementContentWhitespace(true);

        DocumentBuilder documentBuilder = factory.newDocumentBuilder();

        return documentBuilder.parse(INPUT_XML);
    }

    public static boolean writeResultsToXML(List<Double> resultsList) throws JAXBException, SAXException, IOException{

        File output = new File(OUTPUT_XML);

        ObjectFactory factory = new ObjectFactory();
        SimpleCalculator calculator = new SimpleCalculator();
        SimpleCalculator.ExpressionResults results = factory.createSimpleCalculatorExpressionResults();

        JAXBContext context = JAXBContext.newInstance(SimpleCalculator.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        for (double value: resultsList) {
            SimpleCalculator.ExpressionResults.ExpressionResult result = new SimpleCalculator.ExpressionResults.ExpressionResult();
            result.setResult(value);
            results.getExpressionResult().add(result);
        }

        calculator.setExpressionResults(results);
        marshaller.marshal(calculator, output);

        return false;
    }

    public void assertValid() throws SAXException, IOException {

        SchemaFactory factory = SchemaFactory.newInstance(XML_SCHEMA_FACTORY);

        File schemaLocation = new File(XML_SCHEMA);

        Schema schema = factory.newSchema(schemaLocation);

        Validator validator = schema.newValidator();

        Source source = new StreamSource(new File(INPUT_XML));

        validator.validate(source);

    }
}

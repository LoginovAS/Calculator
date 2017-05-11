package org.sbx.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Config {

    private static final Logger logger = LogManager.getLogger(Config.class);

    public static final String INPUT_XML = "inputXml";
    public static final String OUTPUT_XML = "outputXml";
    public static final String W3C_XML_SCHEMA_ULI = "w3cXmlSchemaUri";
    public static final String XML_SCHEMA = "xmlSchema";

    private static Map<String, String> properties;

    private static void configure() throws IOException{

        properties = new HashMap<>();

        Properties props = new Properties();
        InputStream inputStream = new FileInputStream("src/main/resources/config/config.properties");

        props.load(inputStream);

        properties.put(INPUT_XML, props.getProperty(INPUT_XML, "src/main/resources/sampleTest.xml"));
        properties.put(OUTPUT_XML, props.getProperty(OUTPUT_XML, "src/main/resources/results.xml"));
        properties.put(W3C_XML_SCHEMA_ULI, props.getProperty(W3C_XML_SCHEMA_ULI, "http://www.w3.org/2001/XMLSchema"));
        properties.put(XML_SCHEMA, props.getProperty(XML_SCHEMA, "src/main/resources/Calculator.xsd"));

    }

    public static Map<String, String> getProperties(){
        try {
            configure();
        } catch (IOException ex) {
            logger.error(ex);
        }

        return properties;
    }

}

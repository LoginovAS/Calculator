package org.sbx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.dao.XMLManager;
import org.sbx.service.DOMManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    private static final String INPUT_XML = "src/main/resources/sampleTest.xml";

    public static void main(String[] args) {

        XMLManager manager = new XMLManager(INPUT_XML);
        DOMManager processor = new DOMManager();

        try {
            if (manager.isXMLValid(INPUT_XML))
                processor.parse(manager.getDocumentObject());

        } catch (ParserConfigurationException | SAXException | IOException ex) {

            logger.error(ex);

        }

    }
}

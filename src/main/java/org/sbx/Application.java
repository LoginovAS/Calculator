package org.sbx;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.dao.XMLManager;
import org.sbx.helpers.Config;
import org.sbx.service.DOMManager;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {

        XMLManager manager = new XMLManager();
        DOMManager processor = new DOMManager();

        try {
            manager.assertValid();

            XMLManager.writeResultsToXML(processor.parse(manager.getDocumentObject()));


        } catch (JAXBException | ParserConfigurationException | SAXException | IOException ex) {

            logger.error(ex);

        }

    }
}

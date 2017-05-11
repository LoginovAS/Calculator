package org.sbx.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.dao.XMLManager;
import org.sbx.helpers.NodeName;
import org.sbx.helpers.Operation;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMManager {

    private static final Logger logger = LogManager.getLogger(DOMManager.class);

    private static final String SUM = "SUM";
    private static final String SUB = "SUB";
    private static final String MUL = "MUL";
    private static final String DIV = "DIV";

    private Calculator calculator;
    private List<Double> resultList;

    public DOMManager() {
        calculator = new Calculator();
        resultList = new ArrayList<>();
    }

    public List<Double> parse(Node node) {
        if (node.getNodeName().equals(NodeName.OPERATION_TAG)) {

            NodeList list = node.getChildNodes();
            NamedNodeMap attrs = node.getAttributes();

            Operation operation = getCurrentOperation(attrs.getNamedItem(NodeName.OPERATION_TYPE).getNodeValue());

            resultList.add(calculator.getResults(operation, node).get(0));

        } else {
            NodeList list = node.getChildNodes();
            if (list != null) {
                for (int i = 0; i < list.getLength(); i++) {
                    Node child = list.item(i);

                    if (child.getNodeType() != Node.ELEMENT_NODE) {
                        continue;
                    }

                    parse(child);
                }
            }
        }

        return resultList;
    }

    private static List<Node> getChildrenList(Node node) {
        List<Node> list = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                list.add(nodeList.item(i));
            }
        }

        return list;
    }

    public static Node left(Node node) {
        return getChildrenList(node).get(0);
    }

    public static Node right(Node node) {
        return getChildrenList(node).get(1);
    }

    public static Operation getCurrentOperation(String operation) {

        switch (operation) {
            case SUM:
                return Operation.SUM;
            case SUB:
                return Operation.SUB;
            case MUL:
                return Operation.MUL;
            case DIV:
                return Operation.DIV;
            default:
                return Operation.NONE;
        }

    }
}

package org.sbx.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.helpers.NodeName;
import org.sbx.helpers.Operation;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

import static org.sbx.service.DOMManager.left;
import static org.sbx.service.DOMManager.right;

public class Calculator {

    private static final Logger logger = LogManager.getLogger(Calculator.class);

    private double calc(Node node) {

        if (node.getChildNodes() == null) {
            return Integer.getInteger(node.getTextContent());
        }

        double value = 0;

        if (node.getNodeName().equals(NodeName.ARG_TAG)) {
            NodeList argNodes = node.getChildNodes();
            for (int i = 0; i < argNodes.getLength(); i++) {
                Node arg = argNodes.item(i);
                value = Integer.parseInt(arg.getTextContent());
            }
            return value;
        }

        if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals(NodeName.OPERATION_TAG)) {
            NamedNodeMap attrs = node.getAttributes();
            Node operationType = attrs.getNamedItem(NodeName.OPERATION_TYPE);
            Operation operation = DOMManager.getCurrentOperation(operationType.getNodeValue());
            switch (operation) {
                case SUM:
                    return calc(left(node)) + calc(right(node));
                case SUB:
                    return calc(left(node)) - calc(right(node));
                case MUL:
                    return calc(left(node)) * calc(right(node));
                case DIV:
                    double c = calc(right(node));
                    if (c != 0) {
                        return calc(left(node)) / calc(right(node));
                    } else {
                        logger.error("Division by zero found.");
                        System.exit(1);
                    }
                    break;

                default:
                    return Integer.getInteger(node.getTextContent());
            }
        }

        return Integer.getInteger(node.getTextContent());
    }

    public List<Double> getResults(Operation operation, Node node) throws ArithmeticException{

        List<Double> resultList = new ArrayList<>();

        switch (operation) {
            case SUM:
                resultList.add(calc(left(node)) + calc(right(node)));
                break;
            case SUB:
                resultList.add(calc(left(node)) - calc(right(node)));
                break;
            case MUL:
                resultList.add(calc(left(node)) * calc(right(node)));
                break;
            case DIV:
                double d = calc(right(node));
                if (d != 0) {
                    resultList.add(calc(left(node)) / d);
                } else {
                    logger.error("Division by Zero found");
                    throw new ArithmeticException();
                }

                break;
        }

        return resultList;
    }
}

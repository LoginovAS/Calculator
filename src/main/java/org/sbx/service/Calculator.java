package org.sbx.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sbx.helpers.NodeName;
import org.sbx.helpers.Operation;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import static org.sbx.service.DOMManager.left;
import static org.sbx.service.DOMManager.right;

public class Calculator {

    private static final Logger logger = LogManager.getLogger(Calculator.class);

    private float calc(Node node) {

        if (node.getChildNodes() == null) {
            return Integer.getInteger(node.getTextContent());
        }

        float value = 0;

        if (node.getNodeName() == NodeName.ARG_TAG) {
            NodeList argNodes = node.getChildNodes();
            for (int i = 0; i < argNodes.getLength(); i++) {
                Node arg = argNodes.item(i);
                value = Integer.parseInt(arg.getTextContent());
            }
            return value;
        }

        if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName() == NodeName.OPERATION_TAG) {
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
                    return calc(left(node)) / calc(right(node));
                default:
                    return Integer.getInteger(node.getTextContent());
            }
        }

        return Integer.getInteger(node.getTextContent());
    }

    public void printResult(Operation operation, Node node) {
        switch (operation) {
            case SUM:
                logger.info(calc(left(node)) + calc(right(node)));
                break;
            case SUB:
                logger.info(calc(left(node)) - calc(right(node)));
                break;
            case MUL:
                logger.info(calc(left(node)) * calc(right(node)));
                break;
            case DIV:
                logger.info(calc(left(node)) / calc(right(node)));
                break;

        }
    }
}

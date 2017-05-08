package org.sbx.service;

import org.sbx.helpers.NodeName;
import org.sbx.helpers.Operation;
import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.List;

public class DOMManager {

    private Calculator calculator;

    public DOMManager() {
        calculator = new Calculator();
    }

    public void parse(Node node) {
        if (node.getNodeName() == NodeName.OPERATION_TAG) {

            NodeList list = node.getChildNodes();
            NamedNodeMap attrs = node.getAttributes();

            Operation operation = getCurrentOperation(attrs.getNamedItem(NodeName.OPERATION_TYPE).getNodeValue());

            calculator.printResult(operation, node);
        } else {
            NodeList list = node.getChildNodes();
            if (list != null) {
                for (int i = 0; i < list.getLength(); i++) {
                    Node child = list.item(i);
                    if (child.getNodeType() != Node.ELEMENT_NODE)
                        continue;
                    parse(child);
                }
            }
        }
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

        if (operation.equals("SUM")) {
            return Operation.SUM;
        } else if (operation.equals("SUB")) {
            return Operation.SUB;
        } else if (operation.equals("MUL")) {
            return Operation.MUL;
        } else if (operation.equals("DIV")) {
            return Operation.DIV;
        } else return Operation.NONE;

    }
}

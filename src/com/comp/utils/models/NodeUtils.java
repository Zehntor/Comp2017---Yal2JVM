package com.comp.utils.models;

import vendor.Node;
import com.comp.semantic_analyser.NodeType;

import java.util.ArrayList;
import java.util.List;

import static com.comp.semantic_analyser.NodeType.FUNCTION_ID;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class NodeUtils {

    public Node getChildOfType(Node node, NodeType type) {
        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            if (NodeType.fromString(node.jjtGetChild(n).toString()) == type) {
                return node.jjtGetChild(n);
            }
        }

        return null;
    }

    public List<Node> getChildrenOfType(Node node, NodeType type) {
        List<Node> children = new ArrayList<>();

        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            if (NodeType.fromString(node.jjtGetChild(n).toString()) == type) {
                children.add(node.jjtGetChild(n));
            }
        }

        return children;
    }

    public boolean hasChildOfType(Node node, NodeType type) {
        return getChildOfType(node, type) != null;
    }

    public boolean nodeIsOfType(Node node, NodeType type) {
        return NodeType.fromString(node.toString()) == type;
    }

    /**
     * Finds and returns the function id
     * @param node
     * @return String
     */
    public String getFunctionId(Node node) {
        Node functionIdNode = getChildOfType(node, FUNCTION_ID);

        if (functionIdNode != null) {
            return functionIdNode.getValue().toString();
        }

        return null;    // Should never happen
    }
}

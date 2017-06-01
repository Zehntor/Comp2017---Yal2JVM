package com.comp.utils.models;

import vendor.Node;
import com.comp.semantic_analyser.NodeType;

import static com.comp.semantic_analyser.NodeType.FUNCTION_ID;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class NodeUtils {

    public Node getChildByType(Node node, NodeType type) {
        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            if (NodeType.fromString(node.jjtGetChild(n).toString()) == type) {
                return node.jjtGetChild(n);
            }
        }

        return null;
    }

    public boolean hasChildOfType(Node node, NodeType type) {
        return getChildByType(node, type) != null;
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
        Node functionIdNode = getChildByType(node, FUNCTION_ID);

        if (functionIdNode != null) {
            return functionIdNode.getValue().toString();
        }

        return null;    // Should never happen
    }
}

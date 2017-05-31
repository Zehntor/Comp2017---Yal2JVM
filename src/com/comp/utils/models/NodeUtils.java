package com.comp.utils.models;

import vendor.Node;
import com.comp.semantic_analyser.NodeType;

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
}

package com.comp.utils.models;

import vendor.Node;
import java.util.List;
import java.util.ArrayList;
import com.comp.semantic_analyser.NodeType;

import static com.comp.semantic_analyser.NodeType.*;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class NodeUtils {

    /**
     * Finds and returns the first child which is of the specified type
     * @param node
     * @param type
     * @return Node
     */
    public Node getChildOfType(Node node, NodeType type) {
        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            if (NodeType.fromString(node.jjtGetChild(n).toString()) == type) {
                return node.jjtGetChild(n);
            }
        }

        return null;
    }

    /**
     * Finds and returns a list of children which are of the specified type
     * @param node
     * @param type
     * @return List<Node>
     */
    public List<Node> getChildrenOfType(Node node, NodeType type) {
        List<Node> children = new ArrayList<>();

        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            if (NodeType.fromString(node.jjtGetChild(n).toString()) == type) {
                children.add(node.jjtGetChild(n));
            }
        }

        return children;
    }

    /**
     * Returns true if the node has at least one child of the specified type; false otherwise
     * @param node
     * @param type
     * @return boolean
     */
    public boolean nodeHasChildOfType(Node node, NodeType type) {
        return getChildOfType(node, type) != null;
    }

    /**
     * Returns true if the node is of the specified type; false otherwise
     * @param node
     * @param type
     * @return
     */
    public boolean nodeIsOfType(Node node, NodeType type) {
        return NodeType.fromString(node.toString()) == type;
    }

    /**
     * Returns true if the node has an ancestor of the specified type; false otherwise
     * @param node
     * @param type
     * @return
     */
    public boolean nodeHasAncestorOfType(Node node, NodeType type) {
        if (node.jjtGetParent() == null) {
            return false;
        }

        if (nodeIsOfType(node.jjtGetParent(), type)) {
            return true;
        }

        return nodeHasAncestorOfType(node.jjtGetParent(), type);
    }

    /**
     * Return the first (counting bottom up) ancestor of the specified type
     * @param node
     * @param type
     * @return Node the ancestor, or null if not found
     */
    public Node getAncestorOfType(Node node, NodeType type) {
        if (node.jjtGetParent() == null) {
            return null;
        }

        if (nodeIsOfType(node.jjtGetParent(), type)) {
            return node.jjtGetParent();
        }

        return getAncestorOfType(node.jjtGetParent(), type);
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

    /**
     * Returns true if the node is an array declaration node; false otherwise
     * @param node
     * @return
     */
    public boolean declarationNodeIsArray(Node node) {
        return nodeHasChildOfType(node, DECLARATION_ID) && (nodeHasChildOfType(node, ARRAY_SIZE) || nodeHasChildOfType(node, IS_ARRAY));
    }
}

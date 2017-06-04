package com.comp.utils.services;

import vendor.Node;
import com.comp.utils.models.NodeUtils;
import com.comp.semantic_analyser.NodeType;

import java.util.List;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class NodeUtilsService {

    /**
     * The one and only instance of this class
     */
    private static final NodeUtilsService instance = new NodeUtilsService();

    private static final NodeUtils nodeUtils = new NodeUtils();

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private NodeUtilsService() {
    }

    public static NodeUtilsService getInstance() {
        return instance;
    }

    /**
     * Finds and returns the first child which is of the specified type
     * @param node
     * @param type
     * @return Node
     */
    public Node getChildOfType(Node node, NodeType type) {
        return nodeUtils.getChildOfType(node, type);
    }

    /**
     * Finds and returns a list of children which are of the specified type
     * @param node
     * @param type
     * @return List<Node>
     */
    public List<Node> getChildrenOfType(Node node, NodeType type) {
        return nodeUtils.getChildrenOfType(node, type);
    }

    /**
     * Returns true if the node has at least one child of the specified type; false otherwise
     * @param node
     * @param type
     * @return boolean
     */
    public boolean hasChildOfType(Node node, NodeType type) {
        return nodeUtils.hasChildOfType(node, type);
    }

    /**
     * Returns true if the node is of the specified type; false otherwise
     * @param node
     * @param type
     * @return
     */
    public boolean nodeIsOfType(Node node, NodeType type) {
        return nodeUtils.nodeIsOfType(node, type);
    }

    /**
     * Finds and returns the function id
     * @param node
     * @return String
     */
    public String getFunctionId(Node node) {
        return nodeUtils.getFunctionId(node);
    }
}

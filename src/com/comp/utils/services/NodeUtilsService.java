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

    private NodeUtilsService() {
    }

    public static NodeUtilsService getInstance() {
        return instance;
    }

    public Node getChildOfType(Node node, NodeType type) {
        return nodeUtils.getChildOfType(node, type);
    }

    public List<Node> getChildrenOfType(Node node, NodeType type) {
        return nodeUtils.getChildrenOfType(node, type);
    }

    public boolean hasChildOfType(Node node, NodeType type) {
        return nodeUtils.hasChildOfType(node, type);
    }

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

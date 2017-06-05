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
     * Finds and returns the function id
     * @param node
     * @return String
     */
    public String getFunctionId(Node node) {
        return nodeUtils.getFunctionId(node);
    }

    /**
     * Returns true if the node is an array declaration node; false otherwise
     * @param node
     * @return
     */
    public boolean declarationNodeIsArray(Node node) {
        return nodeUtils.declarationNodeIsArray(node);
    }
}

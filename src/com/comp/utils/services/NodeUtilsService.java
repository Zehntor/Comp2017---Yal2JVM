package com.comp.utils.services;

import vendor.Node;
import com.comp.utils.models.NodeUtils;
import com.comp.semantic_analyser.NodeType;

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

    public Node getChildByType(Node node, NodeType type) {
        return nodeUtils.getChildByType(node, type);
    }
}

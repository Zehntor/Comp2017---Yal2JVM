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
     * Finds and returns the function id
     * @param node
     * @return String
     */
    public String getFunctionId(Node node) {
        Node functionIdNode = node.getChildOfType(FUNCTION_ID);

        if (functionIdNode != null) {
            return functionIdNode.getValueToString();
        }

        return null;    // Should never happen
    }

    /**
     * Returns true if the node is an array declaration node; false otherwise
     * @param node
     * @return
     */
    public boolean declarationNodeIsArray(Node node) {
        return node.hasChildOfType(DECLARATION_ID) && (node.hasChildOfType(ARRAY_SIZE) || node.hasChildOfType(IS_ARRAY));
    }
}

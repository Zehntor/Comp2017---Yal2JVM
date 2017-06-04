package com.comp.semantic_analyser.analisers;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.MODULE_ID;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class ModuleAnaliser extends Analiser {

    public void analise(Node node) {
        if (symbolTableStack == null) {
            throw new RuntimeException(MISSING_SYMBOL_TABLE_STACK);
        }

        symbolTableStack.peek().setId(getModuleId(node));
    }

    /**
     * Finds and returns the function id
     * @param node
     * @return String
     */
    private String getModuleId(Node node) {
        Node moduleIdNode = NodeUtilsService.getInstance().getChildOfType(node, MODULE_ID);

        if (moduleIdNode != null) {
            return moduleIdNode.getValue().toString();
        }

        return null;    // Should never happen
    }
}

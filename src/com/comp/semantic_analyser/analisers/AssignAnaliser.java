package com.comp.semantic_analyser.analisers;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.LHS;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class AssignAnaliser extends Analiser {

    public void analise(Node node) {
        if (symbolTableStack == null) {
            throw new RuntimeException(MISSING_SYMBOL_TABLE_STACK);
        }

        Node lhsNode = NodeUtilsService.getInstance().getChildByType(node, LHS);
    }
}

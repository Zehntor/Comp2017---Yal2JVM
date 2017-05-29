package com.comp.semantic_analyser.analisers;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;
import com.comp.semantic_analyser.variables.IntegerVariable;

import static com.comp.semantic_analyser.NodeType.LHS;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class AssignAnaliser extends Analiser {

    public void analise(Node node) {
        if (symbolTableStack == null) {
            throw new RuntimeException(MISSING_SYMBOL_TABLE_STACK);
        }

        // TODO
        Node lhsNode = NodeUtilsService.getInstance().getChildByType(node, LHS);
        String variableName = lhsNode.getValue().toString();
        boolean variableExists = symbolTableStack.peek().findVariable(variableName) != null;

        if (!variableExists) {
            symbolTableStack.peek().addVariable(new IntegerVariable(variableName));
        }
    }
}

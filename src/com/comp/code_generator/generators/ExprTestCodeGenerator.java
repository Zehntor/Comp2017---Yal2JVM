package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.LHS;
import static com.comp.semantic_analyser.NodeType.RHS;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class ExprTestCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        Node
            lhsNode = NodeUtilsService.getInstance().getChildOfType(node, LHS),
            rhsNode = NodeUtilsService.getInstance().getChildOfType(node, RHS);

        addLhs(node);
        addRhs(node);

        return code.toString();
    }

    private void addLhs(Node node) {
        String varId = node.getValue().toString();

        if (node.hasChildren()) {

        }
    }

    private void addRhs(Node node) {

    }

}

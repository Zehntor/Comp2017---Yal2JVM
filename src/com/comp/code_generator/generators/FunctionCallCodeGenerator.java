package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;
import com.comp.utils.services.JasminUtilsService;
import com.comp.semantic_analyser.SemanticAnaliser;

import static com.comp.semantic_analyser.NodeType.*;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 *
 * Generates code for function calls
 */
public final class FunctionCallCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        Node parentNode = node.jjtGetParent();
        if (NodeUtilsService.getInstance().nodeHasChildOfType(parentNode, CALL_ID_2)) {
            addExternalCall(node);
        } else {
            addInternalCall(node);
        }

        return code.toString();
    }

    /**
     * Adds an external call
     * @param node CALL_ID node
     */
    private void addExternalCall(Node node) {
        Node
            stmtAncestorNode = NodeUtilsService.getInstance().getAncestorOfType(node, STMT),
            callId2Node      = NodeUtilsService.getInstance().getChildOfType(stmtAncestorNode, CALL_ID_2);
        String jasminArgs     = JasminUtilsService.getInstance().getJasminArgListCallFromNode(stmtAncestorNode);

        code.add(String.format("    invokestatic %s/%s(%s)V",
            node.getValue(),
            callId2Node.getValue(),
            jasminArgs
        ));
    }

    /**
     * TODO: args
     * Adds an internal call
     * @param node FUNCTION_CALL node
     */
    private void addInternalCall(Node node) {
        String
            moduleId   = SemanticAnaliser.getInstance().getModuleId(),
            functionId = node.getValue().toString(),
            jasminArgs = JasminUtilsService.getInstance().getJasminArgListFromSymbolTable(
                SemanticAnaliser.getInstance().getSymbolTableTree(),
                functionId
            ),
            jasminReturn = JasminUtilsService.getInstance().getJasminReturnTypeFromSymbolTable(
                SemanticAnaliser.getInstance().getSymbolTableTree(),
                functionId
            );

        code.add(String.format("    invokestatic %s/%s(%s)%s", moduleId, functionId, jasminArgs, jasminReturn));
    }
}

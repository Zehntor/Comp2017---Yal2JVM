package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;
import com.comp.utils.services.JasminUtilsService;
import com.comp.semantic_analyser.SemanticAnaliser;
import com.comp.semantic_analyser.symbol_tables.SymbolTableTree;
import com.comp.semantic_analyser.symbol_tables.SymbolTableType;
import com.comp.semantic_analyser.symbol_tables.FunctionSymbolTable;

import static com.comp.semantic_analyser.NodeType.*;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 *
 * Generates code for function calls
 */
public final class FunctionCallCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        if (NodeUtilsService.getInstance().nodeIsOfType(node, CALL_ID)) {
            addExternalCall(node);
        } else if (NodeUtilsService.getInstance().nodeIsOfType(node, FUNCTION_CALL)) {
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
     * Adds an internal call
     * @param node FUNCTION_CALL node
     */
    private void addInternalCall(Node node) {
        String
            moduleId   = SemanticAnaliser.getInstance().getModuleId(),
            functionId = NodeUtilsService.getInstance().getChildOfType(node.jjtGetParent(), ID).getValue().toString(),
            jasminArgs = JasminUtilsService.getInstance().getJasminArgListFromSymbolTable(
                SemanticAnaliser.getInstance().getSymbolTableTree(),
                functionId
            ),
            jasminReturn = JasminUtilsService.getInstance().getJasminReturnTypeFromSymbolTable(
                SemanticAnaliser.getInstance().getSymbolTableTree(),
                functionId
            );

        Node functionAncestorNode = NodeUtilsService.getInstance().getAncestorOfType(node, FUNCTION);

        code.add(String.format("    invokestatic %s/%s(%s)%s", moduleId, functionId, jasminArgs, jasminReturn));
    }
}

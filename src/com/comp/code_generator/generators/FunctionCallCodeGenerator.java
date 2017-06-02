package com.comp.code_generator.generators;

import com.comp.semantic_analyser.SemanticAnaliser;
import com.comp.utils.services.JasminUtilsService;
import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.CALL_ID;
import static com.comp.semantic_analyser.NodeType.FUNCTION_CALL;
import static com.comp.semantic_analyser.NodeType.ID;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
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
     * Adds an external call (io.println)
     * @param node CALL_ID node
     */
    private void addExternalCall(Node node) {
        code.add("    invokestatic io/println(I)V");    // TODO: get the args

    }

    /**
     * Adds an internal call
     * @param node FUNCTION_CALL node
     */
    private void addInternalCall(Node node) {
        String
            moduleName = SemanticAnaliser.getInstance().getModuleName(),
            functionId = NodeUtilsService.getInstance().getChildByType(node.jjtGetParent(), ID).getValue().toString(),
            jasminArgs = JasminUtilsService.getInstance().getJasminArgListFromSymbolTable(
                SemanticAnaliser.getInstance().getSymbolTableTree(),
                functionId
            ),
            jasminReturn = JasminUtilsService.getInstance().getJasminReturnTypeFromSymbolTable(
                SemanticAnaliser.getInstance().getSymbolTableTree(),
                functionId
            );

        code.add(String.format("    invokestatic %s/%s(%s)%s", moduleName, functionId, jasminArgs, jasminReturn));
    }
}

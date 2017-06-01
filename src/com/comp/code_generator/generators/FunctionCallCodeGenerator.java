package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.CALL_ID;
import static com.comp.semantic_analyser.NodeType.FUNCTION_CALL;

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

    private void addExternalCall(Node node) {
        code.add("    invokestatic io/println(I)V");    // TODO: get the args

    }

    private void addInternalCall(Node node) {

    }


}

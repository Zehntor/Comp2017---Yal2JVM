package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.RETURN_ID;
import static com.comp.semantic_analyser.NodeType.FUNCTION_ID;
import static com.comp.semantic_analyser.NodeType.RETURN_IS_ARRAY;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class FunctionCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        Node functionIdNode = NodeUtilsService.getInstance().getChildByType(node, FUNCTION_ID);
        if (functionIdNode == null) {
            functionIdNode = NodeUtilsService.getInstance().getChildByType(node, RETURN_ID);
        }
        String functionId = functionIdNode.getValue().toString();

        String returnType = getReturnType(node);

        addHeader(functionId);
        // TODO: fill in the function

        addFooter();

        return code.toString();
    }

    private String getReturnType(Node node) {
        Node
            returnId      = NodeUtilsService.getInstance().getChildByType(node, RETURN_ID),
            returnIsArray = NodeUtilsService.getInstance().getChildByType(node, RETURN_IS_ARRAY);

        if (returnId == null) {
            return "V";
        }

        if (returnIsArray != null) {
            return "[I";
        }

        return "I";
    }

    private void addHeader(String functionId) {
        code.add("");
        code.add(String.format(".method public static %s", functionId));
    }

    private void addFooter() {
        code.add(".end method");
    }
}

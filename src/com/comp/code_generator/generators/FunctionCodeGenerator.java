package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.RETURN_ID;
import static com.comp.semantic_analyser.NodeType.FUNCTION_NAME;
import static com.comp.semantic_analyser.NodeType.RETURN_IS_ARRAY;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class FunctionCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        Node functionNameNode = NodeUtilsService.getInstance().getChildByType(node, FUNCTION_NAME);
        if (functionNameNode == null) {
            functionNameNode = NodeUtilsService.getInstance().getChildByType(node, RETURN_ID);
        }
        String functionName = functionNameNode.getValue().toString();

        String returnType = getReturnType(node);

        addHeader(functionName);
        // TODO: fill in the function

        addFooter();

        return code.toString();
    }

    private String getReturnType(Node node) {
        Node a = NodeUtilsService.getInstance().getChildByType(node, RETURN_IS_ARRAY);
        return "";
    }

    private void addHeader(String functionName) {
        code.add("");
        code.add(String.format(".method public static %s", functionName));
    }

    private void addFooter() {
        code.add(".end method");
    }
}

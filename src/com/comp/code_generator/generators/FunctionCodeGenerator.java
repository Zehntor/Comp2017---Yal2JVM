package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.RETURN_ID;
import static com.comp.semantic_analyser.NodeType.FUNCTION_NAME;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class FunctionCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        Node functionNameNode = NodeUtilsService.getInstance().getChildByType(node, FUNCTION_NAME);
        if (functionNameNode == null) {
            functionNameNode = NodeUtilsService.getInstance().getChildByType(node, RETURN_ID);
        }
        String functionName = functionNameNode.getValue().toString();

        return code.toString();
    }
}

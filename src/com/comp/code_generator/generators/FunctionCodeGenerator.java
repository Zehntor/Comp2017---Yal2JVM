package com.comp.code_generator.generators;

import com.comp.utils.services.NodeUtilsService;
import vendor.Node;

import static com.comp.semantic_analyser.NodeType.FUNCTION_NAME;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class FunctionCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        Node functionNameNode = NodeUtilsService.getInstance().getChildByType(node, FUNCTION_NAME);
        String functionName   = functionNameNode.getValue().toString();

        return code.toString();
    }
}

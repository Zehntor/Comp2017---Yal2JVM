package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.semantic_analyser.NodeType;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 *
 * Generates code for statements
 */
public class StatementCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            Node childNode = node.jjtGetChild(n);
            NodeType nodeType = NodeType.fromString(childNode.toString());
            switch (nodeType) {
                case ASSIGN:
                    break;
                case WHILE:
                    break;
                case IF:
                    break;
            }
        }

        return code.toString();
    }
}

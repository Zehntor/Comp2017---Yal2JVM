package com.comp.code_generator.generators;

import vendor.Node;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class StatementListCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        CodeGenerator generator = CodeGeneratorFactory.getInstance().createGenerator(CodeGeneratorType.STMT);

        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            code.add(generator.reset().generate(node.jjtGetChild(n)));
        }

        return code.toString();
    }
}

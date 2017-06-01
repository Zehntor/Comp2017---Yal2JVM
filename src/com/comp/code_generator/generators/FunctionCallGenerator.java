package com.comp.code_generator.generators;

import vendor.Node;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class FunctionCallGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        code.add("    invokestatic io/println(I)V");    // TODO: get the args
        return code.toString();
    }
}

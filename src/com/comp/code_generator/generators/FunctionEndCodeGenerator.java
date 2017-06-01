package com.comp.code_generator.generators;

import vendor.Node;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class FunctionEndCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        code.add(".end method");
        return code.toString();
    }
}

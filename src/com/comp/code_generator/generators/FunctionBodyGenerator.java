package com.comp.code_generator.generators;

import vendor.Node;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class FunctionBodyGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        code.add("    ; I am the body");

        addLimits();

        return code.toString();
    }

    private void addLimits() {
        code
            .add(String.format("    .limit stack %s", 0))
            .add(String.format("    .limit locals %s", 0));
    }
}

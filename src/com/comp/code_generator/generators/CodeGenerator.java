package com.comp.code_generator.generators;

import vendor.Node;
import java.util.StringJoiner;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public abstract class CodeGenerator {

    /**
     * Holds the generated code
     * @var StringJoiner
     */
    protected StringJoiner code = new StringJoiner("\n");

    /**
     * Generates the code
     * @param node
     * @return
     */
    public abstract String generate(Node node);

    /**
     * Resets (empties) the code
     * @return CodeGenerator, for a fluent interface
     */
    protected CodeGenerator reset() {
        code = new StringJoiner("\n");
        return this;
    }
}

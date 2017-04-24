package com.comp.code_generator.generators;

import vendor.Node;

import java.util.StringJoiner;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public abstract class CodeGenerator {

    protected final StringJoiner code = new StringJoiner("\n");

    public abstract String generate(Node node);
}

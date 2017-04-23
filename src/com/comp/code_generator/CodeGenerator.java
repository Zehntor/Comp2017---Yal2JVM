package com.comp.code_generator;

import vendor.Node;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class CodeGenerator {

    /**
     * The one and only instance of this class
     */
    private static final CodeGenerator instance = new CodeGenerator();

    private final List<String> errors = new ArrayList<>();

    private CodeGenerator() {
    }

    public static CodeGenerator getInstance() {
        return instance;
    }

    public void generateCode(Node root) {
        NodeVisitor nodeVisitor = new NodeVisitor();
        root.accept(nodeVisitor);
        errors.addAll(nodeVisitor.getErrors());
        if (!hasErrors()) {
            writeCode("", nodeVisitor.getCode());
        }
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    private void writeCode(String filename, String code) {

    }
}

package com.comp.code_generator;

import vendor.Node;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.BufferedWriter;

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

    public void generateCode(Node root, String outputFilename) {
        NodeVisitor nodeVisitor = new NodeVisitor();
        root.accept(nodeVisitor);
        errors.addAll(nodeVisitor.getErrors());
        if (!hasErrors()) {
            writeCode(outputFilename, nodeVisitor.getCode());
        }
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    private void writeCode(String filename, String code) {
        try {
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename))) {
                writer.write(code);
            }
        } catch (IOException e) {
            errors.add(e.getMessage());
        }
    }
}

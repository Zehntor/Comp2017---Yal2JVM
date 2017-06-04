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

    private int labelCounter = 0;

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private CodeGenerator() {
    }

    /**
     * Returns the one and only instance of this class
     * @return CodeGenerator
     */
    public static CodeGenerator getInstance() {
        return instance;
    }

    /**
     * Generates and writes code
     * @param root
     * @param outputFilename
     */
    public void generateCode(Node root, String outputFilename) {
        NodeVisitor nodeVisitor = new NodeVisitor();
        root.accept(nodeVisitor);
        errors.addAll(nodeVisitor.getErrors());
        if (!hasErrors()) {
            writeCode(outputFilename, nodeVisitor.getCode());
        }
    }

    /**
     * Returns true if there are errors; false otherwise
     * @return
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    /**
     * Returns the error list
     * @return
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Returns the value of the label counter
     * @return int
     */
    public int getLabelCounter() {
        return labelCounter;
    }

    /**
     * Increments the label counter
     * @return
     */
    public CodeGenerator incrementLabelCounter() {
        labelCounter++;
        return this;
    }

    /**
     * Writes the generated code to file
     * @param outputFilename
     * @param code
     */
    private void writeCode(String outputFilename, String code) {
        try {
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFilename))) {
                writer.write(code);
                System.out.println(String.format("    Generated: %s", outputFilename));
            }
        } catch (IOException e) {
            errors.add(e.getMessage());
        }
    }
}

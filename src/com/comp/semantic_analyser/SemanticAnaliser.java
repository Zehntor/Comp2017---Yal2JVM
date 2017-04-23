package com.comp.semantic_analyser;

import vendor.Node;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class SemanticAnaliser {

    /**
     * The one and only instance of this class
     */
    private static final SemanticAnaliser instance = new SemanticAnaliser();

    private final List<String> errors = new ArrayList<>();

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private SemanticAnaliser() {
    }

    /**
     * Returns the one and only instance of this class
     * @return
     */
    public static SemanticAnaliser getInstance() {
        return instance;
    }

    /**
     * Walks the tree and builds the symbol tables
     * @param root
     */
    public void analise(Node root) {
        NodeVisitor nodeVisitor = new NodeVisitor();
        root.accept(nodeVisitor);
        errors.addAll(nodeVisitor.getErrors());
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }
}
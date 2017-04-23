package com.comp.code_generator;

import com.comp.common.Visitor;
import com.comp.semantic_analyser.NodeType;
import com.comp.semantic_analyser.analisers.*;
import com.comp.semantic_analyser.symbol_tables.*;
import vendor.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class NodeVisitor implements Visitor, Generator {

    private final List<String> errors = new ArrayList<>();
    private final StringJoiner codeStringJoiner = new StringJoiner("\n");

    public void visit(Node node) {
        System.out.println(String.format("Visiting node %s at (%s, %s) with value = %s",
            node,
            node.getLine(),
            node.getColumn(),
            node.getValue()
        ));

        NodeType nodeType = NodeType.fromString(node.toString());

        if (nodeType != null) {
            switch (nodeType) {
                case MODULE:
                    break;
                case FUNCTION:
                    break;
                case IF:
                case ELSE:
                case WHILE:
                    break;
                case MODULE_END:
                    break;
                case FUNCTION_END:
                    break;
                case IF_END:
                case ELSE_END:
                case WHILE_END:
                    break;
                case ASSIGN:
                    break;
            }
        }
    }

    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String getCode() {
        return codeStringJoiner.toString();
    }

    /**
     * Processes a module node
     * @param node
     */
    private void processModuleNode(Node node) {
    }

    /**
     * Processes a function node
     * @param node
     */
    private void processFunctionNode(Node node) {
    }
}

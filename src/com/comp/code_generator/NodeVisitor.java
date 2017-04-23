package com.comp.code_generator;

import vendor.Node;
import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import com.comp.common.Visitor;
import com.comp.semantic_analyser.NodeType;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.MODULE_ID;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class NodeVisitor implements Visitor, Generator {

    private final List<String> errors = new ArrayList<>();
    private final StringJoiner code   = new StringJoiner("\n");
    private String moduleName;

    public void visit(Node node) {
        NodeType nodeType = NodeType.fromString(node.toString());

        if (nodeType != null) {
            switch (nodeType) {
                case MODULE:
                    processModuleNode(node);
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append(code.toString())
            .append("\n");

        return stringBuilder.toString();
    }

    @Override
    public String getModuleName() {
        return moduleName;
    }

    /**
     * Processes a module node
     * @param node
     */
    private void processModuleNode(Node node) {
        Node moduleIdNode = NodeUtilsService.getInstance().getChildByType(node, MODULE_ID);
        moduleName = moduleIdNode.getValue().toString();
        code.add(String.format(".class public %s", moduleName));
        code.add(".super java/lang/Object");
    }

    /**
     * Processes a function node
     * @param node
     */
    private void processFunctionNode(Node node) {
    }
}

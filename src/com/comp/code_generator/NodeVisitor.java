package com.comp.code_generator;

import vendor.Node;
import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import com.comp.common.Visitor;
import com.comp.semantic_analyser.NodeType;
import com.comp.code_generator.generators.CodeGenerator;
import com.comp.code_generator.generators.CodeGeneratorFactory;

import static com.comp.code_generator.generators.CodeGeneratorType.*;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class NodeVisitor implements Visitor, Generator {

    private final List<String> errors = new ArrayList<>();
    private final StringJoiner code   = new StringJoiner("\n");

    /**
     * Visits a node
     * @param node
     */
    public void visit(Node node) {
        NodeType nodeType = NodeType.fromString(node.toString());

        CodeGenerator generator;

        if (nodeType != null) {
            switch (nodeType) {
                case MODULE:
                    generator = CodeGeneratorFactory.getInstance().createGenerator(MODULE);
                    code.add(generator.generate(node));
                    break;
                case FUNCTION:
                    generator = CodeGeneratorFactory.getInstance().createGenerator(FUNCTION);
                    code.add(generator.generate(node));
                    break;
                case IF:
                case ELSE:
                case WHILE:
                    break;
                case MODULE_END:
                    break;
                case FUNCTION_END:
                    generator = CodeGeneratorFactory.getInstance().createGenerator(FUNCTION_END);
                    code.add(generator.generate(node));
                    break;
                case IF_END:
                case ELSE_END:
                case WHILE_END:
                    break;
                case ASSIGN:
                    break;
                case FUNCTION_CALL:
                case CALL_ID:
                    generator = CodeGeneratorFactory.getInstance().createGenerator(FUNCTION_CALL);
                    code.add(generator.generate(node));
                    break;
            }
        }
    }

    /**
     * Getter for errors
     * @return List<String> the error list
     */
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public String getCode() {
        return new StringBuilder()
            .append(code.toString())
            .append("\n")
            .toString();
    }
}

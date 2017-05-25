package com.comp.code_generator;

import vendor.Node;
import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import com.comp.common.Visitor;
import com.comp.semantic_analyser.NodeType;
import com.comp.utils.services.NodeUtilsService;
import com.comp.code_generator.generators.CodeGenerator;
import com.comp.code_generator.generators.CodeGeneratorFactory;

import static com.comp.semantic_analyser.NodeType.MODULE_ID;
import static com.comp.code_generator.generators.CodeGeneratorType.MODULE;
import static com.comp.code_generator.generators.CodeGeneratorType.FUNCTION;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class NodeVisitor implements Visitor, Generator {

    private final List<String> errors = new ArrayList<>();
    private final StringJoiner code   = new StringJoiner("\n");

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
        return new StringBuilder()
            .append(code.toString())
            .append("\n")
            .toString();
    }
}

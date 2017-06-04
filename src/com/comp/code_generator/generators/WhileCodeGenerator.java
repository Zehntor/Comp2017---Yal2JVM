package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.STMTLST;
import static com.comp.semantic_analyser.NodeType.EXPR_TEST;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class WhileCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        int labelCounter = com.comp.code_generator.CodeGenerator.getInstance().getLabelCounter();

        code.add(String.format("    while_%s:", labelCounter));

        addExprTest(node);
        addStmtLst(node);

        code
            .add(String.format("    goto while_%s", labelCounter))
            .add(String.format("    while_end_%s:", labelCounter));

        com.comp.code_generator.CodeGenerator.getInstance().incrementLabelCounter();

        return code.toString();
    }

    private void addExprTest(Node node) {
        Node exprTestNode       = NodeUtilsService.getInstance().getChildOfType(node, EXPR_TEST);
        CodeGenerator generator = CodeGeneratorFactory.getInstance().createGenerator(CodeGeneratorType.EXPR_TEST);
        code.add(generator.generate(exprTestNode));
    }

    private void addStmtLst(Node node) {
        Node stmtLstNode        = NodeUtilsService.getInstance().getChildOfType(node, STMTLST);
        CodeGenerator generator = CodeGeneratorFactory.getInstance().createGenerator(CodeGeneratorType.STMTLST);
        code.add(generator.generate(stmtLstNode));
    }
}

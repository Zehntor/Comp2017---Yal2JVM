package com.comp.code_generator.generators;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public enum CodeGeneratorType {

    MODULE("module"),
    FUNCTION("function"),
    FUNCTION_CALL("function call"),
    STMT("stmt"),
    ASSIGN("assign"),
    WHILE("while"),
    IF("if"),
    EXPR_TEST("expr test"),
    STMTLST("stmt lst");

    private final String value;

    CodeGeneratorType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

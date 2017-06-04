package com.comp.semantic_analyser;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public enum NodeType {
    
    MODULE("Module"),
    MODULE_ID("ModuleId"),
    MODULE_END("ModuleEnd"),
    FUNCTION("Function"),
    RETURN_ID("ReturnId"),
    RETURN_IS_ARRAY("ReturnIsArray"),
    FUNCTION_ID("FunctionId"),
    ARGS("Args"),
    FUNCTION_BODY("FunctionBody"),
    FUNCTION_END("FunctionEnd"),
    IF("If"),
    IF_END("IfEnd"),
    ELSE("Else"),
    ELSE_END("ElseEnd"),
    WHILE("While"),
    WHILE_END("WhileEnd"),
    VAR_ID("VarId"),
    VAR_IS_ARRAY("VarIsArray"),
    ASSIGN("Assign"),
    LHS("Lhs"),
    RHS("Rhs"),
    TERM("Term"),
    IS_FUNCTION("IsFunction"),
    ID("Id"),
    ARGUMENT_LIST("ArgumentList"),
    ARGUMENT_ID("ArgumentId"),
    CALL_ID("CallId"),
    CALL_ID_2("CallId2"),
    FUNCTION_CALL("FunctionCall"),
    STMTLST("Stmtlst"),
    STMT("Stmt"),
    DECLARATION("Declaration"),
    DECLARATION_ID("DeclarationId"),
    INIT_VAR("InitVar"),
    ARRAY_SIZE("ArraySize"),
    IS_ARRAY("IsArray"),
    ADD_SUB_OP("AddSubOp"),
    INTEGER("Integer"),
    EXPR_TEST("ExprTest");

    private final String value;

    NodeType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static NodeType fromString(String value) {
        if (value != null) {
            for (NodeType source : NodeType.values()) {
                if (value.equalsIgnoreCase(source.value)) {
                    return source;
                }
            }
        }

        return null;
    }
}

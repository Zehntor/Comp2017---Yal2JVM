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
    FUNCTION_NAME("FunctionName"),
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
    IS_ARRAY("IsArray"),
    ASSIGN("Assign"),
    LHS("Lhs"),
    RHS("Rhs");

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

    public static boolean contains(String value) {
        for (NodeType source : values()) {
            if (source.toString().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public static String[] toStringArray() {
        String[] stringArray = new String[values().length];
        int index = 0;

        for (NodeType source : values()) {
            stringArray[index++] = source.toString();
        }

        return stringArray;
    }
}

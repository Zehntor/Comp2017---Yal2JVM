package com.comp.common;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public enum JasminVarType {

    VOID("V"),
    INTEGER("I"),
    ARRAY_OF_INTEGER("[I");

    private final String value;

    JasminVarType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static JasminVarType fromString(String value) {
        if (value != null) {
            for (JasminVarType source : JasminVarType.values()) {
                if (value.equalsIgnoreCase(source.value)) {
                    return source;
                }
            }
        }

        return null;
    }
}

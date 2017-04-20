package com.comp.semantic_analyser.variables;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public enum VariableType {
    
    INTEGER("integer"),
    ARRAY("array");

    private final String value;

    VariableType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static VariableType fromString(String value) {
        if (value != null) {
            for (VariableType source : VariableType.values()) {
                if (value.equalsIgnoreCase(source.value)) {
                    return source;
                }
            }
        }

        return null;
    }

    public static boolean contains(String value) {
        for (VariableType source : values()) {
            if (source.toString().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public static String[] toStringArray() {
        String[] stringArray = new String[values().length];
        int index = 0;

        for (VariableType source : values()) {
            stringArray[index++] = source.toString();
        }

        return stringArray;
    }
}

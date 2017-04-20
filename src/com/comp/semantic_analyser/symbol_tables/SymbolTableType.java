package com.comp.semantic_analyser.symbol_tables;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public enum SymbolTableType {

    GENERAL("General"),
    MODULE("Module"),
    FUNCTION("Function");

    private final String value;

    SymbolTableType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static SymbolTableType fromString(String value) {
        if (value != null) {
            for (SymbolTableType source : SymbolTableType.values()) {
                if (value.equalsIgnoreCase(source.value)) {
                    return source;
                }
            }
        }

        return null;
    }

    public static boolean contains(String value) {
        for (SymbolTableType source : values()) {
            if (source.toString().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public static String[] toStringArray() {
        String[] stringArray = new String[values().length];
        int index = 0;

        for (SymbolTableType source : values()) {
            stringArray[index++] = source.toString();
        }

        return stringArray;
    }
}

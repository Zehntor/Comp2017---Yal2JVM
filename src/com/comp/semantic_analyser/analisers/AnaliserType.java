package com.comp.semantic_analyser.analisers;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public enum AnaliserType {

    MODULE("module"),
    FUNCTION("function"),
    ASSIGN("assign");

    private final String value;

    AnaliserType(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static AnaliserType fromString(String value) {
        if (value != null) {
            for (AnaliserType source : AnaliserType.values()) {
                if (value.equalsIgnoreCase(source.value)) {
                    return source;
                }
            }
        }

        return null;
    }

    public static boolean contains(String value) {
        for (AnaliserType source : values()) {
            if (source.toString().equals(value)) {
                return true;
            }
        }

        return false;
    }

    public static String[] toStringArray() {
        String[] stringArray = new String[values().length];
        int index = 0;

        for (AnaliserType source : values()) {
            stringArray[index++] = source.toString();
        }

        return stringArray;
    }
}

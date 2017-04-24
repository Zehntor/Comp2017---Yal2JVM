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
}

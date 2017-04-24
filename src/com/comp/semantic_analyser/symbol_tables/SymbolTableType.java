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
}

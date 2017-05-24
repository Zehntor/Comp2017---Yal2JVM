package com.comp.semantic_analyser.symbol_tables;

import static com.comp.semantic_analyser.symbol_tables.SymbolTableType.MODULE;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class ModuleSymbolTable extends GeneralSymbolTable {

    @Override
    public SymbolTableType getType() {
        return MODULE;
    }

    @Override
    public String toString() {
        return String.format("ModuleSymbolTable{id = %s}", id);
    }
}

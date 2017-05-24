package com.comp.semantic_analyser.symbol_tables;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class ModuleSymbolTable extends GeneralSymbolTable {

    protected SymbolTableType type = SymbolTableType.MODULE;

    @Override
    public String toString() {
        return String.format("ModuleSymbolTable{id = %s}", id);
    }
}

package com.comp.semantic_analyser.symbol_tables;

import com.comp.semantic_analyser.variables.Variable;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public interface Findable {
    GeneralSymbolTable findSymbolTable(String id, SymbolTableType type);
    Variable findVariable(String name);
}

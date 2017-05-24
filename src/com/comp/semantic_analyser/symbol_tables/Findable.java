package com.comp.semantic_analyser.symbol_tables;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public interface Findable {
    GeneralSymbolTable findChild(String id, SymbolTableType type);
}

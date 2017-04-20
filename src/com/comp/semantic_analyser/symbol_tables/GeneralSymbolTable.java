package com.comp.semantic_analyser.symbol_tables;

import com.comp.semantic_analyser.variables.Variable;

import java.util.Map;
import java.util.HashMap;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class GeneralSymbolTable {

    protected final Map<String, Variable> variables = new HashMap<>();

    /**
     * Adds an entry to the symbol table
     * @param entry
     * @return GeneralSymbolTable, for a fluent interface
     */
    public GeneralSymbolTable addVariable(Variable entry) {
        variables.put(entry.getName(), entry);
        return this;
    }

    /**
     * Finds an entry by name and returns it
     * @param name
     * @return Variable, or null if not found
     */
    public Variable getVariable(String name) {
        return variables.get(name);
    }

    @Override
    public String toString() {
        return "GeneralSymbolTable{}";
    }
}

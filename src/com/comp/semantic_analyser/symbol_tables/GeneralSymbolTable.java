package com.comp.semantic_analyser.symbol_tables;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import com.comp.semantic_analyser.variables.Variable;

import static com.comp.semantic_analyser.symbol_tables.SymbolTableType.GENERAL;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class GeneralSymbolTable implements Findable {

    protected String id;
    protected final Map<String, Variable> variables   = new HashMap<>();
    protected final List<GeneralSymbolTable> children = new ArrayList<>();

    public GeneralSymbolTable setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return id;
    }

    public SymbolTableType getType() {
        return GENERAL;
    }

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

    public Map<String, Variable> getVariables() {
        return variables;
    }

    public GeneralSymbolTable addChild(GeneralSymbolTable symbolTable) {
        children.add(symbolTable);
        return this;
    }

    @Override
    public GeneralSymbolTable find(String id, SymbolTableType type) {
        if (this.id.equals(id) && this.getType() == type) {
            return this;
        }

        for (GeneralSymbolTable child : children) {
            GeneralSymbolTable found = child.find(id, type);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "GeneralSymbolTable{}";
    }
}

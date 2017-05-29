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
     * Adds an variable to the symbol table
     * @param variable
     * @return GeneralSymbolTable, for a fluent interface
     */
    public GeneralSymbolTable addVariable(Variable variable) {
        variables.put(variable.getName(), variable);
        return this;
    }

    public GeneralSymbolTable addVariables(List<Variable> variables) {
        for (Variable variable : variables) {
            addVariable(variable);
        }

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
    public GeneralSymbolTable findSymbolTable(String id, SymbolTableType type) {
        if (this.id != null && this.id.equals(id) && this.getType() == type) {
            return this;
        }

        for (GeneralSymbolTable child : children) {
            GeneralSymbolTable found = child.findSymbolTable(id, type);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    @Override
    public Variable findVariable(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }

        for (GeneralSymbolTable child : children) {
            Variable found = child.findVariable(name);
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

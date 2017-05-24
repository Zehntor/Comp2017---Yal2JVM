package com.comp.semantic_analyser.symbol_tables;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import com.comp.semantic_analyser.variables.Variable;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class GeneralSymbolTable implements Findable {

    protected String id;
    protected SymbolTableType type = SymbolTableType.GENERAL;
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
        return type;
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

    public GeneralSymbolTable addChild(GeneralSymbolTable symbolTable) {
        children.add(symbolTable);
        return this;
    }

    @Override
    public GeneralSymbolTable findChild(String id, SymbolTableType type) {
        for (GeneralSymbolTable child : children) {
            if (child.getId().equals(id) && child.getType() == type) {
                return child;
            }
            GeneralSymbolTable grandChild = child.findChild(id, type);
            if (grandChild != null) {
                return grandChild;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "GeneralSymbolTable{}";
    }
}

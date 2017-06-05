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
    protected int line;
    protected int column;
    protected final Map<String, Variable> variables   = new HashMap<>();
    protected final List<GeneralSymbolTable> children = new ArrayList<>();

    /**
     * Setter for the id
     * @param id
     * @return GeneralSymbolTable, for a fluent interface
     */
    public GeneralSymbolTable setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for the id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the line
     * @param line
     * @return GeneralSymbolTable, for a fluent interface
     */
    public GeneralSymbolTable setLine(int line) {
        this.line = line;
        return this;
    }

    /**
     * Getter for the line
     * @return int
     */
    public int getLine() {
        return line;
    }

    /**
     * Sets the column
     * @param column
     * @return GeneralSymbolTable, for a fluent interface
     */
    public GeneralSymbolTable setColumn(int column) {
        this.column = column;
        return this;
    }

    /**
     * Getter for the column
     * @return int
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns this symbol table's type
     * @return
     */
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

    /**
     * Adds a list of variables to the variable map
     * @param variables
     * @return
     */
    public GeneralSymbolTable addVariables(List<Variable> variables) {
        for (Variable variable : variables) {
            addVariable(variable);
        }

        return this;
    }

    /**
     * Returns the variable index in the variables map
     * @param variableName
     * @return
     */
    public int getVariableIndex(String variableName) {
        int n = 0;
        for (Map.Entry<String, Variable> entry : variables.entrySet()) {
            if (entry.getKey().equals(variableName)) {
                return n;
            }
            n++;
        }

        return -1;
    }

    /**
     * Returns true if this symbol table has a variable with this name; false otherwise
     * @param name
     * @return
     */
    public boolean hasVariable(String name) {
        return variables.get(name) != null;
    }

    /**
     * Finds variable by name and returns it
     * @param name
     * @return Variable, or null if not found
     */
    public Variable getVariable(String name) {
        return variables.get(name);
    }

    /**
     * Getter for the variables map
     * @return Map<String, Variable>
     */
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

package com.comp.semantic_analyser.variables;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public abstract class Variable {

    protected String name;
    protected Object value;
    protected int line;
    protected int column;
    protected VariableType type;

    public Variable() {
    }

    public Variable(String name) {
        this.name = name;
    }

    public Variable(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Setter for this variable's name
     * @param name
     * @return Variable, for a fluent interface
     */
    public Variable setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Getter for the name
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the line
     * @param line
     * @return Variable, for a fluent interface
     */
    public Variable setLine(int line) {
        this.line = line;
        return this;
    }

    /**
     * Getter for the line
     * @return
     */
    public int getLine() {
        return line;
    }

    /**
     * Setter for the column
     * @param column
     * @return
     */
    public Variable setColumn(int column) {
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

    public abstract Object getValue();
    public abstract VariableType getType();
}

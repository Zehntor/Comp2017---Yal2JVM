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

    public Variable setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Variable setLine(int line) {
        this.line = line;
        return this;
    }

    public int getLine() {
        return line;
    }

    public Variable setColumn(int column) {
        this.column = column;
        return this;
    }

    public int getColumn() {
        return column;
    }

    public abstract Object getValue();
    public abstract VariableType getType();
}

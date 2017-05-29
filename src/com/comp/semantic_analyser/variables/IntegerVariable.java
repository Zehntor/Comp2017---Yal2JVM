package com.comp.semantic_analyser.variables;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class IntegerVariable extends Variable {

    private Integer value;

    public IntegerVariable() {
    }

    public IntegerVariable(String name) {
        super(name);
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public VariableType getType() {
        return VariableType.INTEGER;
    }
}

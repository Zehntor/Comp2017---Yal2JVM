package com.comp.semantic_analyser.variables;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class ArrayVariable extends Variable {

    private List<Integer> values = new ArrayList<>();

    public Object getValue() {
        return values;
    }

    public void addValue(Integer value) {
        values.add(value);
    }

    public Integer getValueAt(int index) {
        return values.get(index);
    }

    public void addValues(List<Integer> values) {
        this.values.addAll(values);
    }

    public VariableType getType() {
        return VariableType.ARRAY;
    }
}

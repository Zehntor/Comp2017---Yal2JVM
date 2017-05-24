package com.comp.semantic_analyser.symbol_tables;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import com.comp.semantic_analyser.variables.Variable;

import static com.comp.semantic_analyser.symbol_tables.SymbolTableType.FUNCTION;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class FunctionSymbolTable extends GeneralSymbolTable {

    private Variable returnVariable;
    private final List<Variable> arguments = new ArrayList<>();

    @Override
    public SymbolTableType getType() {
        return FUNCTION;
    }

    public void setReturnVariable(Variable returnVariable) {
        this.returnVariable = returnVariable;
    }

    public Variable getReturnVariable() {
        return returnVariable;
    }

    public List<Variable> getArguments() {
        return arguments;
    }

    /**
     * Adds an argument to the arguments list
     * @param argument
     * @return FunctionSymbolTable, for a fluid interface
     */
    public FunctionSymbolTable addArgument(Variable argument) {
        arguments.add(argument);
        return this;
    }

    /**
     * Adds a list of arguments to the arguments list
     * @param arguments
     * @return FunctionSymbolTable, for a fluid interface
     */
    public FunctionSymbolTable addArguments(List<Variable> arguments) {
        this.arguments.addAll(arguments);
        return this;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (Variable variable : arguments) {
            stringJoiner.add(variable.getName());
        }

        if (returnVariable != null) {
            switch (returnVariable.getType()) {
                case INTEGER:
                    return String.format("FunctionSymbolTable{id = %s, arguments = [%s], return type = integer}", id, stringJoiner.toString());
                case ARRAY:
                    return String.format("FunctionSymbolTable{id = %s, arguments = [%s]}, return type = array", id, stringJoiner.toString());
            }
        }

        return String.format("FunctionSymbolTable{id = %s, arguments = [%s]}, return type = void", id, stringJoiner.toString());
    }
}

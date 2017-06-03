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

    /**
     * Returns this symbol table's type
     * @return
     */
    @Override
    public SymbolTableType getType() {
        return FUNCTION;
    }

    /**
     * Sets the function's return variable
     * @param returnVariable
     * @return FunctionSymbolTable, for a fluent interface
     */
    public FunctionSymbolTable setReturnVariable(Variable returnVariable) {
        this.returnVariable = returnVariable;
        return this;
    }

    /**
     * Getter for the return variable
     * @return Variable
     */
    public Variable getReturnVariable() {
        return returnVariable;
    }

    /**
     * Getter for the argument list
     * @return
     */
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
                    return String.format("FunctionSymbolTable{id = %s, arguments = [%s], return = [%s], type = integer}",
                        id,
                        stringJoiner.toString(),
                        returnVariable.getName()
                    );
                case ARRAY:
                    return String.format("FunctionSymbolTable{id = %s, arguments = [%s]}, return = [%s], type = array",
                        id,
                        stringJoiner.toString(),
                        returnVariable.getName()
                    );
            }
        }

        return String.format("FunctionSymbolTable{id = %s, arguments = [%s]}, return type = void", id, stringJoiner.toString());
    }
}

package com.comp.semantic_analyser.symbol_tables;

import java.util.Stack;
import com.comp.semantic_analyser.variables.Variable;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class SymbolTableStack {

    /**
     * The LIFO stack where the symbol tables are kept
     * @var Stack<GeneralSymbolTable>
     */
    private final Stack<GeneralSymbolTable> stack = new Stack<>();

    /**
     * Pushes a GeneralSymbolTable into the stack
     * @param symbolTable
     * @return SymbolTableStack, for a fluent interface
     */
    public SymbolTableStack push(GeneralSymbolTable symbolTable) {
        stack.push(symbolTable);
        return this;
    }

    /**
     * Pops a GeneralSymbolTable from the stack
     * @return
     */
    public GeneralSymbolTable pop() {
        return stack.pop();
    }

    /**
     * Returns the GeneralSymbolTable at the top of this stack without removing it from the stack.
     * @return
     */
    public GeneralSymbolTable peek() {
        return stack.peek();
    }

    /**
     * Finds a GeneralSymbolTable entry
     * Iterates the stack from top to bottom and searches each symbol table for an entry with name = name
     * @param name
     * @return Variable or null if not found
     */
    public Variable findSymbolTableEntry(String name) {
        for (int n = stack.size() - 1; n >= 0; n--) {
            GeneralSymbolTable symbolTable = stack.get(n);
            Variable variable = symbolTable.getVariable(name);
            if (variable != null) {
                return variable;
            }
        }

        return null;
    }
}

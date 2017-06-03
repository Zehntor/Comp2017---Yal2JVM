package com.comp.semantic_analyser.analisers;

import vendor.Node;
import java.util.List;
import java.util.ArrayList;
import com.comp.semantic_analyser.symbol_tables.SymbolTableTree;
import com.comp.semantic_analyser.symbol_tables.SymbolTableStack;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public abstract class Analiser {

    protected static final String MISSING_SYMBOL_TABLE_STACK = "Missing symbol table stack, cannot analise node";

    protected SymbolTableStack symbolTableStack;
    protected SymbolTableTree symbolTableTree;
    protected final List<String> errors = new ArrayList<>();

    public abstract void analise(Node node);

    /**
     * Setter for a SymbolTableStack
     * @param symbolTableStack
     */
    public void setSymbolTableStack(SymbolTableStack symbolTableStack) {
        this.symbolTableStack = symbolTableStack;
    }

    /**
     * Setter for a SymbolTableTree
     * @param symbolTableTree
     */
    public void setSymbolTableTree(SymbolTableTree symbolTableTree) {
        this.symbolTableTree = symbolTableTree;
    }

    /**
     * Adds an error to the errors list
     * @param error
     */
    public void addError(String error) {
        errors.add(error);
    }

    /**
     * Getter for errors
     * @return List<String> the error list
     */
    public List<String> getErrors() {
        return errors;
    }
}

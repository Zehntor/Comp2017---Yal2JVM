package com.comp.semantic_analyser.symbol_tables;

import com.comp.semantic_analyser.variables.Variable;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class SymbolTableTree implements Findable {

    /**
     * This tree's root
     * @var GeneralSymbolTable
     */
    private GeneralSymbolTable root;

    /**
     * Sets the root node of this tree
     * @param root
     * @return
     */
    public SymbolTableTree setRoot(GeneralSymbolTable root) {
        this.root = root;
        return this;
    }

    /**
     * Returns the root node of this tree
     * @return
     */
    public GeneralSymbolTable getRoot() {
        return root;
    }

    /**
     * Finds and returns a symbol table by id and type
     * @param id
     * @param type
     * @return GeneralSymbolTable
     */
    @Override
    public GeneralSymbolTable findSymbolTable(String id, SymbolTableType type) {
        if (root == null) {
            return null;
        }

        return root.findSymbolTable(id, type);
    }

    /**
     * Finds and returns a variable by name
     * @param name
     * @return
     */
    @Override
    public Variable findVariable(String name) {
        if (root == null) {
            return null;
        }

        return root.findVariable(name);
    }
}

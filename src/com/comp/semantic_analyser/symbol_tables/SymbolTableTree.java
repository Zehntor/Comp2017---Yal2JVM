package com.comp.semantic_analyser.symbol_tables;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class SymbolTableTree implements Findable {

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

    @Override
    public GeneralSymbolTable findChild(String id, SymbolTableType type) {
        if (root == null) {
            return null;
        }

        if (root.getId().equals(id) && root.getType() == type) {
            return root;
        }

        return root.findChild(id, type);
    }
}

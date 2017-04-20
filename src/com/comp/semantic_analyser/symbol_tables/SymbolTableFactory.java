package com.comp.semantic_analyser.symbol_tables;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 *
 * Creates instances of every class related to symbol tables
 * Singleton
 */
public final class SymbolTableFactory {

    /**
     * The one and only instance of this class
     */
    private static final SymbolTableFactory instance = new SymbolTableFactory();

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private SymbolTableFactory() {
    }

    /**
     * Returns the one and only instance of this class
     * @return
     */
    public static SymbolTableFactory getInstance() {
        return instance;
    }

    /**
     * Creates and returns a SymbolTableStack instance
     * @return SymbolTableStack
     */
    public SymbolTableStack createSymbolTableStack() {
        return new SymbolTableStack();
    }

    /**
     * Creates and returns a GeneralSymbolTable instance
     * @return GeneralSymbolTable
     */
    public GeneralSymbolTable createSymbolTable(SymbolTableType type) {
        switch (type) {
            case MODULE:
                return new ModuleSymbolTable();
            case FUNCTION:
                return new FunctionSymbolTable();
        }

        return new GeneralSymbolTable();
    }
}

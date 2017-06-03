package com.comp.semantic_analyser.analisers;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class AnaliserFactory {

    /**
     * The one and only instance of this class
     */
    private static final AnaliserFactory instance = new AnaliserFactory();

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private AnaliserFactory() {
    }

    /**
     * Returns the one and only instance of this class
     * @return AnaliserFactory
     */
    public static AnaliserFactory getInstance() {
        return instance;
    }

    public Analiser createAnaliser(AnaliserType type) {
        switch (type) {
            case FUNCTION:
                return new FunctionAnaliser();
            case MODULE:
                return new ModuleAnaliser();
            case ASSIGN:
                return new AssignAnaliser();
        }

        return null;
    }
}

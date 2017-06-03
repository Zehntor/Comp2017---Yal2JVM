package com.comp.semantic_analyser.variables;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 *
 * Factory for variable creation
 */
public class VariableFactory {

    /**
     * The one and only instance of this class
     */
    private static final VariableFactory instance = new VariableFactory();

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private VariableFactory() {
    }

    /**
     * Returns the one and only instance of this class
     * @return
     */
    public static VariableFactory getInstance() {
        return instance;
    }

    /**
     * Creates and returns a Variable instance
     * @return Variable
     */
    public Variable createVariable(VariableType type) {
        switch (type) {
            case INTEGER:
                return new IntegerVariable();
            case ARRAY:
                return new ArrayVariable();
        }

        return null;
    }
}

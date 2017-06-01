package com.comp.code_generator.generators;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class CodeGeneratorFactory {

    /**
     * The one and only instance of this class
     */
    private static final CodeGeneratorFactory instance = new CodeGeneratorFactory();

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private CodeGeneratorFactory() {
    }

    /**
     * Returns the one and only instance of this class
     *
     * @return
     */
    public static CodeGeneratorFactory getInstance() {
        return instance;
    }

    public CodeGenerator createGenerator(CodeGeneratorType type) {
        switch (type) {
            case MODULE:
                return new ModuleCodeGenerator();
            case FUNCTION:
                return new FunctionCodeGenerator();
            case FUNCTION_END:
                return new FunctionEndCodeGenerator();
            case FUNCTION_CALL:
                return new FunctionCallCodeGenerator();
        }

        return null;
    }
}
package com.comp.code_generator.generators;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 *
 * Creates instances of every class related to code generation
 * Singleton
 */
public final class CodeGeneratorFactory {

    /**
     * The one and only instance of this class
     * @var CodeGeneratorFactory
     */
    private static final CodeGeneratorFactory instance = new CodeGeneratorFactory();

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private CodeGeneratorFactory() {
    }

    /**
     * Returns the one and only instance of this class
     * @return CodeGeneratorFactory
     */
    public static CodeGeneratorFactory getInstance() {
        return instance;
    }

    /**
     * Creates and returns a generator
     * @param type
     * @return CodeGenerator
     */
    public CodeGenerator createGenerator(CodeGeneratorType type) {
        switch (type) {
            case MODULE:
                return new ModuleCodeGenerator();
            case FUNCTION:
                return new FunctionCodeGenerator();
            case FUNCTION_CALL:
                return new FunctionCallCodeGenerator();
            case STMT:
                return new StatementCodeGenerator();
        }

        return null;
    }
}
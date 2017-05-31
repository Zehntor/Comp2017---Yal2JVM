package com.comp;

import vendor.Parser;
import java.util.List;
import vendor.SimpleNode;
import vendor.ParseException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import com.comp.utils.services.UtilsService;
import com.comp.code_generator.CodeGenerator;
import com.comp.profiler.models.TimeMemoryProfile;
import com.comp.semantic_analyser.SemanticAnaliser;
import com.comp.profiler.services.TimeMemoryProfilerService;

public class Yal2jvm {

    private static final String YAL_EXTENSION      = "yal";
    private static final String JASMIN_EXTENSION   = "j";
    private static final String COMPILATION_FAILED = "Compilation failed...";

    /**
     * Application entry point
     * Façade method for the whole compiler
     * @param args
     */
    public static void main(String[] args) {

        // Start profiling
        String profileKey = TimeMemoryProfilerService.getInstance().start();

        // Check arguments
        if (!checkArgs(args)) {
            System.exit(1);
        }

        // Compile
        compile(args[0]);

        // Stop profiling and display the profile
        TimeMemoryProfile profile = TimeMemoryProfilerService.getInstance().stop(profileKey);
        System.out.println();
        System.out.println(String.format("Compilation completed successfully in %s using %s of memory",
            profile.getHumanTime(),
            UtilsService.getInstance().formatBytes(profile.getTotalMemory())
        ));
    }

    /**
     * Checks the application arguments
     * Returns true if the arguments are ok, false otherwise
     * Prints a message if the arguments are not ok
     * @param args
     * @return boolean
     */
    private static boolean checkArgs(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar yal2jvm.jar <input-file.yal>");
        }

        return args.length == 1;
    }

    private static void compile(String inputFilename) {
        SimpleNode root = performSyntacticAnalysis(inputFilename);
        performSemanticAnalysis(root);
        performCodeGeneration(root, getOutputFilename(inputFilename));
    }

    /**
     * Performs the syntactic analysis step of the compilation
     * @param inputFilename
     * @return SimpleNode the root node
     */
    private static SimpleNode performSyntacticAnalysis(String inputFilename) {
        SimpleNode root = null;
        try {
            Parser parser = new Parser(new FileInputStream(inputFilename));
            root = parser.Start();
            // root.dump("");
        } catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found. Cannot do anything about that; exiting now.", inputFilename));
            System.exit(1);
        } catch (ParseException e) {
            System.out.println(String.format("A parse exception occurred while parsing file %s:%n%s", inputFilename, e.getMessage()));
            System.exit(1);
        }

        return root;
    }

    /**
     * Performs the semantic analysis step of the compilation
     * @param root the root node
     */
    private static void performSemanticAnalysis(SimpleNode root) {
        SemanticAnaliser.getInstance().analise(root);
        if (SemanticAnaliser.getInstance().hasErrors()) {
            List<String> errors = SemanticAnaliser.getInstance().getErrors();
            System.out.println(COMPILATION_FAILED);
            System.out.println(String.format("%s:",
                UtilsService.getInstance().getHumanReadableNumber(errors.size(),"semantic error")
            ));
            showErrors(errors);
            System.exit(1);
        }
    }

    /**
     * ﻿Performs the code generation step of the compilation
     * @param root the root node
     */
    private static void performCodeGeneration(SimpleNode root, String outputFilename) {
        CodeGenerator.getInstance().generateCode(root, outputFilename);
        if (CodeGenerator.getInstance().hasErrors()) {
            List<String> errors = CodeGenerator.getInstance().getErrors();
            System.out.println(COMPILATION_FAILED);
            System.out.println(
                UtilsService.getInstance().getHumanReadableNumber(errors.size(),"code generation error:")
            );
            showErrors(CodeGenerator.getInstance().getErrors());
            System.exit(1);
        }
    }

    /**
     * Determines the output file name based on the input file name
     * @param inputFilename
     * @return String
     */
    private static String getOutputFilename(String inputFilename) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
            .append(inputFilename.substring(0, inputFilename.length() - YAL_EXTENSION.length()))
            .append(JASMIN_EXTENSION);

        return stringBuilder.toString();
    }

    /**
     * Displays a list of errors, one per line
     * @param errors
     */
    private static void showErrors(List<String> errors) {
        for (String error : errors) {
            System.out.println(String.format("    -> %s", error));
        }
    }
}

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

    /**
     * Application entry point
     * Façade method for the whole compiler
     * @param args
     */
    public static void main(String[] args) {

        // Start profiling
        String profileKey = TimeMemoryProfilerService.getInstance().start();

        // Check arguments
        checkArgs(args);

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
     */
    private static void checkArgs(String[] args) {
        if (args.length != 1) {
            System.out.println("Argument error:");
            showError("No input file supplied");
            System.out.println("Usage: java -jar yal2jvm.jar <input-file.yal>");
            abortCompilation();
        } else if (!args[0].endsWith("yal")) {
            System.out.println("The input file name must have a 'yal' extension");
            abortCompilation();
        }
    }

    /**
     * Tries to compile the file
     * Calls the syntactic analysis
     * Calls the semantic analysis
     * Calls the code generation
     * @param inputFilename
     */
    private static void compile(String inputFilename) {
        SimpleNode root = performSyntacticAnalysis(inputFilename);
        performSemanticAnalysis(root);
        performCodeGeneration(root, getOutputFilename(inputFilename));
    }

    /**
     * Performs the syntactic analysis step of the compilation
     * If there ara errors, shows them and aborts the compilation
     * @param inputFilename
     * @return SimpleNode the root node
     */
    private static SimpleNode performSyntacticAnalysis(String inputFilename) {
        System.out.print("Performing syntactic analysis...");

        SimpleNode root = null;
        try {
            Parser parser = new Parser(new FileInputStream(inputFilename));
            root = parser.Start();
            // root.dump("");
            System.out.println(" done, everything ok");
        } catch (FileNotFoundException e) {
            System.out.println();
            System.out.println(String.format("File '%s' not found. Cannot do anything about that; exiting now.", inputFilename));
            abortCompilation();
        } catch (ParseException e) {
            System.out.println();
            System.out.println(String.format("A parse exception occurred while parsing file '%s':%n%s", inputFilename, e.getMessage()));
            abortCompilation();
        }


        return root;
    }

    /**
     * Performs the semantic analysis step of the compilation
     * If there are errors, shows them and aborts the compilation
     * @param root the root node
     */
    private static void performSemanticAnalysis(SimpleNode root) {
        System.out.print("Performing semantic analysis...");

        SemanticAnaliser.getInstance().analise(root);
        if (SemanticAnaliser.getInstance().hasErrors()) {
            List<String> errors = SemanticAnaliser.getInstance().getErrors();
            System.out.println();
            System.out.println(String.format("%s:",
                UtilsService.getInstance().getHumanReadableNumber(errors.size(),"semantic error")
            ));
            showErrors(errors);
            abortCompilation();
        }

        System.out.println(" done, everything ok");
    }

    /**
     * ﻿Performs the code generation step of the compilation
     * If there ara errors, shows them and aborts the compilation
     * @param root the root node
     */
    private static void performCodeGeneration(SimpleNode root, String outputFilename) {
        System.out.println("Performing code generation");

        CodeGenerator.getInstance().generateCode(root, outputFilename);
        if (CodeGenerator.getInstance().hasErrors()) {
            List<String> errors = CodeGenerator.getInstance().getErrors();
            System.out.println();
            System.out.println(
                UtilsService.getInstance().getHumanReadableNumber(errors.size(),"code generation error:")
            );
            showErrors(CodeGenerator.getInstance().getErrors());
            abortCompilation();
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
            .append(inputFilename.substring(0, inputFilename.length() - "yal".length()))
            .append("j");

        return stringBuilder.toString();
    }

    /**
     * Displays a list of errors, one per line
     * @param errors
     */
    private static void showErrors(List<String> errors) {
        for (String error : errors) {
            showError(error);
        }
    }

    /**
     * Displays an error
     * @param error
     */
    private static void showError(String error) {
        System.out.println(String.format("    -> %s", error));
    }

    /**
     * Aborts the compilation and exists the program with an error status code
     */
    private static void abortCompilation() {
        System.out.println("Aborting compilation...");
        System.exit(1);
    }
}

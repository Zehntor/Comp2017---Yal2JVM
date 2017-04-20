package com.comp;

import com.comp.profiler.models.TimeMemoryProfile;
import com.comp.profiler.services.TimeMemoryProfilerService;
import com.comp.semantic_analyser.SemanticAnaliser;
import com.comp.utils.services.UtilsService;
import vendor.Parser;
import vendor.SimpleNode;
import vendor.ParseException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class Yal2jvm {

    /**
     * Application entry point
     * Façade method for the whole compiler
     * @param args
     */
    public static void main(String[] args) {
        String profileKey = TimeMemoryProfilerService.getInstance().start();

        if (!checkArgs(args)) {
            System.exit(1);
        }

        String filename = args[0];

        try {
            Parser parser = new Parser(new FileInputStream(filename));
            SimpleNode root = parser.Start();
            root.dump("");
            SemanticAnaliser.getInstance().analise(root);
            if (SemanticAnaliser.getInstance().hasErrors()) {
                showErrors(SemanticAnaliser.getInstance().getErrors());
            }
        } catch (FileNotFoundException e) {
            System.out.println(String.format("File %s not found"));
            System.exit(1);
        } catch (ParseException e) {
            System.out.println(String.format("A parse exception occurred while parsing file %s:%n%s", filename, e.getMessage()));
            System.exit(1);
        }

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
            System.out.println("Usage: java -jar yal2jvm <input-file.yal>");
        }

        return args.length == 1;
    }

    /**
     * Displays a list of errors, one per line
     * @param errors
     */
    private static void showErrors(List<String> errors) {
        for (String error : errors) {
            System.out.println(error);
        }
    }
}

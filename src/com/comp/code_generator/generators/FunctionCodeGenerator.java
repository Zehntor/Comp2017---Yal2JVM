package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.common.JasminVarType;
import com.comp.utils.services.NodeUtilsService;
import com.comp.utils.services.JasminUtilsService;
import com.comp.semantic_analyser.SemanticAnaliser;
import com.comp.semantic_analyser.symbol_tables.FunctionSymbolTable;

import static com.comp.semantic_analyser.symbol_tables.SymbolTableType.FUNCTION;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 *
 * Generates code for functions
 */
public final class FunctionCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        addHeader(node);
        addLimits(node);

        return code.toString();
    }

    /**
     * Adds the function header
     * @param node a FUNTION node
     */
    private void addHeader(Node node) {
        String functionId = NodeUtilsService.getInstance().getFunctionId(node);
        String argList;
        JasminVarType returnType;

        if (functionId.equals("main")) {
            argList    = "[Ljava/lang/String;";
            returnType = JasminVarType.VOID;
        } else {
            argList    = JasminUtilsService.getInstance().getJasminArgListFromNode(node);
            returnType = JasminUtilsService.getInstance().getJasminReturnTypeFromNode(node);
        }

        code
            .add("")
            .add(String.format("; Function %s", functionId))
            .add(String.format(".method public static %s(%s)%s", functionId, argList, returnType));
    }

    private void addLimits(Node node) {
        String functionId = NodeUtilsService.getInstance().getFunctionId(node);

        FunctionSymbolTable symbolTable =
            (FunctionSymbolTable) SemanticAnaliser.getInstance().getSymbolTableTree().findSymbolTable(functionId, FUNCTION);

        int limit = symbolTable.getArguments().size() + symbolTable.getVariables().size();
        if (symbolTable.getReturnVariable() != null) {
            limit++;
        }

        code
            .add(String.format("    .limit stack %s", limit))
            .add(String.format("    .limit locals %s", limit));
    }
}

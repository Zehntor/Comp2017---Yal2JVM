package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.common.JasminVarType;
import com.comp.utils.services.NodeUtilsService;
import com.comp.utils.services.JasminUtilsService;
import com.comp.semantic_analyser.SemanticAnaliser;
import com.comp.semantic_analyser.variables.Variable;
import com.comp.semantic_analyser.variables.ArrayVariable;
import com.comp.semantic_analyser.variables.IntegerVariable;
import com.comp.semantic_analyser.symbol_tables.FunctionSymbolTable;

import static com.comp.semantic_analyser.NodeType.STMTLST;
import static com.comp.semantic_analyser.NodeType.FUNCTION_BODY;
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
        addFunctionBody(node);
        addFooter(node);

        return code.toString();
    }

    /**
     * Adds the function header
     * @param node a FUNCTION node
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

        int limit = Math.max(
            symbolTable.getArguments().size() + symbolTable.getVariables().size(),
            1
        );
        if (symbolTable.getReturnVariable() != null) {
            limit++;
        }

        code
            .add(String.format("    .limit stack %s", limit))
            .add(String.format("    .limit locals %s", limit));
    }

    private void addFunctionBody(Node node) {
        Node functionBodyNode = node.getChildOfType(FUNCTION_BODY);
        if (functionBodyNode == null) {
            return;
        }

        CodeGenerator generator = CodeGeneratorFactory.getInstance().createGenerator(CodeGeneratorType.STMTLST);
        Node stmtlstNode        = functionBodyNode.getChildOfType(STMTLST);

        code.add(generator.generate(stmtlstNode));
    }

    private void addFooter(Node node) {
        String functionId = NodeUtilsService.getInstance().getFunctionId(node);
        FunctionSymbolTable symbolTable =
            (FunctionSymbolTable) SemanticAnaliser.getInstance().getSymbolTableTree().findSymbolTable(functionId, FUNCTION);

        code.add("");

        Variable returnVariable = symbolTable.getReturnVariable();
        if (returnVariable == null) {
            code.add("    return");
        } else if (returnVariable instanceof IntegerVariable) {
            code.add("    ireturn");
        } else if (returnVariable instanceof ArrayVariable) {
            code.add("    areturn");
        }

        code.add(".end method");
    }
}

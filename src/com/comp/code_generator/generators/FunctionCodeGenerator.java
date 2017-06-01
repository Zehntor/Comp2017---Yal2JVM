package com.comp.code_generator.generators;

import vendor.Node;
import java.util.StringJoiner;
import com.comp.semantic_analyser.NodeType;
import com.comp.code_generator.JasminVarType;
import com.comp.utils.services.NodeUtilsService;
import com.comp.semantic_analyser.SemanticAnaliser;
import com.comp.semantic_analyser.symbol_tables.FunctionSymbolTable;

import static com.comp.semantic_analyser.symbol_tables.SymbolTableType.FUNCTION;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class FunctionCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        addHeader(node);
        addLimits(node);

        return code.toString();
    }

    private void addHeader(Node node) {
        String functionId = NodeUtilsService.getInstance().getFunctionId(node);
        String argList;
        JasminVarType returnType;

        if (functionId.equals("main")) {
            argList    = "[Ljava/lang/String;";
            returnType = JasminVarType.VOID;
        } else {
            argList    = getJasminArgList(node);
            returnType = getJasminReturnType(node);
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

    private String getJasminArgList(Node node) {
        Node argsNode = NodeUtilsService.getInstance().getChildByType(node, NodeType.ARGS);
        if (argsNode == null) {
            return "";
        }

        StringJoiner argList = new StringJoiner(",");

        for (int n = 0; n < argsNode.jjtGetNumChildren(); n++) {
            boolean
                nodeIsVarId     = NodeType.fromString(argsNode.jjtGetChild(n).toString()) == NodeType.VAR_ID,
                nextNodeIsArray = n < argsNode.jjtGetNumChildren() - 1 && NodeType.fromString(argsNode.jjtGetChild(n + 1).toString()) == NodeType.VAR_IS_ARRAY;

            if (nodeIsVarId) {
                if (nextNodeIsArray) {
                    argList.add(JasminVarType.ARRAY_OF_INTEGER.toString());
                } else {
                    argList.add(JasminVarType.INTEGER.toString());
                }
            }
        }

        return argList.toString();
    }

    private JasminVarType getJasminReturnType(Node node) {
        Node
            returnId = NodeUtilsService.getInstance().getChildByType(node, NodeType.RETURN_ID),
            returnIsArray = NodeUtilsService.getInstance().getChildByType(node, NodeType.RETURN_IS_ARRAY);

        if (returnId == null) {
            return JasminVarType.VOID;
        }

        if (returnIsArray != null) {
            return JasminVarType.ARRAY_OF_INTEGER;
        }

        return JasminVarType.INTEGER;
    }
}

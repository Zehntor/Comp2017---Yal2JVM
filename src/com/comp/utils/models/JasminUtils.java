package com.comp.utils.models;

import vendor.Node;
import com.comp.common.JasminVarType;
import com.comp.semantic_analyser.NodeType;
import com.comp.utils.services.NodeUtilsService;
import com.comp.semantic_analyser.variables.Variable;
import com.comp.semantic_analyser.variables.ArrayVariable;
import com.comp.semantic_analyser.variables.IntegerVariable;
import com.comp.semantic_analyser.symbol_tables.SymbolTableType;
import com.comp.semantic_analyser.symbol_tables.SymbolTableTree;
import com.comp.semantic_analyser.symbol_tables.FunctionSymbolTable;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class JasminUtils {

    /**
     * Gets a jasmin argument list from a node
     * @param node
     * @return String
     */
    public String getJasminArgListFromNode(Node node) {
        Node argsNode = NodeUtilsService.getInstance().getChildOfType(node, NodeType.ARGS);
        if (argsNode == null) {
            return "";
        }

        StringBuilder argList = new StringBuilder();

        for (int n = 0; n < argsNode.jjtGetNumChildren(); n++) {
            boolean
                nodeIsVarId     = NodeUtilsService.getInstance().nodeIsOfType(argsNode.jjtGetChild(n), NodeType.VAR_ID),
                nextNodeIsArray = n < argsNode.jjtGetNumChildren() - 1 && NodeUtilsService.getInstance().nodeIsOfType(argsNode.jjtGetChild(n + 1), NodeType.VAR_IS_ARRAY);

            if (nodeIsVarId) {
                if (nextNodeIsArray) {
                    argList.append(JasminVarType.ARRAY_OF_INTEGER.toString());
                } else {
                    argList.append(JasminVarType.INTEGER.toString());
                }
            }
        }

        return argList.toString();
    }

    /**
     * Gets a jasmin return type from a node
     * @param node
     * @return String
     */
    public JasminVarType getJasminReturnTypeFromNode(Node node) {
        Node
            returnId = NodeUtilsService.getInstance().getChildOfType(node, NodeType.RETURN_ID),
            returnIsArray = NodeUtilsService.getInstance().getChildOfType(node, NodeType.RETURN_IS_ARRAY);

        if (returnId == null) {
            return JasminVarType.VOID;
        }

        if (returnIsArray != null) {
            return JasminVarType.ARRAY_OF_INTEGER;
        }

        return JasminVarType.INTEGER;
    }

    /**
     * Gets a jasmin argument list from a symbol table
     * @param symbolTableTree
     * @param functionId
     * @return String
     */
    public String getJasminArgListFromSymbolTable(SymbolTableTree symbolTableTree, String functionId) {
        FunctionSymbolTable symbolTable = (FunctionSymbolTable) symbolTableTree.findSymbolTable(functionId, SymbolTableType.FUNCTION);

        StringBuilder stringBuilder = new StringBuilder();
        for (Variable argument : symbolTable.getArguments()) {
            if (argument instanceof IntegerVariable) {
                stringBuilder.append("I");
            } else if (argument instanceof ArrayVariable) {
                stringBuilder.append("[I");
            }
        }

        return stringBuilder.toString();
    }

    /**
     * @param node A stmt node
     * @return String
     */
    public String getJasminArgListCallFromNode(Node node) {
        Node argumentListNode = NodeUtilsService.getInstance().getChildOfType(node, NodeType.ARGUMENT_LIST);
        StringBuilder stringBuilder = new StringBuilder();
        for (int n = 0; n < argumentListNode.jjtGetNumChildren(); n++) {
            stringBuilder.append("I");
        }

        return stringBuilder.toString();
    }

    /**
     * Gets a jasmin return type from a symbol table
     * @param symbolTableTree
     * @param functionId
     * @return String
     */
    public String getJasminReturnTypeFromSymbolTable(SymbolTableTree symbolTableTree, String functionId) {
        FunctionSymbolTable symbolTable = (FunctionSymbolTable) symbolTableTree.findSymbolTable(functionId, SymbolTableType.FUNCTION);

        Variable returnVariable = symbolTable.getReturnVariable();
        if (returnVariable == null) {
            return "V";
        }
        if (returnVariable instanceof IntegerVariable) {
            return "I";
        }
        if (returnVariable instanceof ArrayVariable) {
            return "[I";
        }

        return null;    // Should never happen
    }
}

package com.comp.semantic_analyser.analisers;

import vendor.Node;
import java.util.List;
import java.util.ArrayList;
import com.comp.utils.services.NodeUtilsService;
import com.comp.semantic_analyser.variables.Variable;
import com.comp.semantic_analyser.variables.VariableFactory;
import com.comp.semantic_analyser.variables.IntegerVariable;
import com.comp.semantic_analyser.symbol_tables.SymbolTableType;
import com.comp.semantic_analyser.symbol_tables.SymbolTableFactory;
import com.comp.semantic_analyser.symbol_tables.FunctionSymbolTable;

import static com.comp.semantic_analyser.NodeType.*;
import static com.comp.semantic_analyser.variables.VariableType.INTEGER;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class AssignAnaliser extends Analiser {

    public void analise(Node node) {
        if (symbolTableStack == null) {
            throw new RuntimeException(MISSING_SYMBOL_TABLE_STACK);
        }

        // TODO
        Node lhsNode = NodeUtilsService.getInstance().getChildOfType(node, LHS);
        String variableName = lhsNode.getValue().toString();
        boolean variableExists = symbolTableStack.peek().findVariable(variableName) != null;

        if (!variableExists) {
            symbolTableStack.peek().addVariable(new IntegerVariable(variableName));
        }
    }

    /**
     * Returns true if this node's descendants have IS_FUNCTION nodes
     * @param node
     * @return boolean
     */
    public boolean hasFunctionCalls(Node node) {
        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            Node child = node.jjtGetChild(n);
            if (NodeUtilsService.getInstance().hasChildOfType(child, IS_FUNCTION) || hasFunctionCalls(child)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Getter for function calls
     * @param node
     * @return List<FunctionSymbolTable> the list of function calls
     */
    public List<FunctionSymbolTable> getFunctionCalls(Node node) {
        List<FunctionSymbolTable> functionCalls = new ArrayList<>();

        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            Node child = node.jjtGetChild(n);
            if (NodeUtilsService.getInstance().nodeIsOfType(child, IS_FUNCTION)) {
                functionCalls.add(createFunctionCall(child));
            }
            functionCalls.addAll(getFunctionCalls(child));
        }

        return functionCalls;
    }

    /**
     * Node must be a IS_FUNCTION node
     * @param isFunctionNode
     * @return FunctionSymbolTable
     */
    private FunctionSymbolTable createFunctionCall(Node isFunctionNode) {
        FunctionSymbolTable symbolTable = (FunctionSymbolTable) SymbolTableFactory.getInstance().createSymbolTable(SymbolTableType.FUNCTION);
        Node idNode = NodeUtilsService.getInstance().getChildOfType(isFunctionNode.jjtGetParent(), ID);
        symbolTable
            .setId(idNode.getValue().toString())
            .setLine(idNode.getLine())
            .setColumn(idNode.getColumn());
        List<Variable> arguments = getFunctionArguments(isFunctionNode);
        symbolTable.addArguments(arguments);

        return symbolTable;
    }

    /**
     * Node must be a IS_FUNCTION node
     * @param isFunctionNode
     * @return
     */
    private List<Variable> getFunctionArguments(Node isFunctionNode) {
        List<Variable> functionArguments = new ArrayList<>();
        Node argumentListNode = NodeUtilsService.getInstance().getChildOfType(isFunctionNode.jjtGetParent(), ARGUMENT_LIST);

        if (argumentListNode != null) {
            for (int n = 0; n < argumentListNode.jjtGetNumChildren(); n++) {
                Node argumentNode         = argumentListNode.jjtGetChild(n);
                Variable argumentVariable = VariableFactory.getInstance().createVariable(INTEGER);
                argumentVariable
                    .setName(argumentNode.getValue().toString())
                    .setLine(argumentNode.getLine())
                    .setColumn(argumentNode.getColumn());
                functionArguments.add(argumentVariable);
            }
        }

        return functionArguments;
    }
}

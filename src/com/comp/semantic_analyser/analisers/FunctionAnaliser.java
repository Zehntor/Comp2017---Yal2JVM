package com.comp.semantic_analyser.analisers;

import com.comp.utils.services.NodeUtilsService;
import vendor.Node;
import java.util.List;
import java.util.ArrayList;
import com.comp.semantic_analyser.NodeType;
import com.comp.semantic_analyser.variables.*;
import com.comp.semantic_analyser.symbol_tables.FunctionSymbolTable;

import static com.comp.semantic_analyser.NodeType.*;
import static com.comp.semantic_analyser.variables.VariableType.ARRAY;
import static com.comp.semantic_analyser.variables.VariableType.INTEGER;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class FunctionAnaliser extends Analiser {

    private static final String DUPLICATE_ARGUMENT_ERROR_TEMPLATE = "Duplicate function argument '%s' found @ %s, %s";
    private static final String MISSING_RETURN_ASSIGNMENT         = "Missing return assignment for function '%s' @ %s, %s";

    public void analise(Node node) {
        if (symbolTableStack == null) {
            throw new RuntimeException(MISSING_SYMBOL_TABLE_STACK);
        }

        ((FunctionSymbolTable) symbolTableStack.peek()).setId(getFunctionId(node));

        List<Variable> arguments = getFunctionArguments(node);
        if (!arguments.isEmpty()) {
            checkDuplicateArguments(arguments);
        }
        ((FunctionSymbolTable) symbolTableStack.peek()).addArguments(arguments);

        createReturnVariable(node);
    }

    /**
     * When a FUNCTION_END node is found, this method is called to check if the function has its return assigned
     * @param node
     */
    public void checkReturnAssignment(Node node) {
        if (symbolTableStack == null) {
            throw new RuntimeException(MISSING_SYMBOL_TABLE_STACK);
        }

        FunctionSymbolTable symbolTable = ((FunctionSymbolTable) symbolTableStack.peek());
        Variable returnVariable = symbolTable.getReturnVariable();
        if (returnVariable == null) {
            return;
        }

        Object value = returnVariable.getValue();

        // TODO
        boolean
            isValidInteger = returnVariable.getType() == INTEGER, // && value != null,
            isValidArray   = returnVariable.getType() == ARRAY; // TODO  && ((List<Integer>) value).isEmpty()

        if (!isValidInteger  && !isValidArray) {
            addError(String.format(MISSING_RETURN_ASSIGNMENT, symbolTable.getId(), node.getLine(), node.getColumn()));
        }
    }

    /**
     * Finds and returns the function name
     * @param node
     * @return String
     */
    private String getFunctionId(Node node) {
        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            if (NodeType.fromString(node.jjtGetChild(n).toString()) == FUNCTION_ID) {
                return node.jjtGetChild(n).getValue().toString();
            }
        }

        return null;    // Should never happen
    }

    /**
     * Returns a list of function argument variables
     * @param node
     * @return List<Variable>
     */
    private List<Variable> getFunctionArguments(Node node) {
        List<Variable> functionArguments = new ArrayList<>();
        Node argsNode = NodeUtilsService.getInstance().getChildByType(node, ARGS);

        if (argsNode != null) {
            for (int n = 0; n < argsNode.jjtGetNumChildren(); n++) {
                boolean
                    nodeIsVarId     = NodeType.fromString(argsNode.jjtGetChild(n).toString()) == VAR_ID,
                    nextNodeIsArray = n < argsNode.jjtGetNumChildren() - 1 && NodeType.fromString(argsNode.jjtGetChild(n + 1).toString()) == VAR_IS_ARRAY;
                if (nodeIsVarId) {
                    Node varListIdNode = argsNode.jjtGetChild(n);
                    Variable variable;
                    if (nextNodeIsArray) {
                        variable = VariableFactory.getInstance().createVariable(ARRAY);
                        n++;
                    } else {
                        variable = VariableFactory.getInstance().createVariable(INTEGER);
                    }
                    variable
                        .setName(varListIdNode.getValue().toString())
                        .setLine(varListIdNode.getLine())
                        .setColumn(varListIdNode.getColumn());
                    functionArguments.add(variable);
                }
            }
        }

        return functionArguments;
    }

    /**
     * Checks a list of function argument variables for duplicates
     * If duplicates are found, then an error is added to the error list
     * @param arguments
     */
    private void checkDuplicateArguments(List<Variable> arguments) {
        for (int n = 0; n < arguments.size(); n++) {
            Variable argument = arguments.get(n);
            for (int m = n + 1; m < arguments.size(); m++) {
                Variable otherArgument = arguments.get(m);
                if (otherArgument.getName().equalsIgnoreCase(argument.getName())) {
                    addError(String.format(DUPLICATE_ARGUMENT_ERROR_TEMPLATE,
                        otherArgument.getName(),
                        otherArgument.getLine(),
                        otherArgument.getColumn()
                    ));
                }
            }
        }
    }

    private void createReturnVariable(Node node) {
        NodeType
            firstChildType  = NodeType.fromString(node.jjtGetChild(0).toString()),
            secondChildType = NodeType.fromString(node.jjtGetChild(1).toString());
        boolean
            returnIsInteger = firstChildType == RETURN_ID && secondChildType == FUNCTION_ID,
            returnIsArray   = firstChildType == RETURN_ID && secondChildType == RETURN_IS_ARRAY;

        Variable returnVariable;
        if (returnIsInteger) {
            ((FunctionSymbolTable) symbolTableStack.peek()).setReturnVariable(
                VariableFactory.getInstance().createVariable(INTEGER)
            );
        } else if (returnIsArray) {
            ((FunctionSymbolTable) symbolTableStack.peek()).setReturnVariable(
                VariableFactory.getInstance().createVariable(ARRAY)
            );
        }
    }
}

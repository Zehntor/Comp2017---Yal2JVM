package com.comp.semantic_analyser.analisers;

import com.comp.semantic_analyser.variables.*;
import vendor.Node;
import java.util.List;
import java.util.ArrayList;
import com.comp.semantic_analyser.NodeType;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class ModuleAnaliser extends Analiser {

    private static final String DUPLICATE_GLOBAL_VARIABLE_ERROR_TEMPLATE = "Duplicate global variable '%s' found @ %s, %s";

    public void analise(Node node) {
        if (symbolTableStack == null) {
            throw new RuntimeException(MISSING_SYMBOL_TABLE_STACK);
        }

        symbolTableStack.peek().setId(getModuleId(node));

        List<Variable> globalVariables = getGlobalVariables(node);
        checkDuplicateGlobalVariables(globalVariables);

        symbolTableStack.peek().addVariables(globalVariables);
    }

    /**
     * Finds and returns the function id
     * @param node
     * @return String
     */
    private String getModuleId(Node node) {
        Node moduleIdNode = node.getChildOfType(NodeType.MODULE_ID);

        if (moduleIdNode != null) {
            return moduleIdNode.getValueToString();
        }

        return null;    // Should never happen
    }

    private List<Variable> getGlobalVariables(Node node) {
        List<Variable> globalVariables = new ArrayList<>();

        List<Node> declarationNodes = node.getChildrenOfType(NodeType.DECLARATION);

        for (Node declarationNode : declarationNodes) {
            Node declarationIdNode = declarationNode.getChildOfType(NodeType.DECLARATION_ID);
            int
                line   = declarationIdNode.getLine(),
                column = declarationIdNode.getColumn();

            if (declarationNode.hasChildOfType(NodeType.ARRAY_SIZE)) {
                ArrayVariable variable = (ArrayVariable) VariableFactory.getInstance().createVariable(VariableType.ARRAY);

                variable.setName(declarationIdNode.getValueToString());
                variable.setSize(
                    Integer.parseInt(
                        declarationNode.getChildOfType(NodeType.ARRAY_SIZE).getChildOfType(NodeType.INTEGER).getValueToString()
                    )
                );

                variable
                    .setLine(line)
                    .setColumn(column);

                globalVariables.add(variable);
            } else {
                IntegerVariable variable = (IntegerVariable) VariableFactory.getInstance().createVariable(VariableType.INTEGER);

                variable.setName(
                    declarationNode.getChildOfType(NodeType.DECLARATION_ID).getValueToString()
                );

                if (declarationNode.hasChildOfType(NodeType.INIT_VAR)) {
                    variable.setValue(
                        Integer.parseInt(
                            declarationNode.getChildOfType(NodeType.INIT_VAR).getValueToString()
                        )
                    );
                }

                variable
                    .setLine(line)
                    .setColumn(column);

                globalVariables.add(variable);
            }

        }

        return globalVariables;
    }
    
    private void checkDuplicateGlobalVariables(List<Variable> globalVariables) {
        for (int n = 0; n < globalVariables.size(); n++) {
            Variable globalVariable = globalVariables.get(n);
            for (int m = n + 1; m < globalVariables.size(); m++) {
                Variable otherglobalVariable = globalVariables.get(m);
                if (otherglobalVariable.getName().equalsIgnoreCase(globalVariable.getName())) {
                    addError(String.format(DUPLICATE_GLOBAL_VARIABLE_ERROR_TEMPLATE,
                        otherglobalVariable.getName(),
                        otherglobalVariable.getLine(),
                        otherglobalVariable.getColumn()
                    ));
                }
            }
        }
    }
}

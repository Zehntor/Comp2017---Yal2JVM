package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.semantic_analyser.NodeType;
import com.comp.utils.services.NodeUtilsService;
import com.comp.semantic_analyser.symbol_tables.*;
import com.comp.semantic_analyser.SemanticAnaliser;
import com.comp.semantic_analyser.variables.Variable;
import com.comp.semantic_analyser.variables.ArrayVariable;

import static com.comp.semantic_analyser.NodeType.LHS;
import static com.comp.semantic_analyser.NodeType.RHS;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class ExprTestCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        Node
            lhsNode = node.getChildOfType(LHS),
            rhsNode = node.getChildOfType(RHS);

        addLhs(lhsNode);
        addRhs(rhsNode);

        return code.toString();
    }

    private void addLhs(Node node) {
        String variableName = node.getValueToString();

        if (node.hasChildren()) {   // It is an array
            loadVariable(node, variableName, true);
            translateIndex(node.jjtGetChild(0));
        } else {
            loadVariable(node, variableName, false);
        }
    }

    private void addRhs(Node node) {
        Node firstChild = node.jjtGetChild(0);
        if (firstChild.isOfType(NodeType.ARRAY_SIZE)) {
            Node firstGrandChild = firstChild.jjtGetChild(0);
            if (firstGrandChild.isOfType(NodeType.INTEGER)) {
                loadInteger(firstGrandChild.getValueToString());
            } else if (firstChild.isOfType(NodeType.SCALAR)) {
                translateScalar(node);
            }
        }
    }

    private void loadVariable(Node node, String variableName, boolean isArray) {
        SymbolTableTree symbolTableTree     = SemanticAnaliser.getInstance().getSymbolTableTree();
        ModuleSymbolTable moduleSymbolTable = (ModuleSymbolTable) symbolTableTree.getRoot();
        String moduleId                     = SemanticAnaliser.getInstance().getModuleId();

        if (moduleSymbolTable.hasVariable(variableName)) {
            // Global var
            if (isArray) {
                code.add(String.format("    getstatic %s/%s [I", moduleId, variableName));
            } else {
                code.add(String.format("    getstatic %s/%s I", moduleId, variableName));
            }
        } else {
            // Local var
            Node functionAncestorNode               = node.getAncestorOfType(NodeType.FUNCTION);
            String functionId                       = NodeUtilsService.getInstance().getFunctionId(functionAncestorNode);
            FunctionSymbolTable functionSymbolTable = (FunctionSymbolTable) symbolTableTree.findSymbolTable(functionId, SymbolTableType.FUNCTION);
            if (functionSymbolTable.hasVariable(variableName)) {
                int variableIndex = functionSymbolTable.getVariableIndex(variableName);
                if (isArray) {
                    if (variableIndex >= 0 && variableIndex <= 3) {
                        code.add(String.format("    aload_%s", variableIndex));
                    } else {
                        code.add(String.format("    aload %s", variableIndex));
                    }
                } else {
                    if (variableIndex >= 0 && variableIndex <= 3) {
                        code.add(String.format("    iload_%s", variableIndex));
                    } else {
                        code.add(String.format("    iload %s", variableIndex));
                    }
                }
            }
        }
    }

    private void translateIndex(Node node) {
        Node firstChild = node.jjtGetChild(0);
        if (firstChild.isOfType(NodeType.INTEGER)) {
            loadInteger(firstChild.getValueToString());
        }
    }

    private void translateScalar(Node node) {
        String variableName = node.jjtGetChild(0).getValueToString();
        if (node.jjtGetNumChildren() > 1) {
            loadVariable(node, variableName, true);
            code.add("arraylength");
        } else {
            SymbolTableTree symbolTableTree         = SemanticAnaliser.getInstance().getSymbolTableTree();
            Node functionAncestorNode               = node.getAncestorOfType(NodeType.FUNCTION);
            String functionId                       = NodeUtilsService.getInstance().getFunctionId(functionAncestorNode);
            FunctionSymbolTable functionSymbolTable = (FunctionSymbolTable) symbolTableTree.findSymbolTable(functionId, SymbolTableType.FUNCTION);
            Variable variable = functionSymbolTable.findVariable(variableName);
            boolean isArray = variable instanceof ArrayVariable;
            loadVariable(node, variableName, isArray);
        }
    }

    private void loadInteger(String value) {
        if (Integer.valueOf(value) <= 5) {
            code.add(String.format("    iconst_%s", value));
        } else {
            code.add(String.format("    bipush %s", value));
        }
    }
}

package com.comp.semantic_analyser;

import com.comp.common.Visitor;
import vendor.Node;
import java.util.List;
import java.util.ArrayList;
import com.comp.semantic_analyser.NodeType;
import com.comp.semantic_analyser.analisers.*;
import com.comp.semantic_analyser.symbol_tables.*;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class NodeVisitor implements Visitor {

    private final SymbolTableStack symbolTableStack = SymbolTableFactory.getInstance().createSymbolTableStack();
    private final List<String> errors = new ArrayList<>();

    public void visit(Node node) {
        NodeType nodeType = NodeType.fromString(node.toString());

        if (nodeType != null) {
            switch (nodeType) {
                case MODULE:
                    processModuleNode(node);
                    break;
                case FUNCTION:
                    processFunctionNode(node);
                    break;
                case IF:
                case ELSE:
                case WHILE:
                    processGeneralNode(node);
                    break;
                case MODULE_END:
                    symbolTableStack.pop();
                    break;
                case FUNCTION_END:
                    processFunctionEnd(node);
                    System.out.println(String.format("\tFound a %s, popping a symbol table", node));
                    break;
                case IF_END:
                case ELSE_END:
                case WHILE_END:
                    System.out.println(String.format("\tFound a %s, popping a symbol table", node));
                    symbolTableStack.pop();
                    break;
                case ASSIGN:
                    processAssignNode(node);
                    break;
            }
        }
    }

    public List<String> getErrors() {
        return errors;
    }

    /**
     * Processes a module node
     * Collects the module id and stores it in the the symbol table
     * @param node
     */
    private void processModuleNode(Node node) {
        ModuleAnaliser analiser       = (ModuleAnaliser) AnaliserFactory.getInstance().createAnaliser(AnaliserType.MODULE);

        ModuleSymbolTable symbolTable = (ModuleSymbolTable) SymbolTableFactory.getInstance().createSymbolTable(SymbolTableType.MODULE);
        symbolTableStack.push(symbolTable);
        analiser.setSymbolTableStack(symbolTableStack);

        analiser.analise(node);
        errors.addAll(analiser.getErrors());

        System.out.println(String.format("\tFound a %s, pushing a new symbol table: %s", node, analiser.getSymbolTableStack()));
    }

    /**
     * Processes a function node
     * Collects the function id and arguments and stores them in the the symbol table
     * @param node
     */
    private void processFunctionNode(Node node) {
        FunctionAnaliser analiser       = (FunctionAnaliser) AnaliserFactory.getInstance().createAnaliser(AnaliserType.FUNCTION);
        FunctionSymbolTable symbolTable = (FunctionSymbolTable) SymbolTableFactory.getInstance().createSymbolTable(SymbolTableType.FUNCTION);

        symbolTableStack.push(symbolTable);
        analiser.setSymbolTableStack(symbolTableStack);

        analiser.analise(node);
        errors.addAll(analiser.getErrors());

        System.out.println(String.format("\tFound a %s, pushing a new symbol table: %s", node, analiser.getSymbolTableStack()));
    }

    private void processFunctionEnd(Node node) {
        FunctionAnaliser analiser = (FunctionAnaliser) AnaliserFactory.getInstance().createAnaliser(AnaliserType.FUNCTION);
        analiser.setSymbolTableStack(symbolTableStack);

        analiser.checkReturnAssignment(node);
        errors.addAll(analiser.getErrors());

        System.out.println(String.format("\tFound a %s, popping a symbol table", node));
        symbolTableStack.pop();
    }

    /**
     * Processes a general node that needs a symbol table
     * @param node
     */
    private void processGeneralNode(Node node) {
        GeneralSymbolTable symbolTable = SymbolTableFactory.getInstance().createSymbolTable(SymbolTableType.GENERAL);

        System.out.println(String.format("\tFound a %s, pushing a new symbol table: %s", node, symbolTable));
        symbolTableStack.push(symbolTable);
    }

    private void processAssignNode(Node node) {
        AssignAnaliser analiser = (AssignAnaliser) AnaliserFactory.getInstance().createAnaliser(AnaliserType.ASSIGN);
        analiser.setSymbolTableStack(symbolTableStack);
        analiser.analise(node);
    }
}

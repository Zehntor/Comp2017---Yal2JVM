package com.comp.semantic_analyser;

import vendor.Node;
import java.util.List;
import java.util.ArrayList;
import com.comp.common.Visitor;
import com.comp.semantic_analyser.analisers.*;
import com.comp.semantic_analyser.symbol_tables.*;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class NodeVisitor implements Visitor {

    private final SymbolTableStack symbolTableStack       = SymbolTableFactory.getInstance().createSymbolTableStack();
    private final List<String> errors                     = new ArrayList<>();
    private final List<FunctionSymbolTable> functionCalls = new ArrayList<>();
    private SymbolTableTree symbolTableTree;

    public NodeVisitor setSymbolTableTree(SymbolTableTree symbolTableTree) {
        this.symbolTableTree = symbolTableTree;
        return this;
    }

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
                    break;
                case IF_END:
                case ELSE_END:
                case WHILE_END:
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

    public List<FunctionSymbolTable> getFunctionCalls() {
        return functionCalls;
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
        symbolTableTree.setRoot(symbolTable);
        analiser.setSymbolTableStack(symbolTableStack);

        analiser.analise(node);
        errors.addAll(analiser.getErrors());
    }

    /**
     * Processes a function node
     * Collects the function id and arguments and stores them in the the symbol table
     * @param node
     */
    private void processFunctionNode(Node node) {
        FunctionAnaliser analiser       = (FunctionAnaliser) AnaliserFactory.getInstance().createAnaliser(AnaliserType.FUNCTION);
        FunctionSymbolTable symbolTable = (FunctionSymbolTable) SymbolTableFactory.getInstance().createSymbolTable(SymbolTableType.FUNCTION);

        symbolTableStack.peek().addChild(symbolTable);
        symbolTableStack.push(symbolTable);
        analiser.setSymbolTableStack(symbolTableStack);
        analiser.setSymbolTableTree(symbolTableTree);

        analiser.analise(node);
        errors.addAll(analiser.getErrors());
    }

    /**
     * Processes a function end node
     * @param node
     */
    private void processFunctionEnd(Node node) {
        FunctionAnaliser analiser = (FunctionAnaliser) AnaliserFactory.getInstance().createAnaliser(AnaliserType.FUNCTION);
        analiser.setSymbolTableStack(symbolTableStack);

        analiser.checkReturnAssignment(node);
        errors.addAll(analiser.getErrors());

        symbolTableStack.pop();
    }

    /**
     * Processes a general node that needs a symbol table
     * @param node
     */
    private void processGeneralNode(Node node) {
        GeneralSymbolTable symbolTable = SymbolTableFactory.getInstance().createSymbolTable(SymbolTableType.GENERAL);
        symbolTableStack.peek().addChild(symbolTable);
        symbolTableStack.push(symbolTable);
    }

    /**
     * Processes an assign node
     * @param node
     */
    private void processAssignNode(Node node) {
        AssignAnaliser analiser = (AssignAnaliser) AnaliserFactory.getInstance().createAnaliser(AnaliserType.ASSIGN);
        analiser.setSymbolTableStack(symbolTableStack);
        analiser.analise(node);

        if (analiser.hasFunctionCalls(node)) {
            functionCalls.addAll(analiser.getFunctionCalls(node));
        }


    }
}

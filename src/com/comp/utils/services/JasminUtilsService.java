package com.comp.utils.services;

import com.comp.common.JasminVarType;
import com.comp.semantic_analyser.symbol_tables.SymbolTableTree;
import com.comp.utils.models.JasminUtils;
import vendor.Node;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class JasminUtilsService {

    /**
     * The one and only instance of this class
     */
    private static final JasminUtilsService instance = new JasminUtilsService();

    private static final JasminUtils jasminUtils = new JasminUtils();

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private JasminUtilsService() {
    }

    /**
     * Returns the one and only instance of this class
     * @return
     */
    public static JasminUtilsService getInstance() {
        return instance;
    }

    /**
     * Gets a jasmin argument list from a node
     * @param node
     * @return String
     */
    public String getJasminArgListFromNode(Node node) {
        return jasminUtils.getJasminArgListFromNode(node);
    }

    /**
     * Gets a jasmin return type from a node
     * @param node
     * @return String
     */
    public JasminVarType getJasminReturnTypeFromNode(Node node) {
        return jasminUtils.getJasminReturnTypeFromNode(node);
    }

    /**
     * Gets a jasmin argument list from a symbol table
     * @param symbolTableTree
     * @param functionId
     * @return String
     */
    public String getJasminArgListFromSymbolTable(SymbolTableTree symbolTableTree, String functionId) {
        return jasminUtils.getJasminArgListFromSymbolTable(symbolTableTree, functionId);
    }

    /**
     * Gets a jasmin return type from a symbol table
     * @param symbolTableTree
     * @param functionId
     * @return String
     */
    public String getJasminReturnTypeFromSymbolTable(SymbolTableTree symbolTableTree, String functionId) {
        return jasminUtils.getJasminReturnTypeFromSymbolTable(symbolTableTree, functionId);
    }
}

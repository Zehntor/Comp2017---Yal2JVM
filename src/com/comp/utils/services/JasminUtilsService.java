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

    private JasminUtilsService() {
    }

    public static JasminUtilsService getInstance() {
        return instance;
    }

    public String getJasminArgListFromNode(Node node) {
        return jasminUtils.getJasminArgListFromNode(node);
    }

    public JasminVarType getJasminReturnTypeFromNode(Node node) {
        return jasminUtils.getJasminReturnTypeFromNode(node);
    }

    public String getJasminArgListFromSymbolTable(SymbolTableTree symbolTableTree, String functionId) {
        return jasminUtils.getJasminArgListFromSymbolTable(symbolTableTree, functionId);
    }

    public String getJasminReturnTypeFromSymbolTable(SymbolTableTree symbolTableTree, String functionId) {
        return jasminUtils.getJasminReturnTypeFromSymbolTable(symbolTableTree, functionId);
    }
}

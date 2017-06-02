package com.comp.semantic_analyser;

import vendor.Node;
import java.util.List;
import java.util.ArrayList;
import com.comp.utils.services.NodeUtilsService;
import com.comp.semantic_analyser.symbol_tables.SymbolTableTree;
import com.comp.semantic_analyser.symbol_tables.SymbolTableFactory;

import static com.comp.semantic_analyser.symbol_tables.SymbolTableType.FUNCTION;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public final class SemanticAnaliser {

    private static final String MISSING_MAIN_FUNCTION = "Missing main function";

    private final SymbolTableTree symbolTableTree = SymbolTableFactory.getInstance().createSymbolTableTree();

    private String moduleName;

    /**
     * The one and only instance of this class
     */
    private static final SemanticAnaliser instance = new SemanticAnaliser();

    private final List<String> errors = new ArrayList<>();

    /**
     * Private constructor, so this class cannot be instantiated outside
     */
    private SemanticAnaliser() {
    }

    /**
     * Returns the one and only instance of this class
     * @return
     */
    public static SemanticAnaliser getInstance() {
        return instance;
    }

    /**
     * Walks the tree and builds the symbol tables
     * @param root
     */
    public void analise(Node root) {
        NodeVisitor nodeVisitor = new NodeVisitor();
        nodeVisitor.setSymbolTableTree(symbolTableTree);
        root.accept(nodeVisitor);

        checkMainFunctionExists();
        nodeVisitor.checkFunctionCalls();

        errors.addAll(nodeVisitor.getErrors());

        findModuleName(root);
    }

    public SymbolTableTree getSymbolTableTree() {
        return symbolTableTree;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    public String getModuleName() {
        return moduleName;
    }

    private void checkMainFunctionExists() {
        if (symbolTableTree.findSymbolTable("main", FUNCTION) == null) {
            errors.add(MISSING_MAIN_FUNCTION);
        }
    }

    private void findModuleName(Node node) {
        if (NodeUtilsService.getInstance().nodeIsOfType(node, NodeType.MODULE_ID)) {
            moduleName = node.getValue().toString();
        }

        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            findModuleName(node.jjtGetChild(n));
            if (moduleName != null) {
                return;
            }
        }
    }
}

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

    private String moduleId;

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
     * @return SemanticAnaliser
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

        findModuleId(root);
    }

    /**
     * Getter for the symbol table tree
     * @return SymbolTableTree
     */
    public SymbolTableTree getSymbolTableTree() {
        return symbolTableTree;
    }

    /**
     * @return true if there are errors; false otherwise
     */
    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    /**
     * Getter for errors
     * @return
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * @return String the module id
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * Checks that the main function exists
     * If it does not exist, an error is added to the errors list
     */
    private void checkMainFunctionExists() {
        if (symbolTableTree.findSymbolTable("main", FUNCTION) == null) {
            errors.add(MISSING_MAIN_FUNCTION);
        }
    }

    /**
     * Dives into the AST and finds the module id
     * @param node
     */
    private void findModuleId(Node node) {
        if (node.isOfType(NodeType.MODULE_ID)) {
            moduleId = node.getValue().toString();
            return;
        }

        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            findModuleId(node.jjtGetChild(n));
            if (moduleId != null) {
                return;
            }
        }
    }
}

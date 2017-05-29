package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;
import com.comp.semantic_analyser.SemanticAnaliser;
import com.comp.semantic_analyser.symbol_tables.FunctionSymbolTable;

import static com.comp.semantic_analyser.NodeType.FUNCTION_ID;
import static com.comp.semantic_analyser.symbol_tables.SymbolTableType.FUNCTION;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class FunctionBodyCodeGenerator extends CodeGenerator {

    /**
     * @param node FUNCTION_BODY node
     * @return
     */
    @Override
    public String generate(Node node) {
        addLimits(node);

        return code.toString();
    }

    private void addLimits(Node node) {
        String functionId = getFunctionId(node);

        FunctionSymbolTable symbolTable = (FunctionSymbolTable) SemanticAnaliser.getInstance().getSymbolTableTree().findSymbolTable(functionId, FUNCTION);

        int limit = symbolTable.getArguments().size() + symbolTable.getVariables().size();
        if (symbolTable.getReturnVariable() != null) {
            limit++;
        }

        code
            .add(String.format("    .limit stack %s", limit))
            .add(String.format("    .limit locals %s", limit));
    }

    private String getFunctionId(Node node) {
        Node functionIdNode = NodeUtilsService.getInstance().getChildByType(node.jjtGetParent(), FUNCTION_ID);
        return functionIdNode.getValue().toString();
    }
}

package com.comp.code_generator.generators;

import vendor.Node;
import java.util.List;
import com.comp.utils.services.NodeUtilsService;
import com.comp.semantic_analyser.SemanticAnaliser;

import static com.comp.semantic_analyser.NodeType.*;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 *
 * Generates code for modules
 */
public final class ModuleCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        addHeader(node);
        addDeclarations(node);
        addClassConstructor(node);

        return code.toString();
    }

    /**
     * Adds the class header
     * @param node
     */
    private void addHeader(Node node) {
        Node moduleIdNode = node.getChildOfType(MODULE_ID);
        String moduleId   = moduleIdNode.getValueToString();

        code
            .add(String.format(".class public %s", moduleId))
            .add(".super java/lang/Object");
    }

    /**
     * Adds the defautl constructor code
     */
    private void addDefaultConstructor() {
        code
            .add("")
            .add("; Default constructor")
            .add(".method public <init>()V")
            .add("    aload_0 ; push this")
            .add("    invokespecial java/lang/Object/<init>()V ; call super")
            .add("    return")
            .add(".end method");
    }

    /**
     * Adds the class constructor, initializing global variables
     * @param node
     */
    private void addClassConstructor(Node node) {
        code
            .add("")
            .add("; Class constructor")
            .add(".method static public <clinit>()V")
            .add("    .limit stack 2")
            .add("    .limit locals 0");

        addStaticArrayInitializations(node);

        code
            .add("")
            .add("    return")
            .add(".end method");
    }

    /**
     * Adds the static declarations code
     * @param node
     */
    private void addDeclarations(Node node) {
        List<Node> declarationNodes = node.getChildrenOfType(DECLARATION);

        if (!declarationNodes.isEmpty()) {
            code.add("");
            code.add("; Static declarations");
            for (Node declarationNode : declarationNodes) {
                addDeclaration(declarationNode);
            }
        }
    }

    /**
     * Adds a single static declaration
     * @param node
     */
    private void addDeclaration(Node node) {
        String declarationId = node.getChildOfType(DECLARATION_ID).getValueToString();

        if (NodeUtilsService.getInstance().declarationNodeIsArray(node)) {
            code.add(String.format(".field static %s [I", declarationId));
        } else {
            if (node.hasChildOfType(INIT_VAR)) {
                String signal = "";
                if (node.hasChildOfType(ADD_SUB_OP)) {
                    signal = node.getChildOfType(ADD_SUB_OP).getValueToString();
                }
                String value = node.getChildOfType(INIT_VAR).getValueToString();
                code.add(String.format(".field static %s I = %s%s",
                    declarationId,
                    signal,
                    value
                ));
            } else {
                code.add(String.format(".field static %s I", declarationId));
            }
        }
    }

    /**
     * Adds the static array initializations
     * @param node
     */
    private void addStaticArrayInitializations(Node node) {
        List<Node> declarationNodes = node.getChildrenOfType(DECLARATION);

        for (Node declarationNode : declarationNodes) {
            if (NodeUtilsService.getInstance().declarationNodeIsArray(declarationNode)) {
                Node
                    arraySizeNode = declarationNode.getChildOfType(ARRAY_SIZE),
                    integerNode   = arraySizeNode.getChildOfType(INTEGER);
                String size = integerNode.getValueToString();

                code.add("");
                if (Integer.valueOf(size) <= 5) {
                    code.add(String.format("    iconst_%s", size));
                } else {
                    code.add(String.format("    bipush %s", size));
                }
                code
                    .add("    newarray int")
                    .add(String.format("    putstatic %s/%s [I",
                        SemanticAnaliser.getInstance().getModuleId(),
                        declarationNode.getChildOfType(DECLARATION_ID).getValueToString()
                    ));
            }
        }
    }
}

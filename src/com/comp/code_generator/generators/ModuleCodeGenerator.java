package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import java.util.List;

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
        addDefaultConstructor();

        return code.toString();
    }

    private void addHeader(Node node) {
        Node moduleIdNode = NodeUtilsService.getInstance().getChildOfType(node, MODULE_ID);
        String moduleName = moduleIdNode.getValue().toString();

        code
            .add(String.format(".class public %s", moduleName))
            .add(".super java/lang/Object");
    }

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

    private void addClassConstructor() {
        code
            .add("")
            .add("; Class constructor")
            .add(".method static public <clinit>()V")
            .add("    .limit stack 0")
            .add("    .limit locals 0")
            .add("    return")
            .add(".end method");
    }

    private void addDeclarations(Node node) {
        List<Node> declarationNodes = NodeUtilsService.getInstance().getChildrenOfType(node, DECLARATION);

        if (!declarationNodes.isEmpty()) {
            code.add("");
            code.add("; Static declarations");
            for (Node declarationNode : declarationNodes) {
                addDeclaration(declarationNode);
            }
        }
    }

    private void addDeclaration(Node node) {
        for (int n = 0; n < node.jjtGetNumChildren(); n++) {
            Node childNode = node.jjtGetChild(n);
            if (NodeUtilsService.getInstance().nodeIsOfType(childNode, DECLARATION_ID)) {
                if (n < node.jjtGetNumChildren() - 1) {
                    Node nextNode = node.jjtGetChild(n + 1);
                    if (NodeUtilsService.getInstance().nodeIsOfType(nextNode, INIT_VAR)) {
                        code.add(String.format(".field static %s I", childNode.getValue()));
                    } else {
                        boolean
                            nodeIsArraySize = NodeUtilsService.getInstance().nodeIsOfType(nextNode, ARRAY_SIZE),
                            nodeIsIsArray   = NodeUtilsService.getInstance().nodeIsOfType(nextNode, IS_ARRAY);
                        if (nodeIsArraySize || nodeIsIsArray) {
                            code.add(String.format(".field static %s [I", childNode.getValue()));
                        }
                    }
                }
            }
        }
    }
}

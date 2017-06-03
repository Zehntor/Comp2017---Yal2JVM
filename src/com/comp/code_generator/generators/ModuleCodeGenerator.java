package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.MODULE_ID;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 *
 * Generates code for modules
 */
public final class ModuleCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        Node moduleIdNode = NodeUtilsService.getInstance().getChildByType(node, MODULE_ID);
        String moduleName = moduleIdNode.getValue().toString();

        addHeader(moduleName);
        addDefaultConstructor();

        return code.toString();
    }

    private void addHeader(String moduleName) {
        code
            .add(String.format(".class public %s", moduleName))
            .add(".super java/lang/Object");
    }

    private void addDefaultConstructor() {
        code
            .add("")
            .add("; Default constructor")
            .add(".method static public <clinit>()V")
            .add("    .limit stack 0")
            .add("    .limit locals 0")
            .add("    return")
            .add(".end method");
    }
}

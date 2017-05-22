package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.MODULE_ID;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
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
        code.add(String.format(".class public %s", moduleName));
        code.add(".super java/lang/Object");
    }

    private void addDefaultConstructor() {
        code.add("");
        code.add("; Default constructor");
        code.add(".method public <init>()V");
        code.add("    aload_0");
        code.add("    invokespecial java/lang/Object/<init>()V");
        code.add("    return");
        code.add(".end method");
    }
}

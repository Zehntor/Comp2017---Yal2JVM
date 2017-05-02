package com.comp.code_generator.generators;

import vendor.Node;
import com.comp.utils.services.NodeUtilsService;

import static com.comp.semantic_analyser.NodeType.MODULE_ID;
import static com.comp.code_generator.Instruction.CLASS_PUBLIC;
import static com.comp.code_generator.Instruction.SUPER_JAVA_LANG_OBJECT;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class ModuleCodeGenerator extends CodeGenerator {

    @Override
    public String generate(Node node) {
        Node moduleIdNode = NodeUtilsService.getInstance().getChildByType(node, MODULE_ID);
        String moduleName   = moduleIdNode.getValue().toString();

        code.add(String.format(CLASS_PUBLIC.toString(), moduleName));
        code.add(SUPER_JAVA_LANG_OBJECT.toString());

        return code.toString();
    }
}

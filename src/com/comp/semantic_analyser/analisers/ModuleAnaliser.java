package com.comp.semantic_analyser.analisers;

import vendor.Node;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public class ModuleAnaliser extends Analiser {

    public void analise(Node node) {
        if (symbolTableStack == null) {
            throw new RuntimeException(MISSING_SYMBOL_TABLE_STACK);
        }
    }
}

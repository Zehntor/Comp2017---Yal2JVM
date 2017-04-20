package com.comp.semantic_analyser.node_visitor;

import vendor.Node;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public interface Visitor {
    void visit(Node node);
}

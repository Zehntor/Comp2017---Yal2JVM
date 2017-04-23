package com.comp.common;

import vendor.Node;
import java.util.List;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public interface Visitor {
    void visit(Node node);
    List<String> getErrors();
}

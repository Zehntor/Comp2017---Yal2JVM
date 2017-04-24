package com.comp.code_generator;

/**
 * @author Ricardo Wragg Freitas <ei95036@fe.up.pt> 199502870
 */
public enum Instruction {

    CLASS_PUBLIC(".class public %s"),
    SUPER_JAVA_LANG_OBJECT(".super java/lang/Object");

    private final String value;

    Instruction(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

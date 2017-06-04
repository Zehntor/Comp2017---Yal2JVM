.class public yal_003
.super java/lang/Object

; Default constructor
.method public <init>()V
    aload_0 ; push this
    invokespecial java/lang/Object/<init>()V ; call super
    return
.end method

; Function cenas
.method public static cenas(II)V
    .limit stack 2
    .limit locals 2
    iload 0
    invokestatic io/println(I)V
    iload 1
    invokestatic io/println(I)V

    return
.end method

; Function main
.method public static main([Ljava/lang/String;)V
    .limit stack 2
    .limit locals 2
    ldc 42
    ldc 45
    invokestatic yal_003/cenas(II)V

    return
.end method

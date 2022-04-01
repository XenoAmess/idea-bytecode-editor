package com.github.pshirshov.conversion;

public interface DisassembleStrategy {

    String getName();

    Assembler getAssembler();

    Disassembler getDisassembler();

}

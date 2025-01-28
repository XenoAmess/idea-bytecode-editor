package com.github.pshirshov.conversion;

import com.intellij.openapi.fileTypes.FileType;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public interface DisassembleStrategy {

    @NotNull
    String getName();

    @NotNull
    Assembler getAssembler();

    @NotNull
    Disassembler getDisassembler();

    @NotNull
    String getFileNameSuffix();

    @NotNull
    FileType getFileType();

}

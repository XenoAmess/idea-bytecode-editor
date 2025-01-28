package com.github.pshirshov.conversion.impl.asm;

import com.github.pshirshov.conversion.DisassembleStrategyEnum;
import com.intellij.ide.highlighter.JavaClassFileType;
import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;


public final class AsmFileType implements FileType {
    public static final AsmFileType INSTANCE = new AsmFileType();

    private AsmFileType() {
    }

    @Override
    public @NotNull String getName() {
        return DisassembleStrategyEnum.ASM.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DisassembleStrategyEnum.ASM.getName();
    }

    @Override
    public @Nls @NotNull String getDisplayName() {
        return DisassembleStrategyEnum.ASM.getName();
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return DisassembleStrategyEnum.ASM.getFileNameSuffix();
    }

    @Override
    public Icon getIcon() {
        return JavaClassFileType.INSTANCE.getIcon();
    }

    @Override
    public boolean isBinary() {
        return true;
    }
}

package com.github.pshirshov.conversion.impl.jasmin;

import com.github.pshirshov.conversion.DisassembleStrategyEnum;
import com.intellij.ide.highlighter.JavaClassFileType;
import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;


public final class JasminFileType implements FileType {
    public static final JasminFileType INSTANCE = new JasminFileType();

    private JasminFileType() {
    }

    @Override
    public @NotNull String getName() {
        return DisassembleStrategyEnum.JASMIN.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DisassembleStrategyEnum.JASMIN.getName();
    }

    @Override
    public @Nls @NotNull String getDisplayName() {
        return DisassembleStrategyEnum.JASMIN.getName();
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return DisassembleStrategyEnum.JASMIN.getFileNameSuffix();
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

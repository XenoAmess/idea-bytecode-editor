package com.github.pshirshov.conversion.impl.krakatau;

import com.github.pshirshov.conversion.DisassembleStrategyEnum;
import com.intellij.ide.highlighter.JavaClassFileType;
import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;


public final class KrakatauFileType implements FileType {
    public static final KrakatauFileType INSTANCE = new KrakatauFileType();

    private KrakatauFileType() {
    }

    @Override
    public @NotNull String getName() {
        return DisassembleStrategyEnum.KRAKATAU.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DisassembleStrategyEnum.KRAKATAU.getName();
    }

    @Override
    public @Nls @NotNull String getDisplayName() {
        return DisassembleStrategyEnum.KRAKATAU.getName();
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return DisassembleStrategyEnum.KRAKATAU.getFileNameSuffix();
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

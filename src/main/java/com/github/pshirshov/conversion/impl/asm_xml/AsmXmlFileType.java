package com.github.pshirshov.conversion.impl.asm_xml;

import com.github.pshirshov.conversion.DisassembleStrategyEnum;
import com.intellij.ide.highlighter.JavaClassFileType;
import com.intellij.openapi.fileTypes.FileType;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;


public final class AsmXmlFileType implements FileType {
    public static final AsmXmlFileType INSTANCE = new AsmXmlFileType();

    private AsmXmlFileType() {
    }

    @Override
    public @NotNull String getName() {
        return DisassembleStrategyEnum.ASM_XML.getName();
    }

    @Override
    public @NotNull String getDescription() {
        return DisassembleStrategyEnum.ASM_XML.getName();
    }

    @Override
    public @Nls @NotNull String getDisplayName() {
        return DisassembleStrategyEnum.ASM_XML.getName();
    }

    @Override
    public @NotNull String getDefaultExtension() {
        return DisassembleStrategyEnum.ASM_XML.getFileNameSuffix();
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

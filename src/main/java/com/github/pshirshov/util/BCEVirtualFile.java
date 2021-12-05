package com.github.pshirshov.util;

import com.github.pshirshov.conversion.DisassembleStrategy;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.BinaryLightVirtualFile;
import org.jetbrains.annotations.NotNull;

public class BCEVirtualFile extends BinaryLightVirtualFile {

    private DisassembleStrategy disassembleStrategy;

    private PsiElement element;

    public BCEVirtualFile(
            String name,
            FileType fileType,
            byte[] content,
            PsiElement element,
            DisassembleStrategy disassembleStrategy
    ) {
        super(name, fileType, content);
        this.element = element;
        this.disassembleStrategy = disassembleStrategy;
    }


    @Override
    @NotNull
    public String getPresentableName() {
        final String[] parts = getPath().split("/");
        return parts[parts.length - 1];
    }


    public PsiElement getElement() {
        return element;
    }


    public void setElement(PsiElement element) {
        this.element = element;
    }


    public DisassembleStrategy getDisassembleStrategy() {
        return disassembleStrategy;
    }


    public void setDisassembleStrategy(DisassembleStrategy disassembleStrategy) {
        this.disassembleStrategy = disassembleStrategy;
    }
}

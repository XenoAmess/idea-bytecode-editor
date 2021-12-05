package com.github.pshirshov.vfs;

import com.github.pshirshov.conversion.DisassembleStrategyEnum;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.testFramework.BinaryLightVirtualFile;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

public class DisassembledVirtualFile extends BinaryLightVirtualFile {

    @Getter
    @Setter
    @NotNull
    private PsiElement element;

    @Getter
    @Setter
    @NotNull
    private VirtualFile originalClassVirtualFile;

    @Getter
    @Setter
    @NotNull
    private DisassembleStrategyEnum disassembleStrategyEnum;

    public DisassembledVirtualFile(
            String name,
            FileType fileType,
            byte[] content,
            @NotNull PsiElement element,
            @NotNull VirtualFile originalClassVirtualFile,
            @NotNull DisassembleStrategyEnum disassembleStrategyEnum
    ) {
        super(name, fileType, content);
        this.element = element;
        this.originalClassVirtualFile = originalClassVirtualFile;
        this.disassembleStrategyEnum = disassembleStrategyEnum;
    }


    @Override
    @NotNull
    public String getPresentableName() {
        final String[] parts = getPath().split("/");
        return parts[parts.length - 1];
    }

}

package com.github.pshirshov.conversion;

import com.github.pshirshov.conversion.impl.asm.AsmDisassembler;
import com.github.pshirshov.conversion.impl.asm.AsmFileType;
import com.github.pshirshov.conversion.impl.asm_xml.AsmXmlAssembler;
import com.github.pshirshov.conversion.impl.asm_xml.AsmXmlDisassembler;
import com.github.pshirshov.conversion.impl.asm_xml.AsmXmlFileType;
import com.github.pshirshov.conversion.impl.jasmin.JasminDisassembler;
import com.github.pshirshov.conversion.impl.jasmin.JasminFileType;
import com.github.pshirshov.conversion.impl.krakatau.KrakatauAssembler;
import com.github.pshirshov.conversion.impl.krakatau.KrakatauDisassembler;
import com.github.pshirshov.conversion.impl.krakatau.KrakatauFileType;
import com.intellij.openapi.fileTypes.FileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@AllArgsConstructor
public enum DisassembleStrategyEnum implements DisassembleStrategy {

    /**
     * jasmin
     */
    JASMIN(
            DisassembleStrategyConstant.STRING_JASMIN,
            null,
            JasminDisassembler.INSTANCE,
            JasminFileType.INSTANCE
    ),

    /**
     * asm
     */
    ASM(
            DisassembleStrategyConstant.STRING_ASM,
            null,
            AsmDisassembler.INSTANCE,
            AsmFileType.INSTANCE
    ),

    /**
     * asm xml
     */
    ASM_XML(
            DisassembleStrategyConstant.STRING_ASM_XML,
            AsmXmlAssembler.INSTANCE,
            AsmXmlDisassembler.INSTANCE,
            AsmXmlFileType.INSTANCE
    ),

    /**
     * krakatau
     */
    KRAKATAU(
            DisassembleStrategyConstant.STRING_KRAKATAU,
            KrakatauAssembler.INSTANCE,
            KrakatauDisassembler.INSTANCE,
            KrakatauFileType.INSTANCE
    );

    @Getter
    @NotNull
    private final String name;

    @Getter
    @Nullable
    private final Assembler assembler;

    @Getter
    @NotNull
    private final Disassembler disassembler;

    @Getter
    @NotNull
    private final FileType fileType;

    @NotNull
    @Override
    public String getFileNameSuffix(){
        return this.getName();
    }

}

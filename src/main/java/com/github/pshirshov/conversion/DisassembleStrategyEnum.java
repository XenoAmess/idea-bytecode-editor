package com.github.pshirshov.conversion;

import com.github.pshirshov.conversion.impl.asm_xml.AsmXmlAssembler;
import com.github.pshirshov.conversion.impl.asm_xml.AsmXmlDisassembler;
import com.github.pshirshov.conversion.impl.jasmin.JasminAssembler;
import com.github.pshirshov.conversion.impl.jasmin.JasminDisassembler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
public enum DisassembleStrategyEnum {

    /**
     * jasmin
     */
    JASMIN(
            DisassembleStrategyConstant.STRING_JASMIN,
            JasminAssembler.INSTANCE,
            JasminDisassembler.INSTANCE
    ),

    /**
     * asm xml
     */
    ASM_XML(
            DisassembleStrategyConstant.STRING_ASM_XML,
            AsmXmlAssembler.INSTANCE,
            AsmXmlDisassembler.INSTANCE
    );

    @Getter
    @NotNull
    private final String name;

    @Getter
    @NotNull
    private final Assembler assembler;

    @Getter
    @NotNull
    private final Disassembler disassembler;

}

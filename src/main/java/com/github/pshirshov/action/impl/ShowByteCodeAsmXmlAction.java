package com.github.pshirshov.action.impl;

import com.github.pshirshov.action.AbstractShowByteCodeAction;
import com.github.pshirshov.conversion.DisassembleStrategyEnum;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * @author XenoAmess
 */
public class ShowByteCodeAsmXmlAction extends AbstractShowByteCodeAction {

    @Getter(onMethod_ = {@Override, @NotNull})
    private final DisassembleStrategyEnum disassembleStrategyEnum = DisassembleStrategyEnum.ASM_XML;

}

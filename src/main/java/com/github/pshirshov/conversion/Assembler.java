package com.github.pshirshov.conversion;

import java.io.InputStream;
import java.io.OutputStream;

import org.jetbrains.annotations.NotNull;

public interface Assembler {

    void assemble(
            @NotNull InputStream inputStream,
            @NotNull OutputStream outputStream
    ) throws Exception;
}

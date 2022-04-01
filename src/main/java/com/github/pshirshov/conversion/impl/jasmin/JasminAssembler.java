package com.github.pshirshov.conversion.impl.jasmin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.github.pshirshov.conversion.Assembler;
import jasmin.Main;
import org.jetbrains.annotations.NotNull;

@Deprecated
public class JasminAssembler implements Assembler {

    public static final JasminAssembler INSTANCE = new JasminAssembler();

    private JasminAssembler() {
    }

    @Override
    public void assemble(
            @NotNull InputStream inputStream,
            @NotNull OutputStream outputStream
    ) throws IOException {
        try (
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ) {
            Main.assemble(
                    inputStream,
                    byteArrayOutputStream,
                    true
            );
            if (byteArrayOutputStream.size() == 0) {
                throw new JasminFailedException("jasmin failed!");
            }
            byteArrayOutputStream.writeTo(outputStream);
        }
    }
}

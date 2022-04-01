package com.github.pshirshov.conversion.impl.krakatau;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;

import com.github.pshirshov.conversion.Assembler;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;

public class KrakatauAssembler implements Assembler {

    public static final KrakatauAssembler INSTANCE = new KrakatauAssembler();

    private KrakatauAssembler() {
    }

    @Override
    public void assemble(
            @NotNull InputStream inputStream,
            @NotNull OutputStream outputStream
    ) throws IOException,
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException {
        String inputKrakatauString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        Class<?> krakatauUtilClass = KrakatauFetchUtil.assureKrakatauDownloaded();
        Method method = krakatauUtilClass.getMethod(
                "assemble",
                String.class
        );
        byte[] bytes = (byte[]) method.invoke(null, inputKrakatauString);
        outputStream.write(bytes);
    }

}

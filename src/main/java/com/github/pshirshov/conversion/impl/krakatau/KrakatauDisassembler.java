package com.github.pshirshov.conversion.impl.krakatau;

import com.github.pshirshov.conversion.Disassembler;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class KrakatauDisassembler implements Disassembler {

    public static final KrakatauDisassembler INSTANCE = new KrakatauDisassembler();

    private KrakatauDisassembler() {
    }

    @Override
    public String disassemble(byte[] classfile) throws
            IOException,
            ClassNotFoundException,
            NoSuchMethodException,
            InvocationTargetException,
            IllegalAccessException {
        Class<?> krakatauUtilClass = KrakatauFetchUtil.assureKrakatauDownloaded();
        Method method = krakatauUtilClass.getMethod(
                "disassemble",
                byte[].class
        );
        return (String) method.invoke(null, (Object) classfile);
    }

}

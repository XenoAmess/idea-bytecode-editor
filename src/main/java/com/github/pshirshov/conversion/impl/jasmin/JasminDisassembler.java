package com.github.pshirshov.conversion.impl.jasmin;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.github.pshirshov.conversion.Disassembler;
import com.github.pshirshov.util.IdeaUtils;
import com.intellij.openapi.editor.Document;
import com.xenoamess.org.objectweb.asm.v_9_2.ClassReader;

public class JasminDisassembler implements Disassembler {

    public static final JasminDisassembler INSTANCE = new JasminDisassembler();

    private JasminDisassembler() {
    }

    @Override
    public String disassemble(byte[] classfile) {
        final ClassReader classReader = new ClassReader(classfile);
        final StringWriter writer = new StringWriter();

        try (final PrintWriter printWriter = new PrintWriter(writer)) {
            int flags = ClassReader.EXPAND_FRAMES;
            classReader.accept(new JasminifierClassAdapter(printWriter), flags);
        }

        return writer.toString();

    }


    @Override
    public int getLineOffset(
            String assembledByteCodeString,
            Document document,
            int lineNumber
    ) {
        return IdeaUtils.findSubstringOffset(
                assembledByteCodeString,
                document,
                lineNumber,
                ".line "
        );
    }
}

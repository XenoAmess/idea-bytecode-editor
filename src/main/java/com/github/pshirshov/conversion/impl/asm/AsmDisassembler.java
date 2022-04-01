package com.github.pshirshov.conversion.impl.asm;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.github.pshirshov.conversion.Disassembler;
import com.github.pshirshov.util.IdeaUtils;
import com.intellij.openapi.editor.Document;
import com.xenoamess.org.objectweb.asm.v_9_2.ClassReader;
import com.xenoamess.org.objectweb.asm.v_9_2.util.Textifier;
import com.xenoamess.org.objectweb.asm.v_9_2.util.TraceClassVisitor;

public class AsmDisassembler implements Disassembler {

    public static final AsmDisassembler INSTANCE = new AsmDisassembler();

    private AsmDisassembler() {

    }

    @Override
    public String disassemble(byte[] classfile) {
        final ClassReader classReader = new ClassReader(classfile);
        final StringWriter writer = new StringWriter();

        try (final PrintWriter printWriter = new PrintWriter(writer)) {
            classReader.accept(new TraceClassVisitor(null, new Textifier(), printWriter), 0);
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
                "LINENUMBER "
        );
    }
}

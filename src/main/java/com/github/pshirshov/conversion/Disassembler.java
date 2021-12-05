package com.github.pshirshov.conversion;

import com.intellij.openapi.editor.Document;

public interface Disassembler {

    String disassemble(byte[] classfile);

    /**
     * get the offset that, in idea ide, where the cursor should move to, after the string change.
     *
     * if you still have no idea what is, just remain return 0.
     *
     * @param assembledByteCodeString assembledByteCodeString
     * @param document idea document
     * @param lineNumber lineNumber
     * @return offset
     */
    default int getLineOffset(String assembledByteCodeString, Document document, int lineNumber) {
        return 0;
    }

}

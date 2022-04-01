package com.github.pshirshov.conversion.impl.asm_xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.github.pshirshov.conversion.Assembler;
import com.xenoamess.org.objectweb.asm.v_9_2.ClassWriter;
import com.xenoamess.org.objectweb.asm.xml.ASMContentHandler;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.xml.sax.SAXException;

@Slf4j
public class AsmXmlAssembler implements Assembler {

    public static final AsmXmlAssembler INSTANCE = new AsmXmlAssembler();

    private AsmXmlAssembler() {
    }

    @Override
    public void assemble(
            @NotNull InputStream inputStream,
            @NotNull OutputStream outputStream
    ) throws IOException, SAXException, ParserConfigurationException {
        final ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        final ASMContentHandler handler = new ASMContentHandler(classWriter);
        final SAXParserFactory factory = SAXParserFactory.newInstance();
        final SAXParser saxParser = factory.newSAXParser();
        saxParser.parse(inputStream, handler);
        final byte[] bytes = classWriter.toByteArray();
        outputStream.write(bytes);
    }

}

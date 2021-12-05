package com.github.pshirshov.conversion.impl.asm_xml;

import java.io.StringWriter;
import java.io.Writer;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.github.pshirshov.conversion.Disassembler;
import com.github.pshirshov.util.IdeaUtils;
import com.xenoamess.org.objectweb.asm.v_9_2.ClassReader;
import com.xenoamess.org.objectweb.asm.v_9_2.xml.SAXClassAdapter;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class AsmXmlDisassembler implements Disassembler {

    public static final AsmXmlDisassembler INSTANCE = new AsmXmlDisassembler();

    private AsmXmlDisassembler() {
    }

    @Override
    public String disassemble(byte[] classfile) {
        final ClassReader classReader = new ClassReader(classfile);


        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation impl = builder.getDOMImplementation();
            Document doc = impl.createDocument(null, null, null);
            SaxToDomHandler handlers = new SaxToDomHandler(doc);
            classReader.accept(new SAXClassAdapter(handlers, true), ClassReader.EXPAND_FRAMES);
            return prettyPrint(doc);

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }


    }


    public static String prettyPrint(Document xml) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.setOutputProperty(OutputKeys.METHOD, "xml");
        tf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));
        return out.toString();
    }


    @Override
    public int getLineOffset(
            String assembledByteCodeString,
            com.intellij.openapi.editor.Document document,
            int lineNumber
    ) {
        return IdeaUtils.findSubstringOffset(
                assembledByteCodeString,
                document,
                lineNumber,
                "<LineNumber line=\""
        );
    }


}

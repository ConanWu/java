package cn.wl.test.xmlDocument;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

public class StringDocumentDom4J {

    public Document getStringToDocument(String content) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(content);
        }catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public String getDocumentToString(Document document) {
        return document.asXML();
    }
}

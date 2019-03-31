package cn.wl.test.xmlDocument;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

public class StringToDocument {

    public Document getStringtoDocument(String content) {
        Document document = null;
        StringReader stringReader = new StringReader(content);
        InputSource inputSource = new InputSource(stringReader);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder documentBuilder = factory.newDocumentBuilder();
            document = documentBuilder.parse(inputSource);
        }catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return document;
    }

    public String getDocumentToString(Document document) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        String xmlResult = null;
        try {
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty("encoding","GB23121");//解决中文问题，试过用GBK不行
            ByteArrayOutputStream  byteArrayOutputStream = new ByteArrayOutputStream();
            transformer.transform(new DOMSource(document), new StreamResult(byteArrayOutputStream));
            xmlResult = byteArrayOutputStream.toString();
        }catch (TransformerException e) {
            e.printStackTrace();
        }
        return xmlResult;
    }

}

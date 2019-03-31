package cn.wl.test.xmlDocument;

import cn.wl.test.xmlDocument.StringDocumentDom4J;
import cn.wl.test.xmlDocument.StringToDocument;
import org.apache.commons.jxpath.JXPathContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.List;

public class StringToDocumentTest {
    private String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<catalog>\n" +
            "  <cd gender=\"male\">\n" +
            "    <title>Empire Burlesque</title>\n" +
            "    <artist>Bob Dylan</artist>\n" +
            "    <country>USA</country>\n" +
            "    <company>Columbia</company>\n" +
            "    <price>10.90</price>\n" +
            "    <year>1985</year>\n" +
            "  </cd>\n" +
            "  <cd gender=\"female\">\n" +
            "    <title>Hide your heart</title>\n" +
            "    <artist>Bonnie Tyler</artist>\n" +
            "    <country>UK</country>\n" +
            "    <company>CBS Records</company>\n" +
            "    <price>9.90</price>\n" +
            "    <year>1988</year>\n" +
            "  </cd>\n" +
            "</catalog>";

    @Test
    public void testGetStringToDocumentDom4J() {
        StringDocumentDom4J stringDocumentDom4J = new StringDocumentDom4J();
        Document document = stringDocumentDom4J.getStringToDocument(xmlString);
        String titlePath = "/catalog/cd/title";
        String titleSpecificPath = "/catalog/cd[@gender='female']/title";
        String titleElement = ((List<Element>) document.selectNodes(titlePath)).get(0).getStringValue();
        String titleSpecificSting = document.selectSingleNode(titleSpecificPath).getStringValue();
        System.out.println(titleElement);
        System.out.println(titleSpecificSting);

    }

    @Test
    public void testGetStringToDocument() {
        StringToDocument stringToDocument = new StringToDocument();
        org.w3c.dom.Document document = stringToDocument.getStringtoDocument(xmlString);
        javax.xml.xpath.XPath xPath = XPathFactory.newInstance().newXPath();
        try {
            Object titleString = xPath.evaluate("/catalog/cd[@gender='female']/title/text()", document, XPathConstants.STRING);
            String a = titleString.toString();
            System.out.println(a);
        }catch (XPathExpressionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJxpathPerson() {
        Person person = new Person();
        person.setName("conan");
        person.setAge(29);
        person.setAddress("China Shanghai");
        JXPathContext jxPathContext = JXPathContext.newContext(person);
        jxPathContext.setLenient(true);
        System.out.println((String) jxPathContext.getValue("name"));
    }

    @Test
    public void testJxpathMultiPerson() {
        Human human = new Human();
        Person person = new Person();
        person.setName("conan");
        person.setAge(29);
        person.setAddress("China Shanghai");
        human.setPerson(person);
        JXPathContext jxPathContext = JXPathContext.newContext(human);
        jxPathContext.setLenient(true);
        System.out.println((String) jxPathContext.getValue("person/name"));
    }


}

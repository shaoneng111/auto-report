package utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

public class XmlDocumentUtil {
    protected static final Logger logger = LoggerFactory.getLogger(XmlDocumentUtil.class);


    /**
     *  load  载入一个XML文档
     */

    public static Document load (InputStream inputStream) {
        Document document = null;
        try {
            SAXReader saxReader = new SAXReader();
            document = saxReader.read(inputStream);
        } catch (DocumentException e) {
            e.printStackTrace();
            logger.error("", e);
        }
        return document;
    }

}

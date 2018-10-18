package parser;

import org.dom4j.Element;
import org.dom4j.Node;
import tag.*;
import utils.XmlDocumentUtil;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

public class RdlParserDefault implements IRdlParser{


    @Override
    public Document parse(String xmlStr) {
        try {
            org.dom4j.Document xmldoc = null;
            xmldoc = XmlDocumentUtil.load(new ByteArrayInputStream(xmlStr.getBytes("UTF-8")));
            Document doc = new Document();


            Element root = xmldoc.getRootElement();
            String rootName = root.getName();
            if (rootName.toLowerCase().equals("component")) {
                //没有Document节点
                doc.addComponent(this._parseComponent(root));
            } else {
                return null;
            }
            return doc;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private Component _parseComponent(Node node) {
        if (node == null) {
            return null;
        }

        String nodeName = node.getName();
        if (nodeName.toLowerCase().equals("content")) {
            Content content = new Content();
 //           String str = node.valueOf("@template");
//            System.out.println(str);
            content.setTemplate(node.valueOf("@template"));
            Node cdataNode = this.getFirstCDATA(node);
            if (cdataNode != null) {
                content.setContent(cdataNode.getStringValue());
            }
            return content;
        }

        Component res = new Component();
        res.setTitle(node.valueOf("@title"));
        res.setName(node.valueOf("@name"));

        if (node.valueOf("@showTitle") != null || !node.valueOf("@showTitle").equals("")) {
            res.setShowTitle(Boolean.valueOf(node.valueOf("@showTitle")));
        }
        if (node.valueOf("@showContent") != null || !node.valueOf("@showContent").equals("")) {
            res.setShowContent(node.valueOf("@showContent"));
        }
        if (node.valueOf("@titleLevel") != null || !node.valueOf("@titleLevel").equals("")) {
            res.setTitleLevel(node.numberValueOf("@titleLevel").intValue());
        }
        if (node.valueOf("@foreach") != null || !node.valueOf("@foreach").equals("")) {
            res.setForeach(node.valueOf("@foreach"));
        }
        if (node.valueOf("@foreach") != null || !node.valueOf("@foreach").equals("")) {
            res.setIndex(node.valueOf("@index"));
        }

        //Data
        Node dataNode = node.selectSingleNode("./Data");
        if (dataNode != null) {
            res.setData(this._parseDataNode(dataNode));
        }

        //style
        Node styleNode = node.selectSingleNode("./Style");
        if (styleNode != null) {
            res.setStyle(this._parseStyle(styleNode));
        }
        return  null;
    }

    private Style _parseStyle(Node node) {
        if (node == null) {
            return null;
        }

        Style style = new Style();
        Node cdataNode = this.getFirstCDATA(node);
        if (cdataNode != null) {
            style.setValue(cdataNode.getStringValue());
        }
        return style;
    }

    private Data _parseDataNode(Node node) {
        if (node == null) {
            return null;
        }

        Data data = new Data();

        List<Node> dataSourceNodes = node.selectNodes(".//DataSource");
        if (dataSourceNodes != null) {
            data.setDataSources(dataSourceNodes.stream().map(r -> this._parseDataSource(r)).collect(Collectors.toList()));
        }

        List<Node> dataSetNodes = node.selectNodes(".//DataSet");
        if (dataSetNodes != null) {
            data.setDataSets(dataSetNodes.stream().map(r -> this._parseDataSet(r)).collect(Collectors.toList()));
        }

        List<Node> propertiesNodes = node.selectNodes("./Properties//Property");
        if (propertiesNodes != null) {
            data.setProperties(propertiesNodes.stream().map(r -> this._parseProperty(r)).collect(Collectors.toList()));
        }

        return data;
    }

    private Property _parseProperty(Node node) {

        if (node == null) {
            return null;
        }
        Property property = new Property();
        property.setName(node.valueOf("@name"));
        property.setValue(node.valueOf("@value"));
        property.setRef(node.valueOf("@ref"));
        return property;
    }

    private DataSet _parseDataSet(Node node) {
        if (node == null) {
            return null;
        }

        DataSet dataSet = new DataSet();
        dataSet.setDataSource(node.valueOf("@dataSource"));
        dataSet.setName(node.valueOf("@name"));
        dataSet.setScriptType(node.valueOf("@query"));
        dataSet.setArgs(node.valueOf("@args"));
        dataSet.setReturnType(DataSetReturnType.valueOf(node.valueOf("@returnType")));

        List<Node> propertiesNodes = node.selectNodes(".//Property");
        if (propertiesNodes != null) {
            dataSet.setProperties(propertiesNodes.stream().map(r -> _parseProperty(r)).collect(Collectors.toList()));
        }

        return dataSet;
    }

    private DataSource _parseDataSource(Node node) {
        if (node == null) {
            return null;
        }

        DataSource dataSource = new DataSource();
        dataSource.setDriver(node.valueOf("@driver"));
        dataSource.setName(node.valueOf("@name"));
        dataSource.setPassword(node.valueOf("@password"));
        dataSource.setType(node.valueOf("@type"));
        dataSource.setUrl(node.valueOf("@url"));
        dataSource.setUsername(node.valueOf("@username"));

        List<Node> propertiesNodes = node.selectNodes("./Property");
        if (propertiesNodes != null) {
            dataSource.setProperties(propertiesNodes.stream().map(r -> _parseProperty(r)).collect(Collectors.toList()));
        }

        return dataSource;
    }

    private Node getFirstCDATA(Node node) {
        if (!(node instanceof Node)) {
            return null;
        }
        Element element = (Element) node;

        for (Object n : element.content()) {
            if (n instanceof  Node) {
                Node nn = (Node) n;
                if (Node.CDATA_SECTION_NODE == nn.getNodeType()) {
                    return nn;
                }
            }
        }
        return null;
    }

    @Override
    public Data parseData(String xmlStr) {
        return null;
    }

    @Override
    public Component parseComponent(String xmlStr) {
        try {
            org.dom4j.Document xmldoc = XmlDocumentUtil.load(new ByteArrayInputStream(xmlStr.getBytes("UTF-8")));
            String rootName = xmldoc.getRootElement().getName();

            if (rootName.toLowerCase().equals("component")) {
                return this._parseComponent(xmldoc.getRootElement());
            } else {
                return null;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}

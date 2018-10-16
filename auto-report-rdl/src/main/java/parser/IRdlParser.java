package parser;

import tag.Component;
import tag.Data;
import tag.Document;

public interface IRdlParser {
    public Document parse(String xmlStr);

    public Data parseData(String xmlStr);

    public Component parseComponent(String xmlStr);
}

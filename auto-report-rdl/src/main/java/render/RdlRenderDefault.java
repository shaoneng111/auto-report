package render;

import engine.IDataProviderFactory;
import tag.Document;

/**
 * @author shao
 * @date 18-10-18
 * @time 上午11:05
 */
public class RdlRenderDefault implements IRdlRender{


    @Override
    public String render(Document document, IDataProviderFactory provider) {

        if (document == null) {
            return "";
        }

        return null;
    }
}

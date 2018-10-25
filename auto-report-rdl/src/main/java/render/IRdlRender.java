package render;

import engine.IDataProviderFactory;
import tag.Document;

/**
 * @author shao
 * @date 18-10-18
 * @time 上午11:01
 */
public interface IRdlRender {

    public String render(Document document, IDataProviderFactory provider);
}

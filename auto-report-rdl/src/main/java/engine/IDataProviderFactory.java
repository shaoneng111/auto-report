package engine;

import tag.Document;

/**
 * @author shao
 * @date 18-10-18
 * @time 下午2:54
 */
public interface IDataProviderFactory {

    public IDataProvider getDataProvider(Document doc);
}

package render;

import engine.IDataProvider;

public interface ITplRender {

    public String render(String tpl, Object privider);

    public String render(String tpl, IDataProvider provider);
}

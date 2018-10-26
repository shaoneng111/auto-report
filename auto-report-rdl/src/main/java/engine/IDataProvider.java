package engine;

public interface IDataProvider {
    public void put (String path, Object obj);

    public Object get (String name);

    public void setParent(IDataProvider parent);
}

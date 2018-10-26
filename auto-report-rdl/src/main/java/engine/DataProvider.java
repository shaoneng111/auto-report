package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProvider implements IDataProvider {

    private Map<String, Object> map = new HashMap<>();

    private IDataProvider parent;

    public List<IDataProvider> children = new ArrayList<>();

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public IDataProvider getParent() {
        return parent;
    }

    @Override
    public void setParent(IDataProvider parent) {
        this.parent = parent;
    }

    public List<IDataProvider> getChildren() {
        return children;
    }

    public void setChildren(List<IDataProvider> children) {
        this.children = children;
    }

    @Override
    public void put(String path, Object obj) {
        if (map.containsKey(path)){
            this.map.replace(path, obj);
        } else {
            this.map.put(path, obj);
        }
    }

    @Override
    public Object get(String name) {
        return map.get(name);
    }
}

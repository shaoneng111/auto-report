package engine;

import java.util.HashMap;
import java.util.Map;

public class DataProvider implements IDataProvider {

    private Map<String, Object> map = new HashMap<>();

    @Override
    public void put(String path, Object obj) {
        if (map.containsKey(path)){
            this.map.replace(path, obj);
        } else {
            this.map.put(path, obj);
        }
    }
}

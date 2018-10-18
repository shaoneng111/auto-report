package tag;

import java.util.ArrayList;
import java.util.List;

public class DataSet {

    private String dataSource;

    private String name;

    private String query;

    private String fillQuery;

    private String scriptType = "SQL";

    private String args;

    private DataSetReturnType returnType = DataSetReturnType.List;

    private List<Property> properties = new ArrayList<>();

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getFillQuery() {
        return fillQuery;
    }

    public void setFillQuery(String fillQuery) {
        this.fillQuery = fillQuery;
    }

    public String getScriptType() {
        return scriptType;
    }

    public void setScriptType(String scriptType) {
        this.scriptType = scriptType;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public DataSetReturnType getReturnType() {
        return returnType;
    }

    public void setReturnType(DataSetReturnType returnType) {
        this.returnType = returnType;
    }

    public void addProperty (Property property) {
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }else {
            this.properties.add(property);
        }
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}

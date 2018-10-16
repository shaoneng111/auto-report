package tag;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private List<DataSource> dataSources = new ArrayList<>();

    private List<DataSet> dataSets = new ArrayList<>();

    private List<Property> properties = new ArrayList<>();

    public void addDataSource (DataSource dataSource) {
        if (this.dataSources == null) {
            this.dataSources = new ArrayList<>();
        }
        this.dataSources.add(dataSource);
    }

    public void addDataSets (DataSet dataSet) {
        if (this.dataSets == null) {
            this.dataSets = new ArrayList<>();
        }
        this.dataSets.add(dataSet);
    }

    public void addProperty (Property property) {
        if (this.properties == null) {
            this.properties = new ArrayList<>();
        }
        this.properties.add(property);
    }


    public List<DataSource> getDataSources() {
        return dataSources;
    }

    public void setDataSources(List<DataSource> dataSources) {
        this.dataSources = dataSources;
    }

    public List<DataSet> getDataSets() {
        return dataSets;
    }

    public void setDataSets(List<DataSet> dataSets) {
        this.dataSets = dataSets;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }



}

package engine;

import tag.*;

import java.util.List;

/**
 * @author shao
 * @date 18-10-18
 * @time 下午2:57
 */
public abstract class DataProviderFactoryBase implements IDataProviderFactory {
    @Override
    public IDataProvider getDataProvider(Document doc) {

        IDataProvider res = new DataProvider();

        //填充properties
        _fillProperties(doc, res);

        //TODO 插入freemarker方法
        return res;
    }

    private void _fillProperties(Document doc, IDataProvider dataProvider) {
        if (doc == null) {
            return;
        }

        if (doc.getData() != null) {
            List<Property> properties = doc.getData().getProperties();
            if (properties != null && properties.size() > 0) {
                _listAdd(properties, dataProvider);
            }

            List<DataSource> dataSources = doc.getData().getDataSources();
            if (dataSources != null && dataSources.size() > 0) {
                dataSources.stream().forEach(dataSource -> {
                    dataProvider.put(dataSource.getName(), dataSource);
                });
            }

            List<DataSet> dataSets = doc.getData().getDataSets();
            if (dataSets != null && dataSets.size() > 0) {
                dataSets.forEach(dataSet -> {
                    dataProvider.put(dataSet.getName(), this._datasetResult(dataSet, dataProvider));
                });
            }

        }

    }

    private void _listAdd(List<Property> properties, IDataProvider dataProvider) {
        if (properties != null && properties.size() > 0) {
            properties.stream().forEach(property -> {
               if (property.getRef() == null || property.getRef().equals("")) {
                   dataProvider.put(property.getName(), property.getValue());
               }else {
                   //暂未实现
               }
            });
        }

//        List<Integer> list = Arrays.asList(1,2,3,4);
//        list.stream().map(r -> r * r).collect(Collectors.toList());
    }



    @Override
    public IDataProvider getDataProvider(Component cmp, IDataProvider parent) {
        
        return _fillComponentPropeties(cmp, parent);
    }

    private IDataProvider _fillComponentPropeties(Component cmp, IDataProvider parentProvider) {

        IDataProvider selfProvider = new DataProvider();
        selfProvider.setParent(parentProvider);

        if (cmp.getData() != null) {
            List<Property> properties = cmp.getData().getProperties();

            if (properties != null && properties.size() > 0) {
                _listAdd(properties, selfProvider);
            }

            List<DataSource> dsList = cmp.getData().getDataSources();

            if (dsList != null && dsList.size() > 0) {
                dsList.stream().forEach(dataSource -> {
                    selfProvider.put(dataSource.getName(), selfProvider);
                });
            }

            List<DataSet> dataSets = cmp.getData().getDataSets();

            if (dataSets != null && dataSets.size() > 0) {
                dataSets.stream().forEach(dataSet -> {
                    selfProvider.put(dataSet.getName(), this._datasetResult(dataSet, selfProvider));
                });
            }
        }

        return  selfProvider;
    }

    public abstract Object _datasetResult(DataSet dataSet, IDataProvider provider);

}

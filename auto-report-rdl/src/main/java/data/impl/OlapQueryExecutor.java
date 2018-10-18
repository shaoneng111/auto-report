package data.impl;

import data.IQueryExecutor;
import tag.DataSet;
import tag.DataSource;

/**
 * @author shao
 * @date 18-10-18
 * @time 下午5:57
 */
public class OlapQueryExecutor implements IQueryExecutor {
    @Override
    public Object getConnection(DataSource dataSource) {
        return null;
    }

    @Override
    public Object getQueryResult(Object connect, DataSet dataSet) {
        return null;
    }
}

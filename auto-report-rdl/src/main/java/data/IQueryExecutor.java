package data;

import tag.DataSet;
import tag.DataSource;

/**
 * @author shao
 * @date 18-10-18
 * @time 下午5:47
 */
public interface IQueryExecutor {

    public Object getConnection(DataSource dataSource);

    public Object getQueryResult(Object connect, DataSet dataSet);
}

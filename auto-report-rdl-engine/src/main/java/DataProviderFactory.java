import data.IQueryExecutor;
import data.impl.JsonQueryExecutor;
import data.impl.OlapQueryExecutor;
import data.impl.SqlQueryExecutor;
import engine.DataProviderFactoryBase;
import engine.IDataProvider;
import tag.DataSet;
import tag.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author shao
 * @date 18-10-18
 * @time 下午4:22
 */
public class DataProviderFactory extends DataProviderFactoryBase {


    @Override
    public Object datasetResult(DataSet dataSet, IDataProvider provider) {

        String dsname = dataSet.getDataSource();
        DataSource dataSource = (DataSource) provider.get(dsname);

        IQueryExecutor executor = null;
        String scriptType = dataSet.getScriptType();

        if (scriptType.equals("SQL")) {
  //          _execute(dataSource, dataSet);
            executor = new SqlQueryExecutor();
        }else if (scriptType.equals("MDX")) {
            executor = new OlapQueryExecutor();
        }else if (scriptType.equals("JSON")) {
            executor = new JsonQueryExecutor();
        }

        if (executor == null) {
            return null;
        }

        //Query, 参数实例化
        //DataSet有属性，或没属性

        return null;
    }

    private void _execute(DataSource dataSource, DataSet dataSet) {
        String url = dataSource.getUrl();
        String driver = dataSource.getDriver();
        String username = dataSource.getUsername();
        String password = dataSource.getPassword();
        String sql = dataSet.getQuery();

        try {
            _jdbcConnection(driver, url, username, password, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection _jdbcConnection(String driver, String url, String username, String password, String sql) {
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;

    }

}

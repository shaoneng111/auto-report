package data.impl;

import data.IQueryExecutor;
import tag.DataSet;
import tag.DataSource;

import java.sql.*;

/**
 * @author shao
 * @date 18-10-18
 * @time 下午5:58
 */
public class SqlQueryExecutor implements IQueryExecutor {

    @Override
    public Object getConnection(DataSource dataSource) {
        Connection connection = null;
        if (dataSource.getDriver() != null && !"".equals(dataSource.getDriver())) {
            try {
                Class.forName(dataSource.getDriver());
                connection = DriverManager.getConnection(dataSource.getUrl());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public Object getQueryResult(Object connect, DataSet dataSet) {

        if (connect == null) {
            return null;
        }

        if (connect instanceof Connection) {
            Connection conn = (Connection) connect;
            try {
                Statement statement = conn.createStatement();
                ResultSet resultSet = null;

                resultSet = statement.executeQuery(dataSet.getFillQuery());


            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }


        }


        return null;
    }
}

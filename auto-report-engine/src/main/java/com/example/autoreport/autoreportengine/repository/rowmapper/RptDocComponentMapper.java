package com.example.autoreport.autoreportengine.repository.rowmapper;

import com.example.autoreport.autoreportengine.entity.RptDocComponent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author shao
 * @date 18-10-18
 * @time 上午9:06
 */
public class RptDocComponentMapper implements RowMapper<RptDocComponent> {
    @Override
    public RptDocComponent mapRow(ResultSet resultSet, int i) throws SQLException {

        RptDocComponent cmp = new RptDocComponent();

        cmp.setId(resultSet.getLong("id"));
        cmp.setTitle(resultSet.getString("title"));
        cmp.setTitleLevel(resultSet.getInt("title_level"));

        cmp.setShowTitle(resultSet.getShort("show_title"));
        cmp.setType(resultSet.getString("type"));
        cmp.setParameter(resultSet.getString("parameter"));
        cmp.setLabel(resultSet.getString("label"));
        cmp.setStatus(resultSet.getShort("status"));
        cmp.setCoverPage(resultSet.getShort("cover_page"));
        cmp.setOrdernum(resultSet.getInt("ordernum"));


        return cmp;
    }
}

package com.example.autoreport.autoreportengine.services.impl;

import com.example.autoreport.autoreportengine.services.IRptDocTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

public class RptDocTemplateService implements IRptDocTemplateService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String preview(long id, boolean coverPage, Map<String, Object> properties) {

        coverPage = false;
        id = 1;

        String sql = "SELECT *\n" +
                "FROM auto_report_doc_component a\n" +
                "WHERE a.status = 1 AND a.tpl_id = 1 AND a.cover_page = 0\n" +
                "ORDER BY a.id ASC";

 //       List<RptDocComponent> cmpList = jdbcTemplate.query(sql, new Object[]{id, coverPage ? 1 : 0}, );

        return null;
    }
}

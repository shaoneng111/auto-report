package com.example.autoreport.autoreportengine.services.impl;

import Service.Engine;
import com.example.autoreport.autoreportengine.entity.RptDocComponent;
import com.example.autoreport.autoreportengine.repository.rowmapper.RptDocComponentMapper;
import com.example.autoreport.autoreportengine.services.IRptDocTemplateService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import parser.RdlParserFactory;
import tag.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RptDocTemplateService implements IRptDocTemplateService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String preview(long id, boolean coverPage, Map<String, Object> properties) {

 //       coverPage = false;
  //      id = 1;

        String sql = "SELECT * " +
                "FROM auto_report_doc_component a " +
                "WHERE a.status = 1 AND a.tpl_id = ? AND a.cover_page = ? " +
                "ORDER BY a.id ASC";

        List<RptDocComponent> cmpList = jdbcTemplate.query(sql, new Object[]{id, coverPage ? 1 : 0}, new RptDocComponentMapper());

        List<Component> clist = new ArrayList<>();
        for (int i = 0; i < cmpList.size(); i++) {
            RptDocComponent component = cmpList.get(i);

            clist.add(this._DocCmp2Component(component));
        }

        Document doc = new Document();
        doc.setComponents(clist);
        Data data = new Data();
        doc.setData(data);

        //通用样式
        Style style = new Style();
        try {
            style.setValue(IOUtils.toString(this.getClass().getResourceAsStream(coverPage?"/style/cover.css" : "/style/doc.css"), "UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc.setStyle(style);

        if (properties != null) {
            data.setProperties(properties.entrySet().stream().map(entry -> {
                Property property = new Property();
                property.setName(entry.getKey());
                property.setValue(entry.getValue());
                return property;
            }).collect(Collectors.toList()));
        }

        Engine engine = new Engine();
        return engine.process(doc);
    }

    private Component _DocCmp2Component(RptDocComponent docComponent) {

        int type = Integer.valueOf(docComponent.getType());

        Component xmlCmp = new Component();

        switch (type) {

            case 0:

            case 1:

            case 2:

            case 3:

            case 4:

            case 5:
                //xml 组件定义
                String xmlStr = docComponent.getParameter();
                return RdlParserFactory.getRdlParser().parseComponent(xmlStr);
            default:
                break;
        }

        return null;
    }

}

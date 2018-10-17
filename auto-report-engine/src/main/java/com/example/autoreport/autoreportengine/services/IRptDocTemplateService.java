package com.example.autoreport.autoreportengine.services;

import java.util.Map;

public interface IRptDocTemplateService {

    String preview(long id, boolean coverPage, Map<String, Object> properties);
}

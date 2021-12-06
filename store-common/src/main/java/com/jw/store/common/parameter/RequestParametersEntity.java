package com.jw.store.common.parameter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class RequestParametersEntity {
protected  final Logger logger= LoggerFactory.getLogger(RequestParametersEntity.class);
    private String entity;
    private String type;
    private Integer pageNum;
    private String map;

    public RequestParametersEntity(String entity, String type, Integer pageNum, String map) {
        this.entity = entity;
        this.type = type;
        this.pageNum = pageNum;
        this.map = map;
    }

}

package com.jw.store.common.baseController;

import com.jw.store.common.annotation.Filter;
import com.jw.store.common.baseEntity.ResponseEntity;
import com.jw.store.common.baseService.BaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BaseController {
    @Resource
    BaseService service;

}

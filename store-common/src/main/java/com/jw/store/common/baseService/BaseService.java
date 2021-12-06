package com.jw.store.common.baseService;

import com.jw.store.common.baseRepository.BaseRepository;

import javax.annotation.Resource;

public class BaseService {
    @Resource
    BaseRepository repository;

    
}

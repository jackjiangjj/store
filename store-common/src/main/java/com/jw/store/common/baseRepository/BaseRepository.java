package com.jw.store.common.baseRepository;

import com.jw.store.common.baseEntity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<T extends BaseEntity,Integer> extends JpaRepository<T,Integer> {

}

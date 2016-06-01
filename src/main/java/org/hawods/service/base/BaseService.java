package org.hawods.service.base;

import org.hawods.entity.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hawods on 6/1/16.
 */
public interface BaseService<T extends BaseEntity<ID>, ID extends Serializable> {

    T find(ID id);

    List<T> findAll();

    List<T> findList(ID... ids);

//    Page<T> findPage(Pageable pageable);

    long count();

    boolean exists(ID id);

    T save(T entity);

    T update(T entity);

    void delete(T entity);

    void delete(ID... ids);
}


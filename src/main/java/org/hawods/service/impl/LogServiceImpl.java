package org.hawods.service.impl;

import org.hawods.entity.Log;
import org.hawods.service.LogService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hawods on 6/4/16.
 */
@Service
public class LogServiceImpl implements LogService {

    @Override
    public Log find(Long aLong) {
        return null;
    }

    @Override
    public List<Log> findAll() {
        return null;
    }

    @Override
    public List<Log> findList(Long... longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public Log save(Log entity) {
        return null;
    }

    @Override
    public Log update(Log entity) {
        return null;
    }

    @Override
    public void delete(Log entity) {

    }

    @Override
    public void delete(Long... longs) {

    }
}

package com.main.ra.service;

import java.util.List;

public interface BaseService<O,T,R> {
    O add(R objectRequest);
    O update(T id, R newObjectRequest);
    boolean delete(T id);
    O findById(T id);
    List<O> findAll();
}

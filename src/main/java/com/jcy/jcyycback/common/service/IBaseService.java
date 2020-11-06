package com.jcy.jcyycback.common.service;

import java.util.List;

public interface IBaseService<T> {
    List<T> queryAll() throws Exception;

    PageHelper<T> queryList(PageHelper<T> paramPageHelper) throws Exception;

    List<T> queryList(T paramT) throws Exception;

    T get(T paramT) throws Exception;

    Boolean save(T paramT) throws Exception;

    Boolean delete(T paramT) throws Exception;
}


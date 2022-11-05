package com.vincenzoracca.localstack.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {

    List<T> findAll();
    Optional<T> findById(K id);
    T save(T entity);
    void delete(T entity);
    void deleteAll();
}

package com.thalesrocha.restaurantsearch.repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by ThalesRocha
 */
public interface InMemoryRepository<T, ID> {
    void save(T t);
    void saveAll(List<T> ts);
    void deleteAll();
    List<T> findAll();
    List<T> findByPredicate(Predicate<T> specification);
    Optional<T> findById(ID id);
}

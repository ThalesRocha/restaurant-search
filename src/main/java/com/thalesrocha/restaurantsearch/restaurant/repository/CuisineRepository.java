package com.thalesrocha.restaurantsearch.restaurant.repository;

import com.thalesrocha.restaurantsearch.repository.InMemoryRepository;
import com.thalesrocha.restaurantsearch.restaurant.entity.Cuisine;
import com.thalesrocha.restaurantsearch.restaurant.storage.CuisineStorage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by ThalesRocha
 */
@Repository
public class CuisineRepository implements InMemoryRepository<Cuisine, Integer> {

    private final List<Cuisine> storage;

    public CuisineRepository() {
        storage = CuisineStorage.getInstance().getCuisines();
    }

    @Override
    public void save(Cuisine cuisine) {
        storage.add(cuisine);
    }

    @Override
    public void saveAll(List<Cuisine> cuisines) {
        storage.addAll(cuisines);
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }

    @Override
    public List<Cuisine> findAll() {
        return storage;
    }

    @Override
    public List<Cuisine> findByPredicate(Predicate<Cuisine> specification) {
        return storage.stream().filter(specification).collect(Collectors.toList());
    }

    @Override
    public Optional<Cuisine> findById(Integer id) {
        return storage.stream().filter(restaurant -> restaurant.getId().equals(id)).findFirst();
    }
}

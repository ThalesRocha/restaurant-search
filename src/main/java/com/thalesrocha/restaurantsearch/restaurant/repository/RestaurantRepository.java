package com.thalesrocha.restaurantsearch.restaurant.repository;

import com.thalesrocha.restaurantsearch.repository.InMemoryRepository;
import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.restaurant.storage.RestaurantStorage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by ThalesRocha
 */
@Repository
public class RestaurantRepository implements InMemoryRepository<Restaurant, UUID> {

    private final List<Restaurant> storage;

    public RestaurantRepository() {
        storage = RestaurantStorage.getInstance().getRestaurants();
    }

    @Override
    public void save(Restaurant restaurant) {
        storage.add(restaurant);
    }

    @Override
    public void saveAll(List<Restaurant> restaurants) {
        storage.addAll(restaurants);
    }

    @Override
    public void deleteAll() {
        storage.clear();
    }

    @Override
    public List<Restaurant> findAll() {
        return storage;
    }

    @Override
    public List<Restaurant> findByPredicate(Predicate<Restaurant> specification) {
        return storage.stream().filter(specification).collect(Collectors.toList());
    }

    @Override
    public Optional<Restaurant> findById(UUID uuid) {
        return storage.stream().filter(restaurant -> restaurant.getId().equals(uuid)).findFirst();
    }
}

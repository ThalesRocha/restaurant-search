package com.thalesrocha.restaurantsearch.restaurant.service;

import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.restaurant.repository.RestaurantRepository;
import com.thalesrocha.restaurantsearch.service.FinderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by ThalesRocha
 */
@Service
public class RestaurantFinderService implements FinderService<Restaurant> {

    private final RestaurantRepository restaurantRepository;

    public RestaurantFinderService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public List<Restaurant> find(Predicate<Restaurant> predicate) {
        return restaurantRepository.findByPredicate(predicate);
    }
}

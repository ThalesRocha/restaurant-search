package com.thalesrocha.restaurantsearch.restaurant.storage;

import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThalesRocha
 */
public class RestaurantStorage {

    private static final RestaurantStorage instance = new RestaurantStorage();
    private final List<Restaurant> restaurants;


    private RestaurantStorage() {
        restaurants = new ArrayList<>();
    }

    public RestaurantStorage(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public static RestaurantStorage getInstance() {
        return instance;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }


}

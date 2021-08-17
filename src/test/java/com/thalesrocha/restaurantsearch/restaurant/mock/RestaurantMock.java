package com.thalesrocha.restaurantsearch.restaurant.mock;

import com.thalesrocha.restaurantsearch.restaurant.entity.Cuisine;
import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThalesRocha
 */
public class RestaurantMock {

    public static List<Restaurant> mockRestaurants(String baseName, String cuisineName, int length) {

        final List<Restaurant> restaurants = new ArrayList<>();

        for (int i = 1; i <= length; i++) {
            restaurants.add(Restaurant.builder()
                    .name(baseName + i)
                    .distance(i)
                    .rating(i)
                    .price(i)
                    .cuisine(Cuisine.builder().name(cuisineName).id(1).build())
                    .build());
        }

        return restaurants;
    }

    public static Restaurant mock(String name, Integer distance, Integer rating, Integer price, Cuisine cuisine) {

        return Restaurant.builder()
                .name(name)
                .distance(distance)
                .rating(rating)
                .price(price)
                .cuisine(cuisine)
                .build();

    }


}

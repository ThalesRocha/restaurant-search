package com.thalesrocha.restaurantsearch.restaurant.specification;

import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.specification.AbstractSpecification;

/**
 * Created by ThalesRocha
 */
public class ContainsRestaurantName extends AbstractSpecification<Restaurant> {

    private final String name;

    public ContainsRestaurantName(String name){
        this.name = name;
    }

    @Override
    public boolean test(Restaurant restaurant) {
        return restaurant.getName().toLowerCase().contains(name.toLowerCase());
    }
}

package com.thalesrocha.restaurantsearch.restaurant.specification;

import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.specification.AbstractSpecification;

import java.util.Locale;

/**
 * Created by ThalesRocha
 */
public class ContainsRestaurantCuisineName extends AbstractSpecification<Restaurant> {

    private final String cuisineName;

    public ContainsRestaurantCuisineName(String cuisineName) {
        this.cuisineName = cuisineName;
    }

    @Override
    public boolean test(Restaurant restaurant) {
        return restaurant.getCuisine().getName().toLowerCase().contains(cuisineName.toLowerCase());
    }

}

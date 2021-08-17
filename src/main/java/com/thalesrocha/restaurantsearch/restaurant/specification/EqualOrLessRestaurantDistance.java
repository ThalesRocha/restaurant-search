package com.thalesrocha.restaurantsearch.restaurant.specification;

import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.specification.AbstractSpecification;

/**
 * Created by ThalesRocha
 */
public class EqualOrLessRestaurantDistance extends AbstractSpecification<Restaurant> {

    private final Integer distance;

    public EqualOrLessRestaurantDistance(Integer distance){
        super();
        this.distance = distance;
    }

    @Override
    public boolean test(Restaurant restaurant) {
        return restaurant.getDistance() <= distance;
    }
}

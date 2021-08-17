package com.thalesrocha.restaurantsearch.restaurant.specification;

import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.specification.AbstractSpecification;

/**
 * Created by ThalesRocha
 */
public class EqualOrGreaterRestaurantRating extends AbstractSpecification<Restaurant> {

    private final Integer rating;

    public EqualOrGreaterRestaurantRating(Integer rating){
        super();
        this.rating = rating;
    }

    @Override
    public boolean test(Restaurant restaurant) {
        return restaurant.getRating() >= rating;
    }
}

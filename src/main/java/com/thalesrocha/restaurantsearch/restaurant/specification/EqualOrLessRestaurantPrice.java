package com.thalesrocha.restaurantsearch.restaurant.specification;

import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.specification.AbstractSpecification;

/**
 * Created by ThalesRocha
 */
public class EqualOrLessRestaurantPrice extends AbstractSpecification<Restaurant> {
    private final Integer price;

    public EqualOrLessRestaurantPrice(Integer price) {
        super();
        this.price = price;
    }

    @Override
    public boolean test(Restaurant restaurant) {
        return restaurant.getPrice() <= price;
    }
}

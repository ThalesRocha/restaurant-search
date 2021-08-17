package com.thalesrocha.restaurantsearch.restaurant.specification;

import com.thalesrocha.restaurantsearch.restaurant.entity.Cuisine;
import com.thalesrocha.restaurantsearch.specification.AbstractSpecification;

/**
 * Created by ThalesRocha
 */
public class EqualCuisineId extends AbstractSpecification<Cuisine> {

    private final Integer id;

    public EqualCuisineId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean test(Cuisine cuisine) {
        return cuisine.getId().equals(id);
    }
}

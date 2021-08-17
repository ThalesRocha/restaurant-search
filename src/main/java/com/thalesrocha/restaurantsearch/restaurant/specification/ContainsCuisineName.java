package com.thalesrocha.restaurantsearch.restaurant.specification;

import com.thalesrocha.restaurantsearch.restaurant.entity.Cuisine;
import com.thalesrocha.restaurantsearch.specification.AbstractSpecification;

/**
 * Created by ThalesRocha
 */
public class ContainsCuisineName extends AbstractSpecification<Cuisine> {

    private final String name;

    public ContainsCuisineName(String name){
        this.name = name;
    }

    @Override
    public boolean test(Cuisine cuisine) {
        return cuisine.getName().toLowerCase().contains(name.toLowerCase());
    }
}

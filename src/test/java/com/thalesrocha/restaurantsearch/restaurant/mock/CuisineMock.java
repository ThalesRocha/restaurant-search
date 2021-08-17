package com.thalesrocha.restaurantsearch.restaurant.mock;

import com.thalesrocha.restaurantsearch.restaurant.entity.Cuisine;

/**
 * Created by ThalesRocha
 */
public class CuisineMock {

    public static Cuisine mock(String name, Integer id) {
        return Cuisine.builder().name(name).id(id).build();
    }

}

package com.thalesrocha.restaurantsearch.restaurant.storage;

import com.thalesrocha.restaurantsearch.restaurant.entity.Cuisine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ThalesRocha
 */
public class CuisineStorage {

    private static final CuisineStorage instance = new CuisineStorage();
    private final List<Cuisine> cuisines;


    private CuisineStorage() {
        cuisines = new ArrayList<>();
    }

    public CuisineStorage(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public static CuisineStorage getInstance() {
        return instance;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }


}

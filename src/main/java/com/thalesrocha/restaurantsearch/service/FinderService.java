package com.thalesrocha.restaurantsearch.service;

import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by ThalesRocha
 */
public interface FinderService<T> {

    List<Restaurant> find(Predicate<Restaurant> predicate);

}

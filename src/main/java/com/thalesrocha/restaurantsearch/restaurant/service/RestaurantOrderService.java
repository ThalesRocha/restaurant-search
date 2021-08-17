package com.thalesrocha.restaurantsearch.restaurant.service;

import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.service.OrderService;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * Created by ThalesRocha
 */
@Service
public class RestaurantOrderService implements OrderService<Restaurant> {

    @Override
    public List<Restaurant> order(List<Restaurant> restaurants) {

        //TODO - Implement dynamically orderBy feature
        final Comparator<Restaurant> comparator = new Comparator<>() {
            public int compare(Restaurant restOne, Restaurant restTwo) {
                return new CompareToBuilder()
                        .append(restOne.getDistance(), restTwo.getDistance())
                        .append(restTwo.getRating(), restOne.getRating())
                        .append(restOne.getPrice(), restTwo.getPrice())
                        .toComparison();
            }
        };

        restaurants.sort(comparator);
        return restaurants;
    }

}

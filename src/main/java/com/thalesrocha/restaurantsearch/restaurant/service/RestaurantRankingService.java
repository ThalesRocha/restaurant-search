package com.thalesrocha.restaurantsearch.restaurant.service;

import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.service.RankingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ThalesRocha
 */
@Service
public class RestaurantRankingService implements RankingService<Restaurant> {

    @Override
    public List<Restaurant> rank(int topNth, List<Restaurant> restaurants) {
        return restaurants.stream().limit(topNth).collect(Collectors.toList());
    }

}

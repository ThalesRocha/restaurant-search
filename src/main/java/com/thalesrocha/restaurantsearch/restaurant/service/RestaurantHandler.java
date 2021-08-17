package com.thalesrocha.restaurantsearch.restaurant.service;

import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.restaurant.specification.*;
import com.thalesrocha.restaurantsearch.restaurant.to.RestaurantFilterRequest;
import com.thalesrocha.restaurantsearch.restaurant.to.RestaurantResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by ThalesRocha
 */
@Service
@Validated
public class RestaurantHandler {

    private final RestaurantFinderService restaurantFinderService;
    private final RestaurantOrderService restaurantOrderService;
    private final RestaurantRankingService restaurantRankingService;

    @Value(value = "${restaurant.top-nth}")
    private int TOP_NTH;

    public RestaurantHandler(RestaurantFinderService restaurantFinderService,
                             RestaurantOrderService restaurantOrderService,
                             RestaurantRankingService restaurantRankingService) {
        this.restaurantFinderService = restaurantFinderService;
        this.restaurantOrderService = restaurantOrderService;
        this.restaurantRankingService = restaurantRankingService;
    }

    public List<RestaurantResponse> handle(@NotNull(message = "Filter request must not be null") @Valid final RestaurantFilterRequest request) {

        final Predicate<Restaurant> predicate = buildFilterPredicate(request);

        final List<Restaurant> restaurants = restaurantRankingService.rank(TOP_NTH,
                restaurantOrderService.order(
                        restaurantFinderService.find(predicate)));

        return restaurants.stream().map(restaurant ->
                RestaurantResponse.builder()
                        .cuisine(restaurant.getCuisine().getName())
                        .distance(restaurant.getDistance())
                        .price(restaurant.getPrice())
                        .name(restaurant.getName())
                        .rating(restaurant.getRating())
                        .build()
        ).collect(Collectors.toList());

    }

    private Predicate<Restaurant> buildFilterPredicate(RestaurantFilterRequest request) {
        Predicate<Restaurant> predicate = (a) -> true;

        if (request.getName() != null) {
            predicate = new ContainsRestaurantName(request.getName());
        }

        if (request.getRating() != null) {
            predicate = new EqualOrGreaterRestaurantRating(request.getRating()).and(predicate);
        }

        if (request.getDistance() != null) {
            predicate = new EqualOrLessRestaurantDistance(request.getDistance()).and(predicate);
        }

        if (request.getPrice() != null) {
            predicate = new EqualOrLessRestaurantPrice(request.getPrice()).and(predicate);
        }

        if (request.getCuisine() != null) {
            predicate = new ContainsRestaurantCuisineName(request.getCuisine()).and(predicate);
        }

        return predicate;
    }
}

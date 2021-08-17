package com.thalesrocha.restaurantsearch.restaurant.controller;

import com.thalesrocha.restaurantsearch.restaurant.service.RestaurantHandler;
import com.thalesrocha.restaurantsearch.restaurant.to.RestaurantFilterRequest;
import com.thalesrocha.restaurantsearch.restaurant.to.RestaurantResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ThalesRocha
 */
@RestController
@RequestMapping(path = "/restaurant")
public class RestaurantController {

    private final RestaurantHandler restaurantHandler;

    public RestaurantController(RestaurantHandler restaurantHandler) {
        this.restaurantHandler = restaurantHandler;
    }

    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> addEmployee(RestaurantFilterRequest request) {
        return ResponseEntity.ok(restaurantHandler.handle(request));
    }


}

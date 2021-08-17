package com.thalesrocha.restaurantsearch.csv;

import com.opencsv.bean.CsvBindByName;
import com.thalesrocha.restaurantsearch.restaurant.input.RestaurantInput;

/**
 * Created by ThalesRocha
 */
public class RestaurantCsv implements RestaurantInput {

    @CsvBindByName
    String name;

    @CsvBindByName(column = "customer_rating")
    Integer rating;

    @CsvBindByName
    Integer distance;

    @CsvBindByName(column = "price")
    Integer price;

    @CsvBindByName(column = "cuisine_id")
    Integer cuisineId;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getRating() {
        return this.rating;
    }

    @Override
    public Integer getDistance() {
        return this.distance;
    }

    @Override
    public Integer getPrice() {
        return this.price;
    }

    @Override
    public Integer getCuisineId() {
        return this.cuisineId;
    }
}

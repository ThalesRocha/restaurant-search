package com.thalesrocha.restaurantsearch.restaurant.entity;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

/**
 * Created by ThalesRocha
 */
@Data
@Builder
public class Restaurant {

    private UUID id;
    private String name;
    private Integer rating;
    private Integer distance;
    private Integer price;
    private Cuisine cuisine;

    public Restaurant(String name, Integer rating, Integer distance, Integer price, Cuisine cuisine, UUID id) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.distance = distance;
        this.price = price;
        this.cuisine = cuisine;
    }

    public static RestaurantBuilder builder() {
        return new RestaurantBuilder();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public static final class RestaurantBuilder {
        private UUID id;
        private String name;
        private Integer rating;
        private Integer distance;
        private Integer price;
        private Cuisine cuisine;

        private RestaurantBuilder() {
        }

        public RestaurantBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public RestaurantBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RestaurantBuilder rating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public RestaurantBuilder distance(Integer distance) {
            this.distance = distance;
            return this;
        }

        public RestaurantBuilder price(Integer price) {
            this.price = price;
            return this;
        }

        public RestaurantBuilder cuisine(Cuisine cuisine) {
            this.cuisine = cuisine;
            return this;
        }

        public Restaurant build() {
            return new Restaurant(name, rating, distance, price, cuisine, id);
        }
    }
}

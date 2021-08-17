package com.thalesrocha.restaurantsearch.restaurant.to;

import lombok.Builder;
import lombok.Data;

/**
 * Created by ThalesRocha
 */
@Data
@Builder
public class RestaurantResponse {

    private String name;

    private Integer rating;

    private Integer distance;

    private Integer price;

    private String cuisine;

}

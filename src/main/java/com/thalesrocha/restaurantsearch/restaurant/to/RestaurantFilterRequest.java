package com.thalesrocha.restaurantsearch.restaurant.to;

import com.thalesrocha.restaurantsearch.validator.AtLeastOneNotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by ThalesRocha
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AtLeastOneNotNull(fieldNames = {"name", "rating", "distance", "price", "cuisine"}, message = "Invalid parameter, you must provide a value for at least one of these filters [name, rating, distance, price, cuisine]")
public class RestaurantFilterRequest {

    private String name;

    @Min(1)
    @Max(5)
    private Integer rating;

    @Min(1)
    @Max(10)
    private Integer distance;

    @Min(10)
    @Max(50)
    private Integer price;

    private String cuisine;

}

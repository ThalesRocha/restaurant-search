package com.thalesrocha.restaurantsearch.restaurant.entity;

import com.thalesrocha.restaurantsearch.restaurant.input.CuisineInput;
import lombok.Builder;
import lombok.Data;

/**
 * Created by ThalesRocha
 */
@Data
@Builder
public class Cuisine {

    private Integer id;

    private String name;

    public Cuisine(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Cuisine(CuisineInput input) {
        this.id = input.getId();
        this.name = input.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

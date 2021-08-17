package com.thalesrocha.restaurantsearch.csv;

import com.opencsv.bean.CsvBindByName;
import com.thalesrocha.restaurantsearch.restaurant.input.CuisineInput;

/**
 * Created by ThalesRocha
 */
public class CuisineCsv implements CuisineInput {

    @CsvBindByName
    private Integer id;

    @CsvBindByName
    private String name;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

}

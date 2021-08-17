package com.thalesrocha.restaurantsearch;

import com.thalesrocha.restaurantsearch.csv.CsvReader;
import com.thalesrocha.restaurantsearch.csv.CuisineCsv;
import com.thalesrocha.restaurantsearch.csv.RestaurantCsv;
import com.thalesrocha.restaurantsearch.restaurant.entity.Cuisine;
import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.restaurant.input.CuisineInput;
import com.thalesrocha.restaurantsearch.restaurant.input.RestaurantInput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.nio.file.Paths;
import java.util.List;

@SpringBootApplication
public class RestaurantSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantSearchApplication.class, args);
    }
}

package com.thalesrocha.restaurantsearch.restaurant.storage;

import com.thalesrocha.restaurantsearch.csv.CsvReader;
import com.thalesrocha.restaurantsearch.csv.CuisineCsv;
import com.thalesrocha.restaurantsearch.csv.RestaurantCsv;
import com.thalesrocha.restaurantsearch.repository.InMemoryRepository;
import com.thalesrocha.restaurantsearch.restaurant.entity.Cuisine;
import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.restaurant.input.CuisineInput;
import com.thalesrocha.restaurantsearch.restaurant.input.RestaurantInput;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * Created by ThalesRocha
 */
@Component
public class DataLoader {

    private final InMemoryRepository<Restaurant, UUID> restaurantRepository;
    private final InMemoryRepository<Cuisine, Integer> cuisineRepository;

    public DataLoader(InMemoryRepository<Restaurant, UUID> restaurantRepository, InMemoryRepository<Cuisine, Integer> cuisineRepository) {
        this.restaurantRepository = restaurantRepository;
        this.cuisineRepository = cuisineRepository;
    }


    @PostConstruct
    public void init() {
        final List<CuisineInput> cuisineInputs;
        try {

            this.getClass().getResourceAsStream("cuisines.csv");
            cuisineInputs = CsvReader.from("cuisines.csv", CuisineCsv.class);

            cuisineInputs.forEach(input -> {
                        cuisineRepository.save(new Cuisine(input));
                    }
            );

            final List<RestaurantInput> restaurantInputs = CsvReader.from("restaurants.csv", RestaurantCsv.class);
            restaurantInputs.forEach(input -> {
                        final Restaurant restaurant = Restaurant.builder()
                                .id(UUID.randomUUID())
                                .distance(input.getDistance())
                                .name(input.getName())
                                .price(input.getPrice())
                                .rating(input.getRating())
                                .cuisine(cuisineRepository.findById(input.getCuisineId()).orElse(null))
                                .build();
                        restaurantRepository.save(restaurant);
                    }
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

package com.thalesrocha.restaurantsearch.restaurant.integration;

import com.thalesrocha.restaurantsearch.repository.InMemoryRepository;
import com.thalesrocha.restaurantsearch.restaurant.entity.Cuisine;
import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.restaurant.storage.DataLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by ThalesRocha
 */
@SpringBootTest
public class DataLoaderIT {

    @Autowired
    private InMemoryRepository<Restaurant, UUID> restaurantInMemoryRepository;
    @Autowired
    private InMemoryRepository<Cuisine, Integer> cuisineIntegerInMemoryRepository;
    @Autowired
    private DataLoader dataLoader;

    @BeforeEach
    public void setup() {
        restaurantInMemoryRepository.deleteAll();
        cuisineIntegerInMemoryRepository.deleteAll();
    }

    @Test
    void when_method_init_is_triggered_we_should_load_data() throws Exception {

        dataLoader.init();

        assertThat(restaurantInMemoryRepository.findAll().size()).isEqualTo(10);
        assertThat(cuisineIntegerInMemoryRepository.findAll().size()).isEqualTo(2);

        final Restaurant restaurant = restaurantInMemoryRepository.findAll().get(0);
        assertThat(restaurant.getName()).isEqualTo("Deliciousgenix");
        assertThat(restaurant.getRating()).isEqualTo(4);
        assertThat(restaurant.getDistance()).isEqualTo(1);
        assertThat(restaurant.getPrice()).isEqualTo(10);
        assertThat(restaurant.getCuisine().getName()).isEqualTo("American");
    }








}

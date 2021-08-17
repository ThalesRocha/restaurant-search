package com.thalesrocha.restaurantsearch;

import com.thalesrocha.restaurantsearch.restaurant.storage.DataLoader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RestaurantSearchApplicationTests {

    @Autowired
    private DataLoader dataLoader;

    @Test
    void context_loads() {
        assertThat(dataLoader).isNotNull();
    }

}

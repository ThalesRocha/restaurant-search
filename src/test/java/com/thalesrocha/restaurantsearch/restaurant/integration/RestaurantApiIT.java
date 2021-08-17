package com.thalesrocha.restaurantsearch.restaurant.integration;

import com.thalesrocha.restaurantsearch.repository.InMemoryRepository;
import com.thalesrocha.restaurantsearch.restaurant.entity.Cuisine;
import com.thalesrocha.restaurantsearch.restaurant.entity.Restaurant;
import com.thalesrocha.restaurantsearch.restaurant.mock.CuisineMock;
import com.thalesrocha.restaurantsearch.restaurant.mock.RestaurantMock;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by ThalesRocha
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantApiIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private InMemoryRepository<Restaurant, UUID> restaurantInMemoryRepository;
    @Autowired
    private InMemoryRepository<Cuisine, Integer> cuisineIntegerInMemoryRepository;

    @BeforeEach
    public void setup() {
        restaurantInMemoryRepository.deleteAll();
        cuisineIntegerInMemoryRepository.deleteAll();
    }

    @Test
    public void when_matches_more_than_five_return_top_five() throws Exception {

        final Cuisine cuisineMock = CuisineMock.mock("chinese", 1);

        restaurantInMemoryRepository.saveAll(Arrays.asList(
                RestaurantMock.mock("name1", 1, 1, 1, cuisineMock),
                RestaurantMock.mock("name2", 2, 1, 1, cuisineMock),
                RestaurantMock.mock("name3", 3, 1, 1, cuisineMock),
                RestaurantMock.mock("name4", 4, 1, 1, cuisineMock),
                RestaurantMock.mock("name5", 5, 1, 1, cuisineMock),
                RestaurantMock.mock("name6", 6, 1, 1, cuisineMock)));

        mockMvc.perform(get("/restaurant/")
                        .contentType("application/json")
                        .queryParam("name", "Name"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$.[0].distance").value(1))
                .andExpect(jsonPath("$.[4].distance").value(5));
    }

    @Test
    public void when_matches_more_than_one_and_minus_than_five_results_return_all_matches() throws Exception {

        final Cuisine cuisineMock = CuisineMock.mock("chinese", 1);

        restaurantInMemoryRepository.saveAll(Arrays.asList(
                RestaurantMock.mock("match1", 1, 1, 1, cuisineMock),
                RestaurantMock.mock("match2", 2, 1, 1, cuisineMock),
                RestaurantMock.mock("match3", 3, 1, 1, cuisineMock),
                RestaurantMock.mock("name4", 4, 1, 1, cuisineMock),
                RestaurantMock.mock("name5", 5, 1, 1, cuisineMock),
                RestaurantMock.mock("name6", 6, 1, 1, cuisineMock)));

        mockMvc.perform(get("/restaurant/")
                        .contentType("application/json")
                        .queryParam("name", "match"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].distance").value(1))
                .andExpect(jsonPath("$.[2].distance").value(3));

    }

    @Test
    public void if_parameter_values_are_invalid_return_an_error_message() throws Exception {
        mockMvc.perform(get("/restaurant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .queryParam("distance", String.valueOf(300)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("Validation error"))
                .andExpect(jsonPath("$.subErrors", hasSize(1)))
                .andExpect(jsonPath("$.subErrors.[0].field").value("distance"))
                .andExpect(jsonPath("$.subErrors.[0].rejectedValue").value(300))
                .andExpect(jsonPath("$.subErrors.[0].message").value("must be less than or equal to 10"));
    }

    @Test
    public void if_all_parameters_are_null_return_an_error_message_() throws Exception {
        mockMvc.perform(get("/restaurant/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("Validation error"))
                .andExpect(jsonPath("$.subErrors", hasSize(1)))
                .andExpect(jsonPath("$.subErrors.[0].field").isEmpty())
                .andExpect(jsonPath("$.subErrors.[0].rejectedValue.name").isEmpty())
                .andExpect(jsonPath("$.subErrors.[0].rejectedValue.rating").isEmpty())
                .andExpect(jsonPath("$.subErrors.[0].rejectedValue.distance").isEmpty())
                .andExpect(jsonPath("$.subErrors.[0].rejectedValue.price").isEmpty())
                .andExpect(jsonPath("$.subErrors.[0].rejectedValue.cuisine").isEmpty())
                .andExpect(jsonPath("$.subErrors.[0].message").value("Invalid parameter, you must provide a value for at least one of these filters [name, rating, distance, price, cuisine]"));
    }

    @Test
    public void filter_by_provided_restaurant_name_should_match_partial_or_exact() throws Exception {

        final Cuisine cuisineMock = CuisineMock.mock("chinese", 1);

        restaurantInMemoryRepository.saveAll(Arrays.asList(
                RestaurantMock.mock("Exact", 1, 1, 1, cuisineMock),
                RestaurantMock.mock("notExact", 4, 1, 1, cuisineMock),
                RestaurantMock.mock("notExact", 5, 1, 1, cuisineMock)));

        mockMvc.perform(get("/restaurant/")
                        .contentType("application/json")
                        .queryParam("name", "exact"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].name").value("Exact"))
                .andExpect(jsonPath("$.[2].name").value("notExact"))
                .andExpect(jsonPath("$.[0].distance").value(1))
                .andExpect(jsonPath("$.[2].distance").value(5));

    }

    @Test
    public void filter_by_rating_should_match_values_equal_or_more_than() throws Exception {
        final Cuisine cuisineMock = CuisineMock.mock("chinese", 1);

        restaurantInMemoryRepository.saveAll(Arrays.asList(
                RestaurantMock.mock("exact", 1, 1, 1, cuisineMock),
                RestaurantMock.mock("notExact", 4, 1, 1, cuisineMock),
                RestaurantMock.mock("notExact", 5, 1, 1, cuisineMock)));

        mockMvc.perform(get("/restaurant/")
                        .contentType("application/json")
                        .queryParam("name", "exact"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].name").value("exact"))
                .andExpect(jsonPath("$.[2].name").value("notExact"))
                .andExpect(jsonPath("$.[0].distance").value(1))
                .andExpect(jsonPath("$.[2].distance").value(5));
    }

    @Test
    public void filter_by_distance_should_match_values_equal_or_less_than() throws Exception {

        final Cuisine cuisineMock = CuisineMock.mock("chinese", 1);

        restaurantInMemoryRepository.saveAll(Arrays.asList(
                RestaurantMock.mock("distance1", 1, 1, 1, cuisineMock),
                RestaurantMock.mock("distance2", 2, 1, 1, cuisineMock)));

        mockMvc.perform(get("/restaurant/")
                        .contentType("application/json")
                        .queryParam("distance", String.valueOf(1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].name").value("distance1"))
                .andExpect(jsonPath("$.[0].distance").value(1));

    }

    @Test
    public void filter_by_price_should_match_values_equal_or_less_than() throws Exception {

        final Cuisine cuisineMock = CuisineMock.mock("chinese", 1);

        restaurantInMemoryRepository.saveAll(Arrays.asList(
                RestaurantMock.mock("price15", 1, 1, 15, cuisineMock),
                RestaurantMock.mock("price20", 1, 1, 20, cuisineMock)));

        mockMvc.perform(get("/restaurant/")
                        .contentType("application/json")
                        .queryParam("price", String.valueOf(17)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].name").value("price15"));

    }

    @Test
    public void filter_by_cuisine_name_should_match_partial_or_exact() throws Exception {
        final Cuisine chineseMock = CuisineMock.mock("chinese", 1);
        final Cuisine chineseNotExactMock = CuisineMock.mock("chineseNotExact", 1);
        final Cuisine otherMock = CuisineMock.mock("japanese", 1);

        restaurantInMemoryRepository.saveAll(Arrays.asList(
                RestaurantMock.mock("cuisine", 1, 1, 1, chineseMock),
                RestaurantMock.mock("cuisineNotExact", 4, 1, 1, chineseNotExactMock),
                RestaurantMock.mock("other", 5, 1, 1, otherMock)));

        mockMvc.perform(get("/restaurant/")
                        .contentType("application/json")
                        .queryParam("cuisine", "chinese"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].name").value("cuisine"))
                .andExpect(jsonPath("$.[1].name").value("cuisineNotExact"));
    }

    @Test
    public void many_filter_criteria_should_apply_each_one_his_individual_rules() throws Exception {

        final Cuisine chinese = CuisineMock.mock("chinese", 1);
        final Cuisine japanese = CuisineMock.mock("japanese", 1);

        restaurantInMemoryRepository.saveAll(Arrays.asList(
                RestaurantMock.mock("name1", 1, 3, 10, chinese),
                RestaurantMock.mock("name2", 5, 4, 20, japanese),
                RestaurantMock.mock("other3", 10, 5, 30, japanese)));

        mockMvc.perform(get("/restaurant/")
                        .contentType("application/json")
                        .queryParam("name", "name")
                        .queryParam("cuisine", "chinese")
                        .queryParam("distance", String.valueOf(2))
                        .queryParam("rating", String.valueOf(2))
                        .queryParam("price", String.valueOf(15))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].name").value("name1"))
                .andExpect(jsonPath("$.[0].cuisine").value("chinese"))
                .andExpect(jsonPath("$.[0].distance").value(1))
                .andExpect(jsonPath("$.[0].rating").value(3))
                .andExpect(jsonPath("$.[0].price").value(10));

    }

    @Test
    public void sort_by_distance_first_then_high_rating_then_lower_price() throws Exception {
        final Cuisine cuisineMock = CuisineMock.mock("chinese", 1);

        restaurantInMemoryRepository.saveAll(Arrays.asList(
                RestaurantMock.mock("name1", 1, 5, 15, cuisineMock),
                RestaurantMock.mock("name2", 1, 5, 10, cuisineMock),
                RestaurantMock.mock("name3", 2, 1, 1, cuisineMock)));

        mockMvc.perform(get("/restaurant/")
                        .contentType("application/json")
                        .queryParam("name", "name")
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].name").value("name2"))
                .andExpect(jsonPath("$.[1].name").value("name1"))
                .andExpect(jsonPath("$.[2].name").value("name3"));
    }

}

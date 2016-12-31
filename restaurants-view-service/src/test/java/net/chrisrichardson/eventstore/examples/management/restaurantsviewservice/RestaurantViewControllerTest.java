package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.Address;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.DeliveryTime;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.FindAvailableRestaurantsRequest;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.examples.management.restaurant.testutil.RestaurantMother;
import net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.redis.RestaurantQuerySideRedisService;
import net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.web.RestaurantViewController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class RestaurantViewControllerTest {

    @Mock
    RestaurantQuerySideRedisService restaurantQuerySideRedisService;

    @InjectMocks
    private RestaurantViewController controller;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void findRestaurantByIdTest() throws Exception {
        RestaurantInfo restaurantInfo = RestaurantMother.makeRestaurant();
        ObjectMapper om = new ObjectMapper();

        when(restaurantQuerySideRedisService.findById("1")).thenReturn(restaurantInfo);

        MvcResult result = mockMvc.perform(get("/restaurants/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        RestaurantInfo resultingRestaurantInfo = om.readValue(result.getResponse().getContentAsString(), RestaurantInfo.class);
        assertEquals(restaurantInfo, resultingRestaurantInfo);
    }

    @Test
    public void findAvailableRestaurantsTest() throws Exception {
        ObjectMapper om = new ObjectMapper();

        RestaurantInfo restaurantInfo = RestaurantMother.makeRestaurant();
        ArrayList<RestaurantInfo> responseList = new ArrayList<>();
        responseList.add(restaurantInfo);

        when(restaurantQuerySideRedisService.findAvailableRestaurants(any(Address.class), any(DeliveryTime.class))).thenReturn(responseList);

        FindAvailableRestaurantsRequest request = new FindAvailableRestaurantsRequest("111111", 1, 12, 30);

        MvcResult result = mockMvc.perform(get("/restaurants/availablerestaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<RestaurantInfo> restaurantInfoList = om.readValue(result.getResponse().getContentAsString(), new TypeReference<List<RestaurantInfo>>() {});
        assertFalse(restaurantInfoList.isEmpty());
        assertTrue(restaurantInfoList.size()==1);

        assertEquals(restaurantInfo, restaurantInfoList.get(0));
    }


}

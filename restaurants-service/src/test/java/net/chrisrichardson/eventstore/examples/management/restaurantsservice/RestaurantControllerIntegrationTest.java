package net.chrisrichardson.eventstore.examples.management.restaurantsservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.CreateRestaurantResponse;
import net.chrisrichardson.eventstore.examples.management.restaurant.testutil.RestaurantMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestaurantServiceIntegrationTestConfiguration.class)
@IntegrationTest
@WebAppConfiguration
public class RestaurantControllerIntegrationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void shouldCreateAndUpdateRestaurant() throws Exception {
        ObjectMapper om = new ObjectMapper();
        MvcResult result = mockMvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(RestaurantMother.makeRestaurant()))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        result.getAsyncResult();

        mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        CreateRestaurantResponse createRestaurantResponse = (CreateRestaurantResponse)result.getAsyncResult();

        String restaurantId = createRestaurantResponse.getRestaurantId();

        mockMvc.perform(put("/restaurants/"+restaurantId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(RestaurantMother.makeRestaurant()))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}

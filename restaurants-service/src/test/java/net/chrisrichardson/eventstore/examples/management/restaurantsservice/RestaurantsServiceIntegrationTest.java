package net.chrisrichardson.eventstore.examples.management.restaurantsservice;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.CreateRestaurantResponse;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.UpdateRestaurantResponse;
import net.chrisrichardson.eventstore.examples.management.restaurant.testutil.RestaurantMother;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RestaurantServiceIntegrationTestConfiguration.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0", "management.port=0"})
public class RestaurantsServiceIntegrationTest {

  @Value("${local.server.port}")
  private int port;

  private String baseUrl(String path) {
    return "http://localhost:" + port + "/" + path;
  }

  @Autowired
  RestTemplate restTemplate;


  @Test
  public void shouldCreateAndUpdateRestaurant() {
    RestaurantInfo restaurantInfo = RestaurantMother.makeRestaurant();

    ResponseEntity<CreateRestaurantResponse> createRestaurantResponse = restTemplate.postForEntity(baseUrl("/restaurants"), restaurantInfo, CreateRestaurantResponse.class);
    Assert.assertEquals(createRestaurantResponse.getStatusCode(), HttpStatus.OK);
    final String restaurantId = createRestaurantResponse.getBody().getRestaurantId();

    ResponseEntity<UpdateRestaurantResponse> updateRestaurantResponse = restTemplate.exchange(baseUrl("/restaurants/"+restaurantId), HttpMethod.PUT, new HttpEntity(restaurantInfo), UpdateRestaurantResponse.class);
    Assert.assertEquals(updateRestaurantResponse.getStatusCode(), HttpStatus.OK);

  }

}

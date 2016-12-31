package net.chrisrichardson.eventstore.examples.management.restaurantsservice.web;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.CreateRestaurantResponse;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.DeleteRestaurantResponse;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.UpdateRestaurantResponse;
import net.chrisrichardson.eventstore.examples.management.restaurantsservice.backend.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

  private RestaurantService restaurantService;

  @Autowired
  public RestaurantController(RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }

  @RequestMapping(method = RequestMethod.POST)
  public CompletableFuture<CreateRestaurantResponse> createRestaurant(@RequestBody @Valid RestaurantInfo request) {
    return restaurantService.createRestaurant(request)
            .thenApply(entityAndEventInfo -> new CreateRestaurantResponse(entityAndEventInfo.getEntityId()));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public CompletableFuture<UpdateRestaurantResponse> updateRestaurant(@PathVariable("id") String restaurantId,
                                                                      @RequestBody  @Valid RestaurantInfo request) {
    return restaurantService.updateRestaurant(restaurantId, request)
            .thenApply(entityAndEventInfo -> new UpdateRestaurantResponse(entityAndEventInfo.getEntityVersion().asString()));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public CompletableFuture<DeleteRestaurantResponse> deleteRestaurant(@PathVariable("id") String restaurantId) {
    return restaurantService.deleteRestaurant(restaurantId).thenApply(entityAndEventInfo -> new DeleteRestaurantResponse(entityAndEventInfo.getEntityVersion().asString()));
  }
}

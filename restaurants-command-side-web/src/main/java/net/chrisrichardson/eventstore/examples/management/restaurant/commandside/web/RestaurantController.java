package net.chrisrichardson.eventstore.examples.management.restaurant.commandside.web;

import net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantService;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rx.Observable;

import javax.validation.Valid;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

  private RestaurantService restaurantService;

  @Autowired
  public RestaurantController(RestaurantService restaurantService) {
    this.restaurantService = restaurantService;
  }

  @RequestMapping(method = RequestMethod.POST)
  public Observable<CreateRestaurantResponse> createRestaurant(@RequestBody @Valid RestaurantInfo request) {
    return restaurantService.createRestaurant(request)
            .map(entityAndEventInfo -> new CreateRestaurantResponse(entityAndEventInfo.getEntityIdentifier().getId()));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  public Observable<UpdateRestaurantResponse> updateRestaurant(@PathVariable("id") String restaurantId,
                                                               @RequestBody  @Valid RestaurantInfo request) {
    return restaurantService.updateRestaurant(restaurantId, request)
            .map(entityAndEventInfo -> new UpdateRestaurantResponse(entityAndEventInfo.entityVersion().asString()));
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public Observable<DeleteRestaurantResponse> deleteRestaurant(@PathVariable("id") String restaurantId) {
    return restaurantService.deleteRestaurant(restaurantId).map(entityAndEventInfo -> new DeleteRestaurantResponse(entityAndEventInfo.entityVersion().asString()));
  }
}

package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.redis;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.Address;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.DeliveryTime;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;

import java.util.List;

public interface RestaurantQuerySideService {

    void add(final String id, final RestaurantInfo restaurant);

    void delete(String id, RestaurantInfo restaurant);

    List<RestaurantInfo> findAvailableRestaurants(Address deliveryAddress, DeliveryTime deliveryTime);

    RestaurantInfo findById(String id);
}

package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend;

import io.eventuate.DispatchedEvent;
import io.eventuate.EventHandlerMethod;
import io.eventuate.EventSubscriber;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.event.RestaurantCreatedEvent;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.event.RestaurantDeletedEvent;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.event.RestaurantUpdatedEvent;
import net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.redis.RestaurantQuerySideRedisService;

@EventSubscriber(id="restaurantQuerySideEventHandlers")
public class RestaurantViewEventSubscriber {

    private RestaurantQuerySideRedisService restaurantQuerySideRedisService;

    public RestaurantViewEventSubscriber(RestaurantQuerySideRedisService restaurantQuerySideRedisService) {
        this.restaurantQuerySideRedisService = restaurantQuerySideRedisService;
    }

    @EventHandlerMethod
    public void create(DispatchedEvent<RestaurantCreatedEvent> de) {
        RestaurantCreatedEvent event = de.getEvent();
        String id = de.getEntityId();

        restaurantQuerySideRedisService.add(id, event.getRestaurantInfo());
    }

    @EventHandlerMethod
    public void update(DispatchedEvent<RestaurantUpdatedEvent> de) {
        RestaurantUpdatedEvent event = de.getEvent();
        String id = de.getEntityId();
        RestaurantInfo restaurantInfo = restaurantQuerySideRedisService.findById(id);
        restaurantQuerySideRedisService.delete(id, restaurantInfo);
        restaurantQuerySideRedisService.add(id, event.getRestaurantInfo());
    }

    @EventHandlerMethod
    public void delete(DispatchedEvent<RestaurantDeletedEvent> de) {
        String id = de.getEntityId();
        RestaurantInfo restaurantInfo = restaurantQuerySideRedisService.findById(id);
        restaurantQuerySideRedisService.delete(id, restaurantInfo);
    }
}

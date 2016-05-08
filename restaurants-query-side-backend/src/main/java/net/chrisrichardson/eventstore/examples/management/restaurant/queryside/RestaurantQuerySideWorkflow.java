package net.chrisrichardson.eventstore.examples.management.restaurant.queryside;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.event.RestaurantCreatedEvent;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.event.RestaurantDeletedEvent;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.event.RestaurantUpdatedEvent;
import net.chrisrichardson.eventstore.examples.management.restaurant.queryside.redis.RestaurantQuerySideRedisService;
import net.chrisrichardson.eventstore.subscriptions.CompoundEventHandler;
import net.chrisrichardson.eventstore.subscriptions.DispatchedEvent;
import net.chrisrichardson.eventstore.subscriptions.EventHandlerMethod;
import net.chrisrichardson.eventstore.subscriptions.EventSubscriber;
import rx.Observable;

@EventSubscriber(id="restaurantQuerySideEventHandlers")
public class RestaurantQuerySideWorkflow implements CompoundEventHandler {

    private RestaurantQuerySideRedisService restaurantQuerySideRedisService;

    public RestaurantQuerySideWorkflow(RestaurantQuerySideRedisService restaurantQuerySideRedisService) {
        this.restaurantQuerySideRedisService = restaurantQuerySideRedisService;
    }

    @EventHandlerMethod
    public Observable<Object> create(DispatchedEvent<RestaurantCreatedEvent> de) {
        RestaurantCreatedEvent event = de.event();
        String id = de.getEntityIdentifier().getId();

        restaurantQuerySideRedisService.add(id, event.getRestaurantInfo());
        return Observable.just(null);
    }

    @EventHandlerMethod
    public Observable<Object> update(DispatchedEvent<RestaurantUpdatedEvent> de) {
        RestaurantUpdatedEvent event = de.event();
        String id = de.getEntityIdentifier().getId();
        RestaurantInfo restaurantInfo = restaurantQuerySideRedisService.findById(id);
        restaurantQuerySideRedisService.delete(id, restaurantInfo);
        restaurantQuerySideRedisService.add(id, event.getRestaurantInfo());
        return Observable.just(null);
    }

    @EventHandlerMethod
    public Observable<Object> delete(DispatchedEvent<RestaurantDeletedEvent> de) {
        String id = de.getEntityIdentifier().getId();
        RestaurantInfo restaurantInfo = restaurantQuerySideRedisService.findById(id);
        restaurantQuerySideRedisService.delete(id, restaurantInfo);
        return Observable.just(null);
    }
}

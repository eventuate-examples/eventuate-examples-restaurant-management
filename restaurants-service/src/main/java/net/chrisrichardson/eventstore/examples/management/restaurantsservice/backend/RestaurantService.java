package net.chrisrichardson.eventstore.examples.management.restaurantsservice.backend;

import io.eventuate.AggregateRepository;
import io.eventuate.EntityWithIdAndVersion;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;

import java.util.concurrent.CompletableFuture;

public class RestaurantService {

    private final AggregateRepository<RestaurantAggregate, RestaurantCommand> repository;

    public RestaurantService(AggregateRepository<RestaurantAggregate, RestaurantCommand> repository) {
        this.repository = repository;
    }

    public CompletableFuture<EntityWithIdAndVersion<RestaurantAggregate>> createRestaurant(RestaurantInfo restaurantInfo) {
        return repository.save(new CreateRestaurantCommand(restaurantInfo));
    }

    public CompletableFuture<EntityWithIdAndVersion<RestaurantAggregate>> updateRestaurant(String id, RestaurantInfo restaurantInfo) {
        return repository.update(id, new UpdateRestaurantCommand(restaurantInfo));
    }

    public CompletableFuture<EntityWithIdAndVersion<RestaurantAggregate>> deleteRestaurant(String id) {
        return repository.update(id, new DeleteRestaurantCommand());
    }
}

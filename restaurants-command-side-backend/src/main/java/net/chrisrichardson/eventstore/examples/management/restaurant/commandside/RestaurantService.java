package net.chrisrichardson.eventstore.examples.management.restaurant.commandside;

import net.chrisrichardson.eventstore.EntityIdentifier;
import net.chrisrichardson.eventstore.EntityWithIdAndVersion;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.repository.AggregateRepository;
import rx.Observable;

public class RestaurantService {

    private final AggregateRepository<RestaurantAggregate, RestaurantCommand> repository;

    public RestaurantService(AggregateRepository<RestaurantAggregate, RestaurantCommand> repository) {
        this.repository = repository;
    }

    public Observable<EntityWithIdAndVersion<RestaurantAggregate>> createRestaurant(RestaurantInfo restaurantInfo) {
        return repository.save(new CreateRestaurantCommand(restaurantInfo));
    }

    public Observable<EntityWithIdAndVersion<RestaurantAggregate>> updateRestaurant(String id, RestaurantInfo restaurantInfo) {
        return repository.update(new EntityIdentifier(id), new UpdateRestaurantCommand(restaurantInfo));
    }

    public Observable<EntityWithIdAndVersion<RestaurantAggregate>> deleteRestaurant(String id) {
        return repository.update(new EntityIdentifier(id), new DeleteRestaurantCommand());
    }
}

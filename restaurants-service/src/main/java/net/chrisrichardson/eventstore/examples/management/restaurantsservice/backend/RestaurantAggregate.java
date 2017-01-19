package net.chrisrichardson.eventstore.examples.management.restaurantsservice.backend;

import io.eventuate.Event;
import io.eventuate.EventUtil;
import io.eventuate.ReflectiveMutableCommandProcessingAggregate;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.event.RestaurantCreatedEvent;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.event.RestaurantDeletedEvent;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.event.RestaurantUpdatedEvent;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAggregate  extends ReflectiveMutableCommandProcessingAggregate<RestaurantAggregate, RestaurantCommand> {
    private RestaurantInfo restaurant;
    private boolean deleted = false;

    public List<Event> process(CreateRestaurantCommand cmd) {
        return EventUtil.events(new RestaurantCreatedEvent(cmd.getRestaurantInfo()));
    }

    public List<Event> process(UpdateRestaurantCommand cmd) {
        if(!this.deleted) {
            return EventUtil.events(new RestaurantUpdatedEvent(cmd.getRestaurantInfo()));
        }
        return new ArrayList<>();
    }

    public List<Event> process(DeleteRestaurantCommand cmd) {
        if(!this.deleted) {
            return EventUtil.events(new RestaurantDeletedEvent());
        }
        return new ArrayList<>();
    }

    public void apply(RestaurantCreatedEvent event) {
        this.restaurant = event.getRestaurantInfo();
    }

    public void apply(RestaurantUpdatedEvent event) {
        this.restaurant = event.getRestaurantInfo();
    }

    public void apply(RestaurantDeletedEvent event) {
        this.deleted = true;
    }

    public RestaurantInfo getRestaurant() {
        return restaurant;
    }
}

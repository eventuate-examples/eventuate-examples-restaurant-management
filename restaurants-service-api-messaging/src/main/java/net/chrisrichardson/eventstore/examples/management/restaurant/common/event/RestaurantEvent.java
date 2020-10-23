package net.chrisrichardson.eventstore.examples.management.restaurant.common.event;


import io.eventuate.Event;
import io.eventuate.EventEntity;

@EventEntity(entity="net.chrisrichardson.eventstore.examples.management.restaurantsservice.backend.RestaurantAggregate")
public abstract  class RestaurantEvent implements Event {
}
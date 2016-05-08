package net.chrisrichardson.eventstore.examples.management.restaurant.common.event;

import net.chrisrichardson.eventstore.Event;
import net.chrisrichardson.eventstore.EventEntity;

@EventEntity(entity="net.chrisrichardson.eventstore.examples.management.restaurant.commandside.RestaurantAggregate")
public abstract  class RestaurantEvent implements Event {
}
package net.chrisrichardson.eventstore.examples.management.restaurant.common.event;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;

public class RestaurantUpdatedEvent extends RestaurantEvent {
    private RestaurantInfo restaurantInfo;

    public RestaurantUpdatedEvent() {
    }

    public RestaurantUpdatedEvent(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }
}
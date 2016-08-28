package net.chrisrichardson.eventstore.examples.management.restaurant.common.event;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;

public class RestaurantCreatedEvent extends RestaurantEvent {
    private RestaurantInfo restaurantInfo;

    public RestaurantCreatedEvent() {
    }

    public RestaurantCreatedEvent(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }
}

package net.chrisrichardson.eventstore.examples.management.restaurantsservice.backend;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CreateRestaurantCommand  implements RestaurantCommand {
    private RestaurantInfo restaurantInfo;

    public CreateRestaurantCommand() {
    }

    public CreateRestaurantCommand(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    public RestaurantInfo getRestaurantInfo() {
        return restaurantInfo;
    }

    public void setRestaurantInfo(RestaurantInfo restaurantInfo) {
        this.restaurantInfo = restaurantInfo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

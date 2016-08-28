package net.chrisrichardson.eventstore.examples.management.restaurant.commandside.web;


public class CreateRestaurantResponse {

  private String restaurantId;

  public CreateRestaurantResponse() {
  }

  public CreateRestaurantResponse(String restaurantId) {
    this.restaurantId = restaurantId;
  }

  public String getRestaurantId() {
    return restaurantId;
  }

  public void setRestaurantId(String restaurantId) {
    this.restaurantId = restaurantId;
  }
}

package net.chrisrichardson.eventstore.examples.management.restaurant.common;


public class UpdateRestaurantResponse {

  private String version;

  public UpdateRestaurantResponse() {
  }

  public UpdateRestaurantResponse(String version) {
    this.version = version;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}

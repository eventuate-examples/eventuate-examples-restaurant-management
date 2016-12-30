package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.util;


public class AvailableRestaurantKeys {

  public static String closingTimesKey(String zipCode, int dayOfWeek) {
    return RedisUtil.key("closingTimes", zipCode, dayOfWeek);
  }

}

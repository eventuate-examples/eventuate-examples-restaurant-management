package net.chrisrichardson.eventstore.examples.management.restaurant.common;


import org.apache.commons.lang.builder.ToStringBuilder;

public class FindAvailableRestaurantsRequest {

  private String zipcode;
  private int dayOfWeek;
  private int hour;
  private int minute;

  public FindAvailableRestaurantsRequest() {
  }

  public FindAvailableRestaurantsRequest(String zipcode, int dayOfWeek, int hour, int minute) {
    this.zipcode = zipcode;
    this.dayOfWeek = dayOfWeek;
    this.hour = hour;
    this.minute = minute;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public int getDayOfWeek() {
    return dayOfWeek;
  }

  public void setDayOfWeek(int dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  public int getHour() {
    return hour;
  }

  public void setHour(int hour) {
    this.hour = hour;
  }

  public int getMinute() {
    return minute;
  }

  public void setMinute(int minute) {
    this.minute = minute;
  }

  public Address makeAddress() {
    return new Address(null, null, null, null, zipcode);
  }

  public DeliveryTime makeDeliveryTime() {
    return new DeliveryTime(dayOfWeek, hour, minute);
  }

  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}

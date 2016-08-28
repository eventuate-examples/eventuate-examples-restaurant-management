package net.chrisrichardson.eventstore.examples.management.restaurant.common;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class DeliveryTime {
  private int dayOfWeek;
  private int hour;
  private int minute;

  public DeliveryTime(int dayOfWeek, int hour, int minute) {
    this.dayOfWeek = dayOfWeek;
    this.hour = hour;
    this.minute = minute;
  }

  public int getDayOfWeek() {
    return dayOfWeek;
  }

  public int getTimeOfDay() {
    return hour * 100 + minute;
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }

  @Override
  public boolean equals(Object o) {
    return EqualsBuilder.reflectionEquals(this, o);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}

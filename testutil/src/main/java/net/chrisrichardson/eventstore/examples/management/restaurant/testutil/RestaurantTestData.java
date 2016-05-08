package net.chrisrichardson.eventstore.examples.management.restaurant.testutil;


import net.chrisrichardson.eventstore.examples.management.restaurant.common.Address;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.DeliveryTime;

import java.util.Calendar;
import java.util.Date;

public class RestaurantTestData {

    public static DeliveryTime makeGoodDeliveryTime() {
        return getTimeTomorrow(20);
    }

    public static DeliveryTime getTimeTomorrow(int hour) {
        return getTimeTomorrow(hour, 0);
    }

    public static DeliveryTime getTimeTomorrow(int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
            c.add(Calendar.DAY_OF_MONTH, 1);
        return new DeliveryTime(c.get(Calendar.DAY_OF_WEEK), hour, minute);
    }

    public static Address getADDRESS1() {
        return new Address("1 somewhere", null, "Oakland", "CA",
                ZIP1);
    }

    public static Address getADDRESS2() {
        return new Address("22 somewhere else", null, "Oakland", "CA",
                ZIP2);
    }

    public static Address getBAD_ADDRESS() {
        return new Address("1 somewhere", null, "Oakland", "CA",
                BAD_ZIP);
    }

    public static String ZIP1 = "94619";
    public static String ZIP2 = "94618";

    public static String BAD_ZIP = "45001";

    public static final String GOOD_ZIP_CODE = "94619";

    public static final String BAD_ZIP_CODE = "94618";

    public static DeliveryTime makeBadDeliveryTime() {
      return getTimeTomorrow(4);
    }

	public static DeliveryTime makeDeliveryTime(int dayOfWeek, int hour, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);
        c.clear(Calendar.MILLISECOND);
        if (c.getTime().before(new Date()))
        	c.add(Calendar.DAY_OF_MONTH, 7);
        return new DeliveryTime(c.get(Calendar.DAY_OF_WEEK), hour, minute);

  }

}
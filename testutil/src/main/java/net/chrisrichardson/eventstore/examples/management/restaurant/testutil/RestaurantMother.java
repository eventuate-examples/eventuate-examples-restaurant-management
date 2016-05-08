package net.chrisrichardson.eventstore.examples.management.restaurant.testutil;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.MenuItem;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.TimeRange;

import java.util.*;

public class RestaurantMother {

    private static final String LATE_NIGHT_SNACK = "Late Night Snack";
    public static final String MONTCLAIR_EGGSHOP = "Montclair Eggshop";
    public static final String RESTAURANT_NAME = "Ajanta";
    public static final int OPENING_HOUR = 18;
    public static final int OPENING_MINUTE = 12;
    public static final int CLOSING_MINUTE = 50;
    public static final int CLOSING_HOUR = 22;

    public static RestaurantInfo makeRestaurant() {
        return makeRestaurant("94619", System.currentTimeMillis());
    }

    public static RestaurantInfo makeRestaurant(long timestamp) {
        return makeRestaurant("94619", timestamp);
    }

    public static RestaurantInfo makeRestaurant(String zipCode, long timestamp) {
        Set<TimeRange> hours = makeOpeningHours(OPENING_MINUTE);
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        MenuItem mi1 = new MenuItem("Samosas", 5.00);
        MenuItem mi2 = new MenuItem("Chicken Tikka", 6.50);
        menuItems.add(mi1);
        menuItems.add(mi2);

        Set<String> serviceArea = new HashSet<String>();
        serviceArea.add(zipCode);
        serviceArea.add("99999");

        return new RestaurantInfo(RESTAURANT_NAME+timestamp, "Indian", serviceArea,
                hours, menuItems);
    }

    public static RestaurantInfo makeEggShopRestaurant(long timestamp) {
        Set<TimeRange> openingHours = new HashSet<TimeRange>();
        for (int dayOfWeek : new int[]{Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY}) {
            openingHours.add(new TimeRange(dayOfWeek, 7, 0, 14, 30));
        }
        for (int dayOfWeek : new int[]{Calendar.SATURDAY, Calendar.SUNDAY}) {
            openingHours.add(new TimeRange(dayOfWeek, 8, 0, 15, 00));
        }

        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        return new RestaurantInfo(MONTCLAIR_EGGSHOP+timestamp, "Diner", Collections.singleton("94619"), openingHours, menuItems);
    }

    public static RestaurantInfo makeLateNightTacos(long timestamp) {
        Set<TimeRange> openingHours = new HashSet<TimeRange>();
        for (int dayOfWeek : new int[]{Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY}) {
            openingHours.add(new TimeRange(dayOfWeek, 22, 0, 23, 0));
        }

        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        return new RestaurantInfo(LATE_NIGHT_SNACK+timestamp, "Diner", Collections.singleton("94619"), openingHours, menuItems);
    }

    public static Set<TimeRange> makeOpeningHours(int openMinute) {
        return makeOpeningHours(OPENING_HOUR, openMinute);
    }

    public static Set<TimeRange> makeOpeningHours(int openingHour, int openMinute) {
        Set<TimeRange> hours = new HashSet<TimeRange>();

        for (int dayOfWeek : new int[]{Calendar.MONDAY, Calendar.TUESDAY, Calendar.WEDNESDAY, Calendar.THURSDAY, Calendar.FRIDAY, Calendar.SATURDAY, Calendar.SUNDAY}) {
            hours.add(new TimeRange(dayOfWeek, openingHour, openMinute, CLOSING_HOUR, CLOSING_MINUTE));
            hours.add(new TimeRange(dayOfWeek, 11, 30, 14, 30));

        }
        return hours;
    }
}

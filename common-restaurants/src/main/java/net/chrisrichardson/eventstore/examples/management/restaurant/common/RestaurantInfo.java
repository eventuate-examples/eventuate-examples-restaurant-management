package net.chrisrichardson.eventstore.examples.management.restaurant.common;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;
import java.util.Set;

public class RestaurantInfo {
    private String name;
    @NotEmpty
    private Set<String> serviceArea;
    private String notificationEmailAddress;
    private List<MenuItem> menuItems;
    @NotEmpty
    private Set<TimeRange> openingHours;
    private String type;

    public RestaurantInfo() {
    }

    public RestaurantInfo(String name, String type, Set<String> serviceArea, Set<TimeRange> openingHours, List<MenuItem> menuItems) {
        this.name = name;
        this.type = type;
        this.serviceArea = serviceArea;
        this.openingHours = openingHours;
        this.menuItems = menuItems;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(Set<String> serviceArea) {
        this.serviceArea = serviceArea;
    }

    public String getNotificationEmailAddress() {
        return notificationEmailAddress;
    }

    public void setNotificationEmailAddress(String notificationEmailAddress) {
        this.notificationEmailAddress = notificationEmailAddress;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public Set<TimeRange> getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(Set<TimeRange> openingHours) {
        this.openingHours = openingHours;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

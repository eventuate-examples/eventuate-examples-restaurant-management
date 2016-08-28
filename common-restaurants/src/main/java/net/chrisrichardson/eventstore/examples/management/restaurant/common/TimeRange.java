package net.chrisrichardson.eventstore.examples.management.restaurant.common;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class TimeRange {
    private int dayOfWeek;
    private int openingTime;
    private int closingTime;

    public TimeRange() {
    }

    public TimeRange(int dayOfWeek, int openHour, int openMinute, int closeHour, int closeMinute) {
        this(dayOfWeek, openHour * 100 + openMinute, closeHour * 100 + closeMinute);
    }

    public TimeRange(int dayOfWeek, int openingTime, int closingTime) {
        this.dayOfWeek = dayOfWeek;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(int openingTime) {
        this.openingTime = openingTime;
    }

    public int getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
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

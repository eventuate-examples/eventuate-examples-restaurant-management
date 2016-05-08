package net.chrisrichardson.eventstore.examples.management.restaurant.commandside;

import net.chrisrichardson.eventstore.examples.management.restaurant.testutil.AbstractEntityEventTest;

public class RestaurantEventTest  extends AbstractEntityEventTest {

    @Override
    protected Class<RestaurantAggregate> entityClass() {
        return RestaurantAggregate.class;
    }
}
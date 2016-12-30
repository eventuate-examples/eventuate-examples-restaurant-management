package net.chrisrichardson.eventstore.examples.management.restaurantsservice;

import net.chrisrichardson.eventstore.examples.management.restaurant.testutil.AbstractEntityEventTest;
import net.chrisrichardson.eventstore.examples.management.restaurantsservice.backend.RestaurantAggregate;

public class RestaurantEventTest  extends AbstractEntityEventTest {

    @Override
    protected Class<RestaurantAggregate> entityClass() {
        return RestaurantAggregate.class;
    }
}
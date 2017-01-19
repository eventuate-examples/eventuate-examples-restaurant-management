package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.Address;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.DeliveryTime;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.examples.management.restaurant.testutil.RestaurantMother;
import net.chrisrichardson.eventstore.examples.management.restaurant.testutil.RestaurantTestData;
import net.chrisrichardson.eventstore.examples.management.restaurant.testutil.TestUtil;
import net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.redis.RestaurantQuerySideService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AvailableRestaurantManagementServiceIntegrationTestConfiguration.class)
@IntegrationTest
public class AvailableRestaurantManagementServiceIntegrationTest {

    @Autowired
    RestaurantQuerySideService restaurantQuerySideService;

    private void assertFoundAjanta(List<RestaurantInfo> results, long timestamp) {
        assertFoundRestaurant("Ajanta"+timestamp, results);
    }

    private void assertFoundRestaurant(String expectedName, List<RestaurantInfo> results) {
        Assert.assertTrue(results.stream().filter(restaurantInfo -> restaurantInfo.getName().equals(expectedName)).findFirst().isPresent());
    }

    int counter = 0;

    private String add(RestaurantInfo restaurantInfo) {
        String id = "r" + System.currentTimeMillis() + "-" + counter++;
        restaurantQuerySideService.add(id, restaurantInfo);
        return id;
    }

    private void update(String id, RestaurantInfo restaurantInfo) {
        RestaurantInfo ri = restaurantQuerySideService.findById(id);
        restaurantQuerySideService.delete(id, ri);
        restaurantQuerySideService.add(id, restaurantInfo);
    }

    private void delete(String id) {
        RestaurantInfo ri = restaurantQuerySideService.findById(id);
        restaurantQuerySideService.delete(id, ri);
    }

    @Test
    public void testSomething() {
        long timestamp = System.currentTimeMillis();

        String ajantaId = add(RestaurantMother.makeRestaurant(timestamp));
        add(RestaurantMother.makeEggShopRestaurant(timestamp));
        add(RestaurantMother.makeLateNightTacos(timestamp));

        TestUtil.awaitPredicateTrue(t -> restaurantQuerySideService.findById(ajantaId),
                restaurantInfo -> restaurantInfo != null);


        DeliveryTime deliveryTime = RestaurantTestData.makeGoodDeliveryTime();
        Address deliveryAddress = RestaurantTestData.getADDRESS1();
        List<RestaurantInfo> results = restaurantQuerySideService.findAvailableRestaurants(deliveryAddress, deliveryTime);
        assertFoundAjanta(results, timestamp);
    }

    @Test
    public void testFindAvailableRestaurants_Monday8Am() {
        long timestamp = System.currentTimeMillis();

        add(RestaurantMother.makeRestaurant(timestamp));
        String eggshopId = add(RestaurantMother.makeEggShopRestaurant(timestamp));
        add(RestaurantMother.makeLateNightTacos(timestamp));

        TestUtil.awaitPredicateTrue(t -> restaurantQuerySideService.findById(eggshopId),
                restaurantInfo -> restaurantInfo != null);

        DeliveryTime deliveryTime = RestaurantTestData.makeDeliveryTime(Calendar.MONDAY, 8, 0);
        Address deliveryAddress = RestaurantTestData.getADDRESS1();
        List<RestaurantInfo> results = restaurantQuerySideService.findAvailableRestaurants(deliveryAddress, deliveryTime);
        assertFoundRestaurant(RestaurantMother.MONTCLAIR_EGGSHOP+timestamp, results);
    }

    @Test
    public void testFindAvailableRestaurants_None() {
        long timestamp = System.currentTimeMillis();

        String ajantaId = add(RestaurantMother.makeRestaurant(timestamp));
        String eggshopId = add(RestaurantMother.makeEggShopRestaurant(timestamp));
        String lateNightSnackId = add(RestaurantMother.makeLateNightTacos(timestamp));

        TestUtil.awaitPredicateTrue(t -> restaurantQuerySideService.findById(ajantaId),
                restaurantInfo -> restaurantInfo != null);
        TestUtil.awaitPredicateTrue(t -> restaurantQuerySideService.findById(eggshopId),
                restaurantInfo -> restaurantInfo != null);
        TestUtil.awaitPredicateTrue(t -> restaurantQuerySideService.findById(lateNightSnackId),
                restaurantInfo -> restaurantInfo != null);

        DeliveryTime deliveryTime = RestaurantTestData.makeBadDeliveryTime();
        Address deliveryAddress = RestaurantTestData.getADDRESS1();
        List<RestaurantInfo> results = restaurantQuerySideService.findAvailableRestaurants(deliveryAddress, deliveryTime);
        Assert.assertTrue(results.isEmpty());
    }

    private void updateRestaurantOpeningHours(RestaurantInfo restaurant) {
        restaurant.setOpeningHours(RestaurantMother.makeOpeningHours(RestaurantMother.OPENING_MINUTE - 1));
    }

    @Test
    public void testUpdateRestaurant() {
        long timestamp = System.currentTimeMillis();
        
        RestaurantInfo ajantaRestaurant = RestaurantMother.makeRestaurant(timestamp);
        String ajantaId = add(ajantaRestaurant);
        add(RestaurantMother.makeEggShopRestaurant(timestamp));
        add(RestaurantMother.makeLateNightTacos(timestamp));

        TestUtil.awaitPredicateTrue(t -> restaurantQuerySideService.findById(ajantaId),
                restaurantInfo -> restaurantInfo != null);

        updateRestaurantOpeningHours(ajantaRestaurant);
        update(ajantaId, ajantaRestaurant);

        TestUtil.awaitPredicateTrue(t -> restaurantQuerySideService.findById(ajantaId),
                restaurantInfo -> restaurantInfo.getOpeningHours().equals(ajantaRestaurant.getOpeningHours()));

        DeliveryTime deliveryTime = RestaurantTestData.getTimeTomorrow(RestaurantMother.OPENING_HOUR, RestaurantMother.OPENING_MINUTE - 1);
        Address deliveryAddress = RestaurantTestData.getADDRESS1();
        List<RestaurantInfo> results = restaurantQuerySideService.findAvailableRestaurants(deliveryAddress, deliveryTime);
        assertFoundAjanta(results, timestamp);

    }
}

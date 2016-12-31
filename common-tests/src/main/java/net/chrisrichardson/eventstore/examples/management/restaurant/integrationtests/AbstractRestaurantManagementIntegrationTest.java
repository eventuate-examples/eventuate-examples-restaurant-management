package net.chrisrichardson.eventstore.examples.management.restaurant.integrationtests;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.*;
import net.chrisrichardson.eventstore.examples.management.restaurant.testutil.RestaurantMother;
import net.chrisrichardson.eventstore.examples.management.restaurant.testutil.RestaurantTestData;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static net.chrisrichardson.eventstore.examples.management.restaurant.testutil.TestUtil.awaitNotFound;
import static net.chrisrichardson.eventstore.examples.management.restaurant.testutil.TestUtil.awaitPredicateTrue;
import static net.chrisrichardson.eventstore.examples.management.restaurant.testutil.TestUtil.awaitSuccessfulRequest;
import static org.junit.Assert.assertEquals;

public abstract class AbstractRestaurantManagementIntegrationTest {

    private String commandSideUrl(String path) {
        return "http://" + getHost() + ":" + getCommandSidePort() + "/" + path;
    }

    private String querySideUrl(String path) {
        return "http://" + getHost() + ":" + getQuerySidePort() + "/" + path;
    }

    @Autowired
    RestTemplate restTemplate;


    @Test
    public void shouldCreateUpdateAndDeleteRestaurant() {
        RestaurantInfo restaurantInfo = RestaurantMother.makeRestaurant();

        String restaurantId = createRestaurantAndAwaitCreationInView(restaurantInfo);
        RestaurantInfo createdRestaurant = getRestaurant(restaurantId);
        assertEquals(restaurantInfo, createdRestaurant);

        RestaurantInfo restaurantInfoToUpdate =  restaurantInfo;
        restaurantInfoToUpdate.setName("New Bar");

        ResponseEntity<UpdateRestaurantResponse> updateRestaurantResponse = restTemplate.exchange(commandSideUrl("/restaurants/"+restaurantId), HttpMethod.PUT, new HttpEntity(restaurantInfoToUpdate), UpdateRestaurantResponse.class);
        assertEquals(updateRestaurantResponse.getStatusCode(), HttpStatus.OK);

        awaitPredicateTrue(t -> restTemplate.getForEntity(querySideUrl("/restaurants/"+restaurantId), RestaurantInfo.class),
                responseEntity -> responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null && responseEntity.getBody().getName().equals("New Bar"));

        RestaurantInfo updatedRestaurant = getRestaurant(restaurantId);
        assertEquals(restaurantInfoToUpdate, updatedRestaurant);

        restTemplate.exchange(commandSideUrl("/restaurants/"+restaurantId), HttpMethod.DELETE, HttpEntity.EMPTY, UpdateRestaurantResponse.class);
        awaitRestaurantDeletionInView(restaurantId);
    }

    @Test
    public void shouldFindAvailableRestaurants() {
        long timestamp = System.currentTimeMillis();

        createRestaurantAndAwaitCreationInView(RestaurantMother.makeRestaurant(timestamp));
        createRestaurantAndAwaitCreationInView(RestaurantMother.makeEggShopRestaurant(timestamp));
        createRestaurantAndAwaitCreationInView(RestaurantMother.makeLateNightTacos(timestamp));

        DeliveryTime deliveryTime = RestaurantTestData.makeGoodDeliveryTime();
        Address deliveryAddress = RestaurantTestData.getADDRESS1();

        FindAvailableRestaurantsRequest request = new FindAvailableRestaurantsRequest(deliveryAddress.getZip(), deliveryTime.getDayOfWeek(), deliveryTime.getHour(), deliveryTime.getMinute());

        ResponseEntity<RestaurantInfo[]> availableRestaurants = restTemplate.getForEntity(querySideUrl("/restaurants/availablerestaurants?zipcode="+deliveryAddress.getZip()+"&dayOfWeek="+deliveryTime.getDayOfWeek()+"&hour="+deliveryTime.getHour()+"&minute="+deliveryTime.getMinute()), RestaurantInfo[].class);

        assertFoundAjanta(Arrays.asList(availableRestaurants.getBody()), timestamp);
    }

    private RestaurantInfo getRestaurant(String id) {
        ResponseEntity<RestaurantInfo> responseEntity = restTemplate.getForEntity(querySideUrl("/restaurants/"+id), RestaurantInfo.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        return responseEntity.getBody();
    }

    protected RestaurantInfo awaitRestaurantCreationInView(String id) {
        return (RestaurantInfo) awaitSuccessfulRequest(() -> restTemplate.getForEntity(querySideUrl("/restaurants/"+id), RestaurantInfo.class));
    }

    protected void awaitRestaurantDeletionInView(String id) {
        awaitNotFound(() -> restTemplate.getForEntity(querySideUrl("/restaurants/"+id), RestaurantInfo.class));
    }

    private String createRestaurantAndAwaitCreationInView(RestaurantInfo restaurantInfo) {
        ResponseEntity<CreateRestaurantResponse> createRestaurantResponse = restTemplate.postForEntity(commandSideUrl("/restaurants"), restaurantInfo, CreateRestaurantResponse.class);
        assertEquals(createRestaurantResponse.getStatusCode(), HttpStatus.OK);
        final String restaurantId = createRestaurantResponse.getBody().getRestaurantId();

        awaitRestaurantCreationInView(restaurantId);

        return restaurantId;
    }

    private void assertFoundAjanta(List<RestaurantInfo> results, long timestamp) {
        assertFoundRestaurant("Ajanta"+timestamp, results);
    }

    private void assertFoundRestaurant(String expectedName, List<RestaurantInfo> results) {
        Assert.assertTrue(results.stream().filter(restaurantInfo -> restaurantInfo.getName().equals(expectedName)).findFirst().isPresent());
    }

    protected abstract String getHost();

    protected abstract int getCommandSidePort();

    protected abstract int getQuerySidePort();
}

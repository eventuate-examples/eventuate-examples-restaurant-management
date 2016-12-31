package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.redis;

import net.chrisrichardson.eventstore.examples.management.restaurant.common.Address;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.DeliveryTime;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.TimeRange;
import net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.util.AvailableRestaurantKeys;
import net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.util.RedisEntityKeyFormatter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestaurantQuerySideRedisService implements RestaurantQuerySideService {
    private StringRedisTemplate redisTemplate;
    private RedisTemplate<String, RestaurantInfo> restaurantTemplate;

    private RedisEntityKeyFormatter keyFormatter = new RedisEntityKeyFormatter(RestaurantInfo.class);

    public RestaurantQuerySideRedisService(StringRedisTemplate redisTemplate, RedisTemplate<String, RestaurantInfo> restaurantTemplate) {
        this.redisTemplate = redisTemplate;
        this.restaurantTemplate = restaurantTemplate;
    }

    @Override
    public void add(final String id, final RestaurantInfo restaurant) {
        addRestaurantInfo(id, restaurant);
        addAvailabilityIndexEntries(id, restaurant);
    }

    public void addRestaurantInfo(String id, RestaurantInfo restaurant) {
        restaurantTemplate.opsForValue().set(keyFormatter.key(id), restaurant);
    }

    class ZSetEntry {

        public final String key;
        public final String value;
        public final int score;

        public ZSetEntry(String key, String value, int score) {
            this.key = key;
            this.value = value;
            this.score = score;
        }
    }

    private Stream<ZSetEntry> getEntries(String id, RestaurantInfo restaurant) {
        if(restaurant==null || restaurant.getOpeningHours()==null) {
            return Stream.empty();
        }
        return restaurant.getOpeningHours().stream().flatMap ( tr -> {
            String indexValue = formatTrId(id, tr);
            int dayOfWeek = tr.getDayOfWeek();
            int closingTime = tr.getClosingTime();
            return restaurant.getServiceArea().stream().map(zipCode ->
                    new ZSetEntry(closingTimesKey(zipCode, dayOfWeek), indexValue, closingTime)
            );
        });
    }

    private void addAvailabilityIndexEntries(String id, RestaurantInfo restaurant) {
        getEntries(id, restaurant).forEach(entry -> redisTemplate.opsForZSet().add(entry.key, entry.value, entry.score));
    }

    private String closingTimesKey(String zipCode, int dayOfWeek) {
        return AvailableRestaurantKeys.closingTimesKey(zipCode, dayOfWeek);
    }

    private String formatTrId(String id, TimeRange tr) {
        return tr.getOpeningTime() + "_" + id;
    }

    @Override
    public List<RestaurantInfo> findAvailableRestaurants(Address deliveryAddress, DeliveryTime deliveryTime) {
        String zipCode = deliveryAddress.getZip();
        int dayOfWeek = deliveryTime.getDayOfWeek();
        int timeOfDay = deliveryTime.getTimeOfDay();
        String closingTimesKey = closingTimesKey(zipCode, dayOfWeek);

        Set<String> restaurantIds =
                redisTemplate.opsForZSet().rangeByScore(closingTimesKey, timeOfDay, 2359).stream()
                .map(tr -> tr.split("_"))
                .filter(v -> Integer.parseInt(v[0]) <= timeOfDay)
                .map(v -> v[1])
                .collect(Collectors.toSet());

        Collection<String> keys = keyFormatter.keys(restaurantIds);
        return restaurantTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public void delete(String id, RestaurantInfo restaurant) {
        getEntries(id, restaurant).forEach(entry -> redisTemplate.opsForZSet().remove(entry.key, entry.value));
        redisTemplate.delete(keyFormatter.key(id));
    }

    @Override
    public RestaurantInfo findById(String id) {
        return restaurantTemplate.opsForValue().get(keyFormatter.key(id));
    }
}

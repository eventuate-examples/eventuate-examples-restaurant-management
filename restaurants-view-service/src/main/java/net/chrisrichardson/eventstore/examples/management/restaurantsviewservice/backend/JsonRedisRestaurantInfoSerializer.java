package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.chrisrichardson.eventstore.examples.management.restaurant.common.RestaurantInfo;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

public class JsonRedisRestaurantInfoSerializer implements RedisSerializer<RestaurantInfo> {

    private final ObjectMapper om;

    public JsonRedisRestaurantInfoSerializer() {
        this.om = new ObjectMapper();
    }

    @Override
    public byte[] serialize(RestaurantInfo t) throws SerializationException {
        try {
            return om.writeValueAsBytes(t);
        } catch (JsonProcessingException e) {
            throw new SerializationException(e.getMessage(), e);
        }
    }

    @Override
    public RestaurantInfo deserialize(byte[] bytes) throws SerializationException {

        if (bytes == null) {
            return null;
        }

        try {
            return om.readValue(bytes, RestaurantInfo.class);
        } catch (Exception e) {
            throw new SerializationException(e.getMessage(), e);
        }
    }
}
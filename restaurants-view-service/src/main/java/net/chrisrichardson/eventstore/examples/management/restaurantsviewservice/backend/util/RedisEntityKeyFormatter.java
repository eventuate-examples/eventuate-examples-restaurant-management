package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.util;

import java.util.ArrayList;
import java.util.Collection;


public class RedisEntityKeyFormatter {

  private String keyPrefix;

  public RedisEntityKeyFormatter(Class<?> type) {
    this.keyPrefix = type.getSimpleName() + ":entity:";
  }

  public String key(String id) {
    return keyPrefix + id;
  }

  public Collection<String> keys(Collection<String> ids) {
    Collection<String> result = new ArrayList<String>();
    for (String id : ids) {
      result.add(keyPrefix + id);
    }
    return result;
  }

}

package net.chrisrichardson.eventstore.examples.management.restaurantsviewservice.backend.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RedisUtil {

  public static String key(Object s, Object...rest) {
    StringBuffer sb = new StringBuffer(s.toString());
    for (Object o : rest) {
      sb.append(":").append(o);
    }
    return sb.toString();
  }

  static String toString(byte[] bytes) {
    return bytes == null ? "NULL_BYTES" : new String(bytes);
  }

  static void toString(Collection<byte[]> bytes, Collection<String> result) {
    for (byte[] bs : bytes) {
      result.add(toString(bs));
    }
  }

  static List<String> toString(List<byte[]> bytes) {
    List<String> result = new ArrayList<String>(bytes.size());
    toString(bytes, result);
    return result;
  }

  public static List<String> prepend(String prefix, List<String> strings) {
    List<String> result = new ArrayList<String>();
    for (String string : strings) {
      result.add(prefix + string);
    }
    return result;
  }

}

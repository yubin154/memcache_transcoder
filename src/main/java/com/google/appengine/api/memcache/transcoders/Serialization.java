package com.google.appengine.api.memcache.transcoders;

import static com.google.appengine.api.memcache.MemcacheSerialization.makePbKey;

import java.io.IOException;

/** AppEngine compatible key serialization. */
public final class Serialization {

  private Serialization() {}

  /**
   * Converts the user's key Object into a UTF8 string representation for the memcache request.
   * Because the underlying service has a length limit, we actually use the SHA1 hash of the
   * serialized object as its key if it's not a basic type. For the basic types (that is, {@code
   * String}, {@code Boolean}, and the fixed-point numbers), we use a human-readable representation.
   *
   * @param key
   * @return hash result. For the key {@code null}, the hash is also {@code null}.
   */
  public static String makeKey(Object key) throws IOException {
    return new String(makePbKey(key), "UTF-8");
  }

  /**
   * Converts list of user's key Objects into list of UTF8 string representation for bulk request.
   *
   * @param keys
   * @return list of hash result.
   */
  public static java.util.List<String> makeKeys(java.util.List keys) throws IOException {
    java.util.List<String> newKeys = new java.util.ArrayList<>();
    for (Object key : keys) {
      newKeys.add(new String(makePbKey(key), "UTF-8"));
    }
    return newKeys;
  }
}

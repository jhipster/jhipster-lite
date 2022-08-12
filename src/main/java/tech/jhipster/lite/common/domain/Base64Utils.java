package tech.jhipster.lite.common.domain;

import java.security.SecureRandom;
import java.util.Base64;

public final class Base64Utils {

  private static final SecureRandom random = new SecureRandom();

  private Base64Utils() {}

  public static String getBase64Secret() {
    return getBase64Secret(64);
  }

  private static String getBase64Secret(int length) {
    return Base64.getEncoder().encodeToString(getRandomHexString(length).getBytes());
  }

  private static String getRandomHexString(int length) {
    StringBuilder result = new StringBuilder();

    while (result.length() < length) {
      result.append(Integer.toHexString(random.nextInt()));
    }

    return result.substring(0, length);
  }
}

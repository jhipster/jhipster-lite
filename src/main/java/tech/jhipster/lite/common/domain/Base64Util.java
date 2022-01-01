package tech.jhipster.lite.common.domain;

import java.util.Random;
import org.apache.tomcat.util.codec.binary.Base64;

public class Base64Util {

  private Base64Util() {}

  public static String getBase64Secret() {
    return getBase64Secret(null);
  }

  public static String getBase64Secret(String value) {
    return getBase64Secret(value, 50);
  }

  public static String getBase64Secret(String value, int length) {
    return Base64.encodeBase64String(value != null ? value.getBytes() : getRandomHexString(length).getBytes());
  }

  private static String getRandomHexString(int length) {
    Random random = new Random();
    StringBuffer stringBuilder = new StringBuffer();
    while (stringBuilder.length() < length) {
      stringBuilder.append(Integer.toHexString(random.nextInt()));
    }
    return stringBuilder.toString().substring(0, length);
  }
}

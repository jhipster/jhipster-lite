package tech.jhipster.forge.common.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JhforgeUtils {

  private static final Logger log = LoggerFactory.getLogger(JhforgeUtils.class);

  private JhforgeUtils() {}

  public static String getProtocol(String value) {
    return Optional.ofNullable(value).map(key -> "https").orElse("http");
  }

  public static String getContextPath(String value) {
    return Optional.ofNullable(value).filter(StringUtils::isNotBlank).orElse("/");
  }

  public static String getHostAddress() {
    String hostAddress = "localhost";
    try {
      hostAddress = InetAddress.getLocalHost().getHostAddress();
    } catch (UnknownHostException e) {
      log.warn("The host name could not be determined, using `localhost` as fallback");
    }
    return hostAddress;
  }
}

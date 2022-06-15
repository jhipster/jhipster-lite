package tech.jhipster.lite.generator.client.react.security.jwt.domain;

import java.util.List;
import java.util.Map;

public class ReactJwt {

  private ReactJwt() {}

  public static List<String> dependencies() {
    return List.of("react-hook-form", "react-iconly", "axios", "@nextui-org/react");
  }

  public static List<String> devDependencies() {
    return List.of("sass");
  }
}

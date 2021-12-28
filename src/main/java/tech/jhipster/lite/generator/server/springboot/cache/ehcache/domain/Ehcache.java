package tech.jhipster.lite.generator.server.springboot.cache.ehcache.domain;

import tech.jhipster.lite.generator.buildtool.generic.domain.Dependency;

public class Ehcache {

  private Ehcache() {}

  public static Dependency jakartaXmlBindApi() {
    return Dependency.builder().groupId("jakarta.xml.bind").artifactId("jakarta.xml.bind-api").build();
  }

  public static Dependency glassfishJaxbRuntime() {
    return Dependency.builder().groupId("org.glassfish.jaxb").artifactId("jaxb-runtime").scope("runtime").build();
  }

  public static Dependency ehcacheDependency() {
    return Dependency.builder().groupId("org.ehcache").artifactId("ehcache").build();
  }
}

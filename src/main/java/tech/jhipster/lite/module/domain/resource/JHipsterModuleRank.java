package tech.jhipster.lite.module.domain.resource;

public enum JHipsterModuleRank {
  /**
   * Not really usable as is, unless you have good expertise on the subject
   * (ex: custom-jhlite)
   */
  RANK_D,

  /**
   * No known usage on production product
   * (ex: vue-core, the current version)
   */
  RANK_C,

  /**
   * One declared usage on production product
   * (ex: kipe-authorization)
   */
  RANK_B,

  /**
   * Multiple declared usages on production product, by multiple person, on various projects
   * and demonstrated usage on a talk, book or blog post
   * (ex: java-base)
   */
  RANK_A,

  /**
   * A and recognized to add some features that are not available otherwise.
   * Features must be really unique and this must be recognized by at least 10 person
   * with a vote on any social network (GitHub being a social network).
   * With those criteria spring-boot-cucumber-mvc is a good candidate (missing votes).
   */
  RANK_S,
}

package tech.jhipster.lite.error.domain;

public class NotPastTimeException extends AssertionException {

  public NotPastTimeException(String fieldName) {
    super(message(fieldName));
  }

  private static String message(String fieldName) {
    return new StringBuilder().append("Time in \"").append(fieldName).append("\" must be in the past but wasn't").toString();
  }
}

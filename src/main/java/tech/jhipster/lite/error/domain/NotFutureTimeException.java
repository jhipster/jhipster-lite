package tech.jhipster.lite.error.domain;

public class NotFutureTimeException extends AssertionException {

  public NotFutureTimeException(String fieldName) {
    super(message(fieldName));
  }

  private static String message(String fieldName) {
    return new StringBuilder().append("Time in \"").append(fieldName).append("\" must be in the future but wasn't").toString();
  }
}

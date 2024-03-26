package tech.jhipster.lite.shared.error.domain;

public class UrlSafeSingleWordException extends AssertionException {

  public UrlSafeSingleWordException(String field) {
    super(field, message(field));
  }

  private static String message(String field) {
    return "The field \"%s\" is not a single world containing only lower case characters and numbers".formatted(field);
  }

  @Override
  public AssertionErrorType type() {
    return AssertionErrorType.URL_SAFE_SINGLE_WORD;
  }
}

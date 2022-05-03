package tech.jhipster.lite.error.domain;

public class NumberValueTooLowException extends AssertionException {

  private NumberValueTooLowException(NumberValueTooLowExceptionBuilder builder) {
    super(builder.message());
  }

  public static NumberValueTooLowExceptionBuilder builder() {
    return new NumberValueTooLowExceptionBuilder();
  }

  public static class NumberValueTooLowExceptionBuilder {

    private String field;
    private double minValue;
    private double value;

    public NumberValueTooLowExceptionBuilder field(String field) {
      this.field = field;

      return this;
    }

    public NumberValueTooLowExceptionBuilder minValue(double minValue) {
      this.minValue = minValue;

      return this;
    }

    public NumberValueTooLowExceptionBuilder value(double value) {
      this.value = value;

      return this;
    }

    public String message() {
      return new StringBuilder()
        .append("Value of field \"")
        .append(field)
        .append("\" must be at least ")
        .append(minValue)
        .append(" but was ")
        .append(value)
        .toString();
    }

    public NumberValueTooLowException build() {
      return new NumberValueTooLowException(this);
    }
  }
}

package tech.jhipster.lite.error.domain;

public class NumberValueTooHighException extends AssertionException {

  private NumberValueTooHighException(NumberValueTooHighExceptionBuilder builder) {
    super(builder.message());
  }

  public static NumberValueTooHighExceptionBuilder builder() {
    return new NumberValueTooHighExceptionBuilder();
  }

  public static class NumberValueTooHighExceptionBuilder {

    private String field;
    private double maxValue;
    private double value;

    public NumberValueTooHighExceptionBuilder field(String field) {
      this.field = field;

      return this;
    }

    public NumberValueTooHighExceptionBuilder maxValue(double minValue) {
      this.maxValue = minValue;

      return this;
    }

    public NumberValueTooHighExceptionBuilder value(double value) {
      this.value = value;

      return this;
    }

    public String message() {
      return new StringBuilder()
        .append("Value of field \"")
        .append(field)
        .append("\" must be at most ")
        .append(maxValue)
        .append(" but was ")
        .append(value)
        .toString();
    }

    public NumberValueTooHighException build() {
      return new NumberValueTooHighException(this);
    }
  }
}

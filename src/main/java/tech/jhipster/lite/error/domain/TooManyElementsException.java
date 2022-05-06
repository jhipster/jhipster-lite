package tech.jhipster.lite.error.domain;

public class TooManyElementsException extends AssertionException {

  public TooManyElementsException(CollectionTooLargeBuilder builder) {
    super(builder.message());
  }

  public static CollectionTooLargeBuilder builder() {
    return new CollectionTooLargeBuilder();
  }

  public static class CollectionTooLargeBuilder {

    private String field;
    private int maxSize;
    private int size;

    public CollectionTooLargeBuilder field(String field) {
      this.field = field;

      return this;
    }

    public CollectionTooLargeBuilder maxSize(int maxSize) {
      this.maxSize = maxSize;

      return this;
    }

    public CollectionTooLargeBuilder size(int size) {
      this.size = size;

      return this;
    }

    public String message() {
      return new StringBuilder()
        .append("Size of collection \"")
        .append(field)
        .append("\" must be at most ")
        .append(maxSize)
        .append(" but was ")
        .append(size)
        .toString();
    }

    public TooManyElementsException build() {
      return new TooManyElementsException(this);
    }
  }
}

package tech.jhipster.lite.error.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import tech.jhipster.lite.UnitTest;

@UnitTest
class AssertTest {

  public static final String NOT_NULL_OR_EMPTY = "NotNullOrEmpty";

  @Test
  void shouldNotValidateNullInputs() {
    assertThatThrownBy(() -> Assert.notNull("field", null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("\"field\"");
  }

  @Test
  void shouldValidateNonNull() {
    assertThatCode(() -> Assert.notNull("field", NOT_NULL_OR_EMPTY)).doesNotThrowAnyException();
  }

  @Test
  void shouldNotValidateNullString() {
    assertThatThrownBy(() -> Assert.notBlank("field", null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("\"field\"")
      .hasMessageContaining("(null)");
  }

  @Test
  void shouldNotValidateEmptyString() {
    assertNotBlankString("");
  }

  @Test
  void shouldNotValidateSpaceString() {
    assertNotBlankString(" ");
  }

  @Test
  void shouldNotValidateTabString() {
    assertNotBlankString("\t");
  }

  @Test
  void shouldValidateNonBlank() {
    assertThatCode(() -> Assert.notBlank("field", NOT_NULL_OR_EMPTY)).doesNotThrowAnyException();
  }

  private void assertNotBlankString(String input) {
    assertThatThrownBy(() -> Assert.notBlank("field", input))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("\"field\"")
      .hasMessageContaining("(blank)");
  }

  @Test
  void shouldNotValidateNegative() {
    assertThatThrownBy(() -> Assert.notNegative("field", -1))
      .isExactlyInstanceOf(UnauthorizedValueException.class)
      .hasMessageContaining("(negative)");
  }

  @Test
  void shouldValidatePositive() {
    assertThatCode(() -> Assert.notNegative("field", 11)).doesNotThrowAnyException();
  }

  @Test
  void shouldNotValidateValueGreaterThanCeil() {
    assertThatThrownBy(() -> Assert.notGreaterThan("field", 2, 1))
      .isExactlyInstanceOf(UnauthorizedValueException.class)
      .hasMessageContaining("inconsistent");
  }

  @Test
  void shouldValidateValueGreaterThan() {
    assertThatCode(() -> Assert.notGreaterThan("field", 1, 2)).doesNotThrowAnyException();
  }

  @Test
  void shouldNotValidateValueLowerThanFloor() {
    assertThatThrownBy(() -> Assert.notLowerThan("field", 0, 1))
      .isExactlyInstanceOf(UnauthorizedValueException.class)
      .hasMessageContaining("inconsistent");
  }

  @Test
  void shouldValidateValueLowerThanFloor() {
    assertThatCode(() -> Assert.notLowerThan("field", 0, 0)).doesNotThrowAnyException();
  }

  @Test
  void shouldNotValidateValueNotEquals() {
    assertThatThrownBy(() -> Assert.areEqual("field1", "field2", 1, 2))
      .isExactlyInstanceOf(UnauthorizedValueException.class)
      .hasMessageContaining("not equal");
  }

  @Test
  void shouldValidateValueEquals() {
    assertThatCode(() -> Assert.areEqual("field1", "field2", 1, 1)).doesNotThrowAnyException();
  }

  @Test
  void shouldNotValidateEmptyCollection() {
    List<String> list = List.of();
    assertThatThrownBy(() -> Assert.notEmpty("field", list))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("empty");
  }

  @Test
  void shouldValidateNotEmptyCollection() {
    assertThatCode(() -> Assert.notEmpty("field", List.of("Hello"))).doesNotThrowAnyException();
  }

  @Test
  void shouldNotValidateTooShortString() {
    assertThatThrownBy(() -> Assert.hasMinimumLength("field", 10, "dix"))
      .isExactlyInstanceOf(UnauthorizedValueException.class)
      .hasMessageContaining("too short");
  }

  @Test
  void shouldValidateHasMinimumLength() {
    assertThatCode(() -> Assert.hasMinimumLength("field", 4, "four")).doesNotThrowAnyException();
    assertThatCode(() -> Assert.hasMinimumLength("field", 3, "three")).doesNotThrowAnyException();
  }

  @Test
  void shouldNotValidateIsEqualTo() {
    assertThatThrownBy(() -> Assert.isEqualTo("field", 3, 4))
      .isExactlyInstanceOf(UnauthorizedValueException.class)
      .hasMessageContaining("field")
      .hasMessageContaining("expected");
  }

  @Test
  void shouldValidateIsEqualTo() {
    assertThatCode(() -> Assert.isEqualTo("field", "radish", "radish")).doesNotThrowAnyException();
  }

  @Nested
  @DisplayName("String")
  class AssertStringTest {

    @Test
    void shouldNotValidateNullStringAsNotBlank() {
      assertThatThrownBy(() -> Assert.field("fieldName", (String) null).notBlank())
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("(null)");
    }

    @Test
    void shouldNotValidateBlankStringAsNotBlank() {
      assertThatThrownBy(() -> Assert.field("fieldName", " ").notBlank())
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("(blank)");
    }

    @Test
    void shouldValidateStringWithValueAsNotBlank() {
      assertThatCode(() -> Assert.field("fieldName", "value").notBlank()).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateTooShortStringValue() {
      assertThatThrownBy(() -> Assert.field("fieldName", "value").minLength(6))
        .isExactlyInstanceOf(StringTooShortException.class)
        .hasMessageContaining(String.valueOf(6))
        .hasMessageContaining(String.valueOf("value".length()))
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateNullStringWithLength() {
      assertThatThrownBy(() -> Assert.field("fieldName", (String) null).minLength(1))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    void shouldValidateZeroOrNegativeMinLengthWithStringValue(int minLength) {
      assertThatCode(() -> Assert.field("fieldName", "value").minLength(minLength)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0 })
    void shouldValidateZeroOrNegativeMinLengthForNullInput(int minLength) {
      assertThatCode(() -> Assert.field("fieldName", (String) null).minLength(minLength)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = { 4, 5 })
    void shouldValidateLongEnoughString(int minLength) {
      assertThatCode(() -> Assert.field("fieldName", "value").minLength(minLength)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateTooLongStringValue() {
      assertThatThrownBy(() -> Assert.field("fieldName", "value").maxLength(4))
        .isExactlyInstanceOf(StringTooLongException.class)
        .hasMessageContaining(String.valueOf(4))
        .hasMessageContaining(String.valueOf("value".length()))
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldValidateNullInputUnderMaxLength() {
      assertThatCode(() -> Assert.field("fieldName", (String) null).maxLength(5)).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = { 5, 6 })
    void shouldValidateShortEnoughString(int maxLength) {
      assertThatCode(() -> Assert.field("fieldName", "value").maxLength(maxLength)).doesNotThrowAnyException();
    }
  }

  @Nested
  @DisplayName("Integer")
  class AssertIntegerTest {

    @Test
    void shouldNotValidateNullAsPositive() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Integer) null).positive())
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateNegativeValueAsPositive() {
      assertThatThrownBy(() -> Assert.field("fieldName", -4).positive())
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("0")
        .hasMessageContaining("-4");
    }

    @ParameterizedTest
    @ValueSource(ints = { 0, 42 })
    void shouldValidatePositiveValuesAsPositive(int value) {
      assertThatCode(() -> Assert.field("fieldName", value).positive()).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsOverMin() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Integer) null).min(0))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateValueUnderMin() {
      assertThatThrownBy(() -> Assert.field("fieldName", 42).min(1337))
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("42")
        .hasMessageContaining("1337");
    }

    @ParameterizedTest
    @ValueSource(ints = { 41, 42 })
    void shouldValidateValueOverMin(int min) {
      assertThatCode(() -> Assert.field("fieldName", 42).min(min)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsUnderMax() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Integer) null).max(42))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateValueOverMax() {
      assertThatThrownBy(() -> Assert.field("fieldName", 42).max(12))
        .isExactlyInstanceOf(NumberValueTooHighException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("12")
        .hasMessageContaining("42");
    }

    @ParameterizedTest
    @ValueSource(ints = { 42, 43 })
    void shouldValidateValueUnderMax(int max) {
      assertThatCode(() -> Assert.field("fieldName", 42).max(max)).doesNotThrowAnyException();
    }
  }

  @Nested
  @DisplayName("Long")
  class AssertLongTest {

    @Test
    void shouldNotValidateNullAsPositive() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Long) null).positive())
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateNegativeValueAsPositive() {
      assertThatThrownBy(() -> Assert.field("fieldName", -4L).positive())
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("0")
        .hasMessageContaining("-4");
    }

    @ParameterizedTest
    @ValueSource(longs = { 0, 42 })
    void shouldValidatePositiveValuesAsPositive(long value) {
      assertThatCode(() -> Assert.field("fieldName", value).positive()).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsOverMin() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Long) null).min(0))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateValueUnderMin() {
      assertThatThrownBy(() -> Assert.field("fieldName", 42L).min(1337))
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("42")
        .hasMessageContaining("1337");
    }

    @ParameterizedTest
    @ValueSource(longs = { 41, 42 })
    void shouldValidateValueOverMin(long min) {
      assertThatCode(() -> Assert.field("fieldName", 42L).min(min)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsUnderMax() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Long) null).max(42L))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateValueOverMax() {
      assertThatThrownBy(() -> Assert.field("fieldName", 42L).max(12))
        .isExactlyInstanceOf(NumberValueTooHighException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("12")
        .hasMessageContaining("42");
    }

    @ParameterizedTest
    @ValueSource(longs = { 42, 43 })
    void shouldValidateValueUnderMax(long max) {
      assertThatCode(() -> Assert.field("fieldName", 42L).max(max)).doesNotThrowAnyException();
    }
  }

  @Nested
  @DisplayName("Float")
  class AssertFloatTest {

    @Test
    void shouldNotValidateNullAsPositive() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Float) null).positive())
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateNegativeValueAsPositive() {
      assertThatThrownBy(() -> Assert.field("fieldName", -4F).positive())
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("0")
        .hasMessageContaining("-4");
    }

    @ParameterizedTest
    @ValueSource(floats = { 0, 42 })
    void shouldValidatePositiveValuesAsPositive(float value) {
      assertThatCode(() -> Assert.field("fieldName", value).positive()).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(floats = { -0.1F, 0 })
    void shouldNotValidateNegativeAndZeroValueAsStricltyPositive(float value) {
      assertThatThrownBy(() -> Assert.field("fieldName", value).strictlyPositive())
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("0")
        .hasMessageContaining(String.valueOf(value));
    }

    @ParameterizedTest
    @ValueSource(floats = { 0.1F, 1 })
    void shouldValidatePositiveValueAsStricltyPositive(float value) {
      assertThatCode(() -> Assert.field("fieldName", value).strictlyPositive()).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsOverMin() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Float) null).min(0))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateValueUnderMin() {
      assertThatThrownBy(() -> Assert.field("fieldName", 42F).min(1337))
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("42")
        .hasMessageContaining("1337");
    }

    @ParameterizedTest
    @ValueSource(floats = { 41, 42 })
    void shouldValidateValueOverMin(float min) {
      assertThatCode(() -> Assert.field("fieldName", 42F).min(min)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsOverFloor() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Float) null).over(42))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @ParameterizedTest
    @ValueSource(floats = { 41.9F, 42 })
    void shouldNotValidateValueUnderFloor(float value) {
      assertThatThrownBy(() -> Assert.field("fieldName", value).over(42))
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining(String.valueOf(value))
        .hasMessageContaining("42");
    }

    @ParameterizedTest
    @ValueSource(floats = { 42.1F, 43 })
    void shouldValidateValueOverFloor(float value) {
      assertThatCode(() -> Assert.field("fieldName", value).over(42)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsUnderMax() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Float) null).max(42))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateValueOverMax() {
      assertThatThrownBy(() -> Assert.field("fieldName", 42F).max(12))
        .isExactlyInstanceOf(NumberValueTooHighException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("12")
        .hasMessageContaining("42");
    }

    @ParameterizedTest
    @ValueSource(floats = { 42, 43 })
    void shouldValidateValueUnderMax(float max) {
      assertThatCode(() -> Assert.field("fieldName", 42F).max(max)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsUnderCeil() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Float) null).under(42))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @ParameterizedTest
    @ValueSource(floats = { 42, 42.5F })
    void shouldNotValidateValueOverCeil(float value) {
      assertThatThrownBy(() -> Assert.field("fieldName", value).under(42))
        .isExactlyInstanceOf(NumberValueTooHighException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining(String.valueOf(value))
        .hasMessageContaining("42");
    }

    @ParameterizedTest
    @ValueSource(floats = { 41, 41.9F })
    void shouldValidateValueUnderCeil(float value) {
      assertThatCode(() -> Assert.field("fieldName", value).under(42)).doesNotThrowAnyException();
    }
  }

  @Nested
  @DisplayName("Double")
  class AssertDoubleTest {

    @Test
    void shouldNotValidateNullAsPositive() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Double) null).positive())
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateNegativeValueAsPositive() {
      assertThatThrownBy(() -> Assert.field("fieldName", -4D).positive())
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("0")
        .hasMessageContaining("-4");
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0, 42 })
    void shouldValidatePositiveValuesAsPositive(double value) {
      assertThatCode(() -> Assert.field("fieldName", value).positive()).doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(doubles = { -0.1F, 0 })
    void shouldNotValidateNegativeAndZeroValueAsStricltyPositive(double value) {
      assertThatThrownBy(() -> Assert.field("fieldName", value).strictlyPositive())
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("0")
        .hasMessageContaining(String.valueOf(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0.1F, 1 })
    void shouldValidatePositiveValueAsStricltyPositive(double value) {
      assertThatCode(() -> Assert.field("fieldName", value).strictlyPositive()).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsOverMin() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Double) null).min(0))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateValueUnderMin() {
      assertThatThrownBy(() -> Assert.field("fieldName", 42D).min(1337))
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("42")
        .hasMessageContaining("1337");
    }

    @ParameterizedTest
    @ValueSource(doubles = { 41, 42 })
    void shouldValidateValueOverMin(double min) {
      assertThatCode(() -> Assert.field("fieldName", 42D).min(min)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsOverFloor() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Double) null).over(42))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @ParameterizedTest
    @ValueSource(doubles = { 41.9D, 42 })
    void shouldNotValidateValueUnderFloor(double value) {
      assertThatThrownBy(() -> Assert.field("fieldName", value).over(42))
        .isExactlyInstanceOf(NumberValueTooLowException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining(String.valueOf(value))
        .hasMessageContaining("42");
    }

    @ParameterizedTest
    @ValueSource(doubles = { 42.1D, 43 })
    void shouldValidateValueOverFloor(double value) {
      assertThatCode(() -> Assert.field("fieldName", value).over(42)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsUnderMax() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Double) null).max(42))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateValueOverMax() {
      assertThatThrownBy(() -> Assert.field("fieldName", 42D).max(12))
        .isExactlyInstanceOf(NumberValueTooHighException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("12")
        .hasMessageContaining("42");
    }

    @ParameterizedTest
    @ValueSource(doubles = { 42, 43 })
    void shouldValidateValueUnderMax(double max) {
      assertThatCode(() -> Assert.field("fieldName", 42D).max(max)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullAsUnderCeil() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Double) null).under(42))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @ParameterizedTest
    @ValueSource(doubles = { 42, 42.5F })
    void shouldNotValidateValueOverCeil(double value) {
      assertThatThrownBy(() -> Assert.field("fieldName", value).under(42))
        .isExactlyInstanceOf(NumberValueTooHighException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining(String.valueOf(value))
        .hasMessageContaining("42");
    }

    @ParameterizedTest
    @ValueSource(doubles = { 41, 41.9F })
    void shouldValidateValueUnderCeil(double value) {
      assertThatCode(() -> Assert.field("fieldName", value).under(42)).doesNotThrowAnyException();
    }
  }

  @Nested
  @DisplayName("Instant")
  class AssertInstantTest {

    @Test
    void shouldNotValidateNullAsNotNull() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Instant) null).notNull())
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldValidateActualInstantAsNotNull() {
      assertThatCode(() -> Assert.field("fieldName", Instant.now()).notNull()).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullInstantAsPast() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Instant) null).inPast())
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateFutureInstantAsPast() {
      assertThatThrownBy(() -> Assert.field("fieldName", future()).inPast())
        .isExactlyInstanceOf(NotPastTimeException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldValidatePastDateAsPast() {
      assertThatCode(() -> Assert.field("fieldName", past()).inPast()).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateNullInstantAsFuture() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Instant) null).inFuture())
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidatePastInstantAsFuture() {
      assertThatThrownBy(() -> Assert.field("fieldName", past()).inFuture())
        .isExactlyInstanceOf(NotFutureTimeException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldValidateFutureDateAsFuture() {
      assertThatCode(() -> Assert.field("fieldName", future()).inFuture()).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateInstantAfterNullInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", past()).after(null))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("other");
    }

    @Test
    void shouldNotValidateNullInstantAfterInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Instant) null).after(past()))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateSameInstantAsAfterInstant() {
      Instant date = past();

      assertThatThrownBy(() -> Assert.field("fieldName", date).after(date))
        .isExactlyInstanceOf(NotAfterTimeException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("strictly");
    }

    @Test
    void shouldNotValidatePastInstantAsAfterFutureInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", past()).after(future()))
        .isExactlyInstanceOf(NotAfterTimeException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("strictly");
    }

    @Test
    void shouldValidateFutureInstantAsAfterPastInstant() {
      assertThatCode(() -> Assert.field("fieldName", future()).after(past())).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateInstantAfterOrAtNullInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", past()).afterOrAt(null))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("other");
    }

    @Test
    void shouldNotValidateNullInstantAfterOrAtInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Instant) null).afterOrAt(past()))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidatePastInstantAsAfterOrAtFutureInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", past()).afterOrAt(future()))
        .isExactlyInstanceOf(NotAfterTimeException.class)
        .hasMessageContaining("fieldName")
        .hasMessageNotContaining("strictly");
    }

    @Test
    void shouldValidateFutureInstantAsAfterOrAtPastInstant() {
      assertThatCode(() -> Assert.field("fieldName", future()).afterOrAt(past())).doesNotThrowAnyException();
    }

    @Test
    void shouldValidateSameInstantAsAfterOrAtInstant() {
      Instant date = past();

      assertThatCode(() -> Assert.field("fieldName", date).afterOrAt(date)).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateInstantBeforeNullInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", past()).before(null))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("other");
    }

    @Test
    void shouldNotValidateNullInstantBeforeInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Instant) null).before(past()))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateSameInstantAsBeforeInstant() {
      Instant date = past();

      assertThatThrownBy(() -> Assert.field("fieldName", date).before(date))
        .isExactlyInstanceOf(NotBeforeTimeException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("strictly");
    }

    @Test
    void shouldNotValidateFutureInstantAsBeforePastInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", future()).before(past()))
        .isExactlyInstanceOf(NotBeforeTimeException.class)
        .hasMessageContaining("fieldName")
        .hasMessageContaining("strictly");
    }

    @Test
    void shouldValidatePastInstantAsBeforeFutureInstant() {
      assertThatCode(() -> Assert.field("fieldName", past()).before(future())).doesNotThrowAnyException();
    }

    @Test
    void shouldNotValidateInstantBeforeOrAtNullInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", past()).beforeOrAt(null))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("other");
    }

    @Test
    void shouldNotValidateNullInstantBeforeOrAtInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", (Instant) null).beforeOrAt(past()))
        .isExactlyInstanceOf(MissingMandatoryValueException.class)
        .hasMessageContaining("fieldName");
    }

    @Test
    void shouldNotValidateFutureInstantAsBeforeOrAtPastInstant() {
      assertThatThrownBy(() -> Assert.field("fieldName", future()).beforeOrAt(past()))
        .isExactlyInstanceOf(NotBeforeTimeException.class)
        .hasMessageContaining("fieldName")
        .hasMessageNotContaining("strictly");
    }

    @Test
    void shouldValidatePastInstantAsAfterOrAtFutureInstant() {
      assertThatCode(() -> Assert.field("fieldName", past()).beforeOrAt(future())).doesNotThrowAnyException();
    }

    @Test
    void shouldValidateSameInstantAsBeforeOrAtInstant() {
      Instant date = past();

      assertThatCode(() -> Assert.field("fieldName", date).beforeOrAt(date)).doesNotThrowAnyException();
    }

    private Instant past() {
      return Instant.now().minusSeconds(10);
    }

    private Instant future() {
      return Instant.now().plusSeconds(10);
    }
  }
}

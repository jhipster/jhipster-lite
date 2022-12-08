package tech.jhipster.lite.error.infrastructure.primary;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tech.jhipster.lite.common.domain.Enums;
import tech.jhipster.lite.error.domain.ErrorKey;
import tech.jhipster.lite.error.domain.GeneratorException;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE - 1000)
class GeneratorErrorsHandler {

  private static final String MESSAGES_PREFIX = "error.";

  private final MessageSource messages;

  public GeneratorErrorsHandler(@Qualifier("generatorErrorMessageSource") MessageSource messages) {
    Locale.setDefault(Locale.ENGLISH);

    this.messages = messages;
  }

  @ExceptionHandler(GeneratorException.class)
  ProblemDetail handelGeneratorException(GeneratorException exception) {
    ProblemDetail problem = ProblemDetail.forStatusAndDetail(Enums.map(exception.status(), HttpStatus.class), buildDetail(exception));

    problem.setTitle(getMessage(exception.key(), "title"));
    problem.setProperty("key", exception.key().get());

    return problem;
  }

  private String buildDetail(GeneratorException exception) {
    String messageTemplate = getMessage(exception.key(), "detail");

    return ArgumentsReplacer.replaceParameters(messageTemplate, exception.parameters());
  }

  private String getMessage(ErrorKey key, String suffix) {
    return messages.getMessage(MESSAGES_PREFIX + key.get() + "." + suffix, null, locale());
  }

  private Locale locale() {
    return LocaleContextHolder.getLocale();
  }
}

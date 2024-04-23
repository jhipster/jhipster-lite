package tech.jhipster.lite.module.infrastructure.secondary.file;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;
import org.springframework.stereotype.Component;
import tech.jhipster.lite.module.domain.file.TemplateRenderer;
import tech.jhipster.lite.shared.error.domain.GeneratorException;
import tech.jhipster.lite.shared.generation.domain.ExcludeFromGeneratedCodeCoverage;

@Component
public final class MustacheTemplateRenderer implements TemplateRenderer {

  private final MustacheFactory mustacheFactory = new DefaultMustacheFactory();

  @Override
  @ExcludeFromGeneratedCodeCoverage(reason = "IOException is hard to test")
  public String render(String message, Map<String, ?> context) {
    if (message == null || context == null) {
      return message;
    }

    Mustache mustache = mustacheFactory.compile(new StringReader(message), "template");
    try {
      StringWriter stringWriter = new StringWriter();
      mustache.execute(stringWriter, context).flush();
      return stringWriter.toString();
    } catch (IOException e) {
      throw GeneratorException.technicalError("Can't render template: " + e.getMessage(), e);
    }
  }
}

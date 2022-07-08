package tech.jhipster.lite.technical.infrastructure.primary.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JacksonProjectFolderDeserializer extends JsonDeserializer<String> {

  @Value("${application.forced-project-folder:}")
  private String forcedProjectFolder;

  @Override
  public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    String text = jsonParser.getText();
    if (forcedProjectFolder != null && (!text.startsWith(forcedProjectFolder) || text.contains(".."))) {
      throw new InvalidProjectFolderException(forcedProjectFolder);
    }
    return text;
  }
}

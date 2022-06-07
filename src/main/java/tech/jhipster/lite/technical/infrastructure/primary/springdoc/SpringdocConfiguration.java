package tech.jhipster.lite.technical.infrastructure.primary.springdoc;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tech.jhipster.lite.generator.module.infrastructure.primary.JHipsterModuleApiDoc;
import tech.jhipster.lite.generator.module.infrastructure.primary.JHipsterModuleResource;

@Configuration
public class SpringdocConfiguration {

  private static final Schema<?> PROJECT_DTO_SCHEMA = new Schema<>().$ref("#/components/schemas/ProjectDTO");

  private static final String JSON_MEDIA_TYPE = "application/json";

  @Value("${application.version:undefined}")
  private String version;

  @Bean
  public OpenAPI jhLiteOpenAPI(Collection<JHipsterModuleResource> modules) {
    OpenAPI openApi = new OpenAPI()
      .info(
        new Info()
          .title("JHipster Lite API")
          .description("JHipster Lite API")
          .version(version)
          .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
      )
      .externalDocs(new ExternalDocumentation().description("JHipster Lite Documentation").url("https://jhipster.tech/lite"));

    openApi.setPaths(buildJHipsterModulesPaths(modules));

    return openApi;
  }

  private Paths buildJHipsterModulesPaths(Collection<JHipsterModuleResource> modules) {
    Paths paths = new Paths();

    paths.putAll(modules.stream().collect(Collectors.toMap(JHipsterModuleResource::legacyUrl, module -> moduleApiDoc(module.apiDoc()))));

    return paths;
  }

  private PathItem moduleApiDoc(JHipsterModuleApiDoc apiDoc) {
    RequestBody requestBody = new RequestBody()
      .required(true)
      .content(new Content().addMediaType(JSON_MEDIA_TYPE, new MediaType().schema(PROJECT_DTO_SCHEMA)));

    Operation postOperation = new Operation().summary(apiDoc.operation()).tags(List.of(apiDoc.tag())).requestBody(requestBody);

    return new PathItem().post(postOperation);
  }

  @Bean
  public GroupedOpenApi jhLiteAllOpenAPI() {
    return GroupedOpenApi.builder().group("all").pathsToMatch("/api/**").build();
  }
}

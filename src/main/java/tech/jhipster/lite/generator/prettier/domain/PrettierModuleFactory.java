package tech.jhipster.lite.generator.prettier.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.*;
import static tech.jhipster.lite.module.domain.packagejson.VersionSource.COMMON;

import java.util.regex.Pattern;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.file.JHipsterDestination;
import tech.jhipster.lite.module.domain.file.JHipsterSource;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;
import tech.jhipster.lite.module.domain.replacement.ElementReplacer;
import tech.jhipster.lite.module.domain.replacement.RegexReplacer;

public class PrettierModuleFactory {

  private static final JHipsterSource SOURCE = from("prettier");
  private static final JHipsterDestination DESTINATION = to(".");

  private static final Pattern MODULE_EXPORT = Pattern.compile("module.exports = \\{");
  private static final Pattern DEFAULT_ES_LINT = Pattern.compile("'\\*': \\[], //default configuration, replace with your own");

  private static final ElementReplacer EXISTING_ESLINT_CONFIGURATION = new RegexReplacer(
    (contentBeforeReplacement, replacement) -> MODULE_EXPORT.matcher(contentBeforeReplacement).find(),
    MODULE_EXPORT
  );

  private static final ElementReplacer DEFAULT_ES_LINT_CONFIGURATION = new RegexReplacer(
    (contentBeforeReplacement, replacement) -> DEFAULT_ES_LINT.matcher(contentBeforeReplacement).find(),
    DEFAULT_ES_LINT
  );

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    String esLintReplacement =
      "module.exports = \\{" +
      LINE_BREAK +
      properties.indentation().times(1) +
      "'*.{md,json,yml,html,css,scss,java,xml,feature}': ['prettier --write'],";
    //@formatter:off
    return moduleBuilder(properties)
      .context()
        .put("dasherizedBaseName", properties.projectBaseName().kebabCase())
        .put("endOfLine", endOfLine(properties))
        .and()
      .files()
        .batch(SOURCE, DESTINATION)
          .addFile(".prettierignore")
          .addTemplate(".prettierrc")
          .and()
        .and()
      .packageJson()
        .addDevDependency(packageName("@prettier/plugin-xml"), COMMON)
        .addDevDependency(packageName("prettier"), COMMON)
        .addDevDependency(packageName("prettier-plugin-gherkin"), COMMON)
        .addDevDependency(packageName("prettier-plugin-java"), COMMON)
        .addDevDependency(packageName("prettier-plugin-packagejson"), COMMON)
        .addScript(scriptKey("prettier:check"), scriptCommand("prettier --check ."))
        .addScript(scriptKey("prettier:format"), scriptCommand("prettier --write ."))
        .and()
      .optionalReplacements()
        .in(path(DESTINATION + "lintstagedrc.cjs"))
          .add(DEFAULT_ES_LINT_CONFIGURATION, "")
          .add(EXISTING_ESLINT_CONFIGURATION, esLintReplacement)
      .and()
      .and()
      .build();
    //@formatter:on
  }

  private String endOfLine(JHipsterModuleProperties properties) {
    return properties.getOrDefaultString("endOfLine", "lf");
  }
}

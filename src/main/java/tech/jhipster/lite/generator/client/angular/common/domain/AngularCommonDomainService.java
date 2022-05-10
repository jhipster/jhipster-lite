package tech.jhipster.lite.generator.client.angular.common.domain;

import static tech.jhipster.lite.common.domain.WordUtils.indent;
import static tech.jhipster.lite.generator.client.angular.common.domain.AngularCommon.COMA;
import static tech.jhipster.lite.generator.client.angular.common.domain.AngularCommon.DECLARATIONS_REGEX;
import static tech.jhipster.lite.generator.client.angular.common.domain.AngularCommon.DECLARATIONS_WITH_ARRAY_VALUES_REGEX_LIST;
import static tech.jhipster.lite.generator.client.angular.common.domain.AngularCommon.DECORATOR_REGEX_LIST;
import static tech.jhipster.lite.generator.client.angular.common.domain.AngularCommon.ENV_VARIABLES_WITH_VALUES_REGEX_LIST;
import static tech.jhipster.lite.generator.client.angular.common.domain.AngularCommon.OPENING_BRACKET;
import static tech.jhipster.lite.generator.client.angular.common.domain.AngularCommon.PROVIDERS_WITH_ARRAY_VALUES_REGEX_LIST;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import tech.jhipster.lite.common.domain.FileUtils;
import tech.jhipster.lite.error.domain.GeneratorException;
import tech.jhipster.lite.generator.project.domain.Project;

public class AngularCommonDomainService implements AngularCommonService {

  @Override
  public void addImports(Project project, String filePath, String imports) {
    String fullFilePath = FileUtils.getPath(project.getFolder(), filePath);
    String fileContent = getFileContent(fullFilePath);

    String newFileContent = getLastImportInFile(fileContent)
      .map(lastImport -> fileContent.replace(lastImport, lastImport + project.getEndOfLine() + imports))
      .orElse(imports + project.getEndOfLine() + fileContent);

    writeInFile(fullFilePath, newFileContent, project);
  }

  @Override
  public void addConstants(Project project, String filePath, String constants) {
    String fullFilePath = FileUtils.getPath(project.getFolder(), filePath);
    String fileContent = getFileContent(fullFilePath);
    String newFileContent = getFirstMatchInFile(DECORATOR_REGEX_LIST, fileContent)
      .map(decoratorPrefix -> fileContent.replace(decoratorPrefix, constants + project.getEndOfLine() + decoratorPrefix))
      .orElse(fileContent + project.getEndOfLine() + constants);

    writeInFile(fullFilePath, newFileContent, project);
  }

  @Override
  public void addDeclarations(Project project, String filePath, String declarations) {
    String fullFilePath = FileUtils.getPath(project.getFolder(), filePath);
    String fileContent = getFileContent(fullFilePath);
    String newFileContent = getFirstMatchInFile(DECLARATIONS_WITH_ARRAY_VALUES_REGEX_LIST, fileContent)
      .map(declarationsPrefix -> appendValuesInArray(fileContent, declarationsPrefix, declarations, project.getEndOfLine()))
      .orElseThrow(() -> new GeneratorException("Cannot find declarations array in file " + fullFilePath));

    writeInFile(fullFilePath, newFileContent, project);
  }

  @Override
  public void addProviders(Project project, String filePath, String providers) {
    String fullFilePath = FileUtils.getPath(project.getFolder(), filePath);
    String fileContent = getFileContent(fullFilePath);

    String newFileContent = getFirstMatchInFile(PROVIDERS_WITH_ARRAY_VALUES_REGEX_LIST, fileContent)
      .map(providersPrefix -> appendValuesInArray(fileContent, providersPrefix, providers, project.getEndOfLine()))
      .orElseGet(() -> appendProvidersAfterDeclarations(fileContent, fullFilePath, providers, project));

    writeInFile(fullFilePath, newFileContent, project);
  }

  @Override
  public void addEnvVariables(Project project, String envFilePath, String values) {
    String fullFilePath = FileUtils.getPath(project.getFolder(), envFilePath);
    String fileContent = getFileContent(fullFilePath);
    String newFileContent = getFirstMatchInFile(ENV_VARIABLES_WITH_VALUES_REGEX_LIST, fileContent)
      .map(prefixEnvVariables -> {
        String updatedEnvVariablesStr = prefixEnvVariables.stripTrailing();
        if (!prefixEnvVariables.endsWith(COMA)) {
          updatedEnvVariablesStr += COMA;
        }
        updatedEnvVariablesStr += project.getEndOfLine() + values.stripTrailing() + project.getEndOfLine();
        return fileContent.replace(prefixEnvVariables, updatedEnvVariablesStr);
      })
      .orElseThrow(() -> new GeneratorException("Cannot find environnement const in " + fullFilePath));
    writeInFile(fullFilePath, newFileContent, project);
  }

  @Override
  public void prependHtml(Project project, String htmlFilePath, String html, String inHtmlTagRegex) {
    String fullFilePath = FileUtils.getPath(project.getFolder(), htmlFilePath);
    String fileContent = getFileContent(fullFilePath);
    Pattern pattern = Pattern.compile(inHtmlTagRegex, Pattern.MULTILINE);
    Matcher matcher = pattern.matcher(fileContent);
    if (!matcher.find()) {
      throw new GeneratorException("Cannot find " + inHtmlTagRegex + " in file " + fullFilePath);
    }
    String foundHtmlTag = matcher.group(1);
    String newFileContent = fileContent.replace(foundHtmlTag, foundHtmlTag.stripTrailing() + project.getEndOfLine() + html);
    writeInFile(fullFilePath, newFileContent, project);
  }

  private String getFileContent(String fullFilePath) {
    String fileContent;
    try {
      fileContent = FileUtils.read(fullFilePath);
    } catch (IOException e) {
      throw new GeneratorException("Cannot read file " + fullFilePath + ": " + e.getMessage());
    }
    return fileContent;
  }

  private void writeInFile(String fullFilePath, String newFileContent, Project project) {
    try {
      FileUtils.write(fullFilePath, newFileContent, project.getEndOfLine());
    } catch (IOException e) {
      throw new GeneratorException("Cannot write content in file " + fullFilePath + ": " + e.getMessage());
    }
  }

  private Optional<String> getLastImportInFile(String fileContent) {
    Pattern pattern = Pattern.compile(AngularCommon.IMPORT_PATTERN, Pattern.MULTILINE);
    Matcher matcher = pattern.matcher(fileContent);
    String lastMatchFound = null;
    while (matcher.find()) {
      lastMatchFound = matcher.group(1);
    }
    return Optional.ofNullable(lastMatchFound);
  }

  private Optional<String> getFirstMatchInFile(List<String> regexList, String fileContent) {
    return regexList
      .stream()
      .map(regex -> {
        Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        return pattern.matcher(fileContent);
      })
      .filter(Matcher::find)
      .findFirst()
      .map(matcher -> matcher.group(1));
  }

  private String appendValuesInArray(String fileContent, String prefixWithArrayValuesStr, String arrayValuesToAdd, String endOfLine) {
    StringBuilder updatedArrayValuesStr = new StringBuilder(prefixWithArrayValuesStr.stripTrailing());
    if (!prefixWithArrayValuesStr.trim().endsWith(OPENING_BRACKET) && !updatedArrayValuesStr.toString().endsWith(COMA)) {
      updatedArrayValuesStr.append(COMA);
    }
    updatedArrayValuesStr.append(endOfLine).append(arrayValuesToAdd.stripTrailing()).append(endOfLine).append(indent(1));
    return fileContent.replace(prefixWithArrayValuesStr, updatedArrayValuesStr.toString());
  }

  private String appendProvidersAfterDeclarations(String fileContent, String fullFilePath, String providers, Project project) {
    String declarationsStr = getFirstMatchInFile(List.of(DECLARATIONS_REGEX), fileContent)
      .orElseThrow(() -> new GeneratorException("Missing declarations in file: " + fullFilePath));

    String endComma = !providers.trim().endsWith(COMA) ? COMA : "";
    String newProvidersArrayStr =
      indent(1) + "providers: [" + project.getEndOfLine() + providers.stripTrailing() + endComma + project.getEndOfLine() + indent(1) + "]";
    String declarationsAndProvidersStr = declarationsStr.trim().endsWith(COMA) ? declarationsStr : declarationsStr + COMA;
    declarationsAndProvidersStr += project.getEndOfLine() + newProvidersArrayStr;
    return fileContent.replace(declarationsStr, declarationsAndProvidersStr);
  }
}

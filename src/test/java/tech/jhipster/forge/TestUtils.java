package tech.jhipster.forge;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.jhipster.forge.common.utils.FileUtils.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import tech.jhipster.forge.common.domain.Project;
import tech.jhipster.forge.common.utils.FileUtils;

public class TestUtils {

  private static final Logger log = LoggerFactory.getLogger(TestUtils.class);

  public static void assertFileExist(Project project, String... paths) {
    assertFileExist(getPath(project.getPath(), getPath(paths)));
  }

  public static void assertFileExist(String... paths) {
    assertTrue(exists(getPath(paths)), "The file '" + getPath(paths) + "' does not " + "exist.");
  }

  public static void assertFileNotExist(Project project, String... paths) {
    assertFileNotExist(getPath(project.getPath(), getPath(paths)));
  }

  public static void assertFileNotExist(String... paths) {
    assertFalse(exists(getPath(paths)), "The file '" + getPath(paths) + "' should not exist.");
  }

  public static void assertFileContent(Project project, String filename, String value) {
    assertTrue(FileUtils.containsInLine(getPath(project.getPath(), filename), value), "The value '" + value + "' was not found");
  }

  public static void assertFileContent(String path, String filename, String value) {
    assertTrue(FileUtils.containsInLine(getPath(path, filename), value), "The value '" + value + "' was not found");
  }

  public static void assertFileNoContent(Project project, String filename, String value) {
    assertFalse(FileUtils.containsInLine(getPath(project.getPath(), filename), value), "The value '" + value + "' was found");
  }

  public static void assertFileContent(Project project, String filename, List<String> lines) {
    assertTrue(FileUtils.containsLines(getPath(project.getPath(), filename), lines), "The lines '" + lines + "' were not found");
  }

  public static void assertFileNoContent(Project project, String filename, List<String> lines) {
    assertFalse(FileUtils.containsLines(getPath(project.getPath(), filename), lines), "The lines '" + lines + "' were found");
  }

  public static Project.ProjectBuilder tmpProjectBuilder() {
    return Project.builder().path(FileUtils.tmpDirForTest());
  }

  public static Project tmpProject() {
    return tmpProjectBuilder().build();
  }

  public static Project tmpProjectWithPomXml() throws IOException {
    Project project = tmpProject();
    FileUtils.createFolder(project.getPath());
    Files.copy(getPathOf("src/test/resources/template/maven/pom.test.xml"), getPathOf(project.getPath(), "pom.xml"));
    return project;
  }

  private static final ObjectMapper mapper = createObjectMapper();

  private static ObjectMapper createObjectMapper() {
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS, false);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    mapper.registerModule(new JavaTimeModule());
    return mapper;
  }

  public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
    return mapper.writeValueAsBytes(object);
  }

  public static String asString(Resource resource) {
    try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
      return FileCopyUtils.copyToString(reader);
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  public static String readFileToString(String path) {
    ResourceLoader resourceLoader = new DefaultResourceLoader();
    Resource resource = resourceLoader.getResource(path);
    return asString(resource);
  }

  public static <T> T readFileToObject(String path, Class<T> valueType) {
    String stringObject = readFileToString(path);

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    try {
      return mapper.readValue(stringObject, valueType);
    } catch (JsonProcessingException e) {
      log.error("Can't readValue", e);
      return null;
    }
  }
}

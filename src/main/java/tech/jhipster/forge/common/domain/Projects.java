package tech.jhipster.forge.common.domain;

import java.io.IOException;

public interface Projects {
  void create(Project project);
  void add(Project project, String source, String file);
  void template(Project project, String source, String file) throws IOException;
}

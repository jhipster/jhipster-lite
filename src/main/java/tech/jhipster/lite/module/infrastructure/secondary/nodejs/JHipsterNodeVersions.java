package tech.jhipster.lite.module.infrastructure.secondary.nodejs;

import java.util.Collection;
import java.util.function.Supplier;
import org.springframework.stereotype.Repository;
import tech.jhipster.lite.module.domain.nodejs.NodePackagesVersions;
import tech.jhipster.lite.module.domain.nodejs.NodeVersions;
import tech.jhipster.lite.shared.memoizer.domain.Memoizers;

@Repository
class JHipsterNodeVersions implements NodeVersions {

  private final Supplier<NodePackagesVersions> versions;

  public JHipsterNodeVersions(Collection<NodePackagesVersionsReader> readers) {
    versions = Memoizers.of(versionsReader(readers));
  }

  private Supplier<NodePackagesVersions> versionsReader(Collection<NodePackagesVersionsReader> readers) {
    return () -> readers.stream().map(NodePackagesVersionsReader::get).reduce(NodePackagesVersions.EMPTY, NodePackagesVersions::merge);
  }

  @Override
  public NodePackagesVersions get() {
    return versions.get();
  }
}

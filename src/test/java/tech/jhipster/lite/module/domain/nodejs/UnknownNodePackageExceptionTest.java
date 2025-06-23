package tech.jhipster.lite.module.domain.nodejs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.shared.error.domain.ErrorStatus;

@UnitTest
class UnknownNodePackageExceptionTest {

  @Test
  void shouldGetExceptionInformation() {
    UnknownNodePackageException exception = new UnknownNodePackageException(
      new NodePackageName("package-name"),
      JHLiteNodePackagesVersionSource.ANGULAR.build()
    );

    assertThat(exception.getMessage()).isEqualTo("Can't find package-name version in angular package.json, forgot to add it?");
    assertThat(exception.key()).isEqualTo(NodeErrorKey.UNKNOWN_PACKAGE);
    assertThat(exception.status()).isEqualTo(ErrorStatus.INTERNAL_SERVER_ERROR);
    assertThat(exception.parameters()).containsOnly(entry("packageName", "package-name"), entry("packageSource", "angular"));
  }
}

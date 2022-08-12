package tech.jhipster.lite.module.domain.resource;

import static org.assertj.core.api.Assertions.*;
import static tech.jhipster.lite.module.domain.resource.JHipsterLandscapeFixture.*;
import static tech.jhipster.lite.module.domain.resource.JHipsterModulesResourceFixture.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import tech.jhipster.lite.UnitTest;
import tech.jhipster.lite.module.domain.JHipsterFeatureSlug;
import tech.jhipster.lite.module.domain.JHipsterModuleSlug;

@UnitTest
class JHipsterLandscapeTest {

  @Test
  void shouldNotBuildWithFeatureNameConflictingWithModuleName() {
    assertThatThrownBy(() -> JHipsterLandscape.from(moduleResources(testModule(), testFeature())))
      .isExactlyInstanceOf(InvalidLandscapeException.class)
      .hasMessageContaining("\"test\"");
  }

  private JHipsterModuleResource testFeature() {
    return defaultModuleResourceBuilder().feature("test").build();
  }

  private JHipsterModuleResource testModule() {
    return defaultModuleResourceBuilder().slug("test").build();
  }

  @Test
  void shouldNotBuildLandscapeWithoutRootElement() {
    assertThatThrownBy(() -> JHipsterLandscape.from(moduleResources(defaultModuleResourceBuilder().moduleDependency("unknown").build())))
      .isExactlyInstanceOf(InvalidLandscapeException.class)
      .hasMessageContaining("root element");
  }

  @Test
  void shouldNotBuildLandscapeWithUnknownDependency() {
    assertThatThrownBy(() ->
        JHipsterLandscape.from(
          moduleResources(defaultModuleResource(), defaultModuleResourceBuilder().slug("dummy").moduleDependency("unknown").build())
        )
      )
      .isExactlyInstanceOf(InvalidLandscapeException.class)
      .hasMessageContaining("unknown dependency");
  }

  @Test
  void shouldNotBuildLandscapeWithLoopingDependencies() {
    JHipsterModuleResource root = defaultModuleResource();
    JHipsterModuleResource first = defaultModuleResourceBuilder().slug("first").moduleDependency("second").build();
    JHipsterModuleResource second = defaultModuleResourceBuilder().slug("second").moduleDependency("first").build();

    assertThatThrownBy(() -> JHipsterLandscape.from(moduleResources(root, first, second)))
      .isExactlyInstanceOf(InvalidLandscapeException.class)
      .hasMessageContaining("unknown dependency");
  }

  @Test
  void shouldBuildOneLevelLandscapeFromOneModule() {
    JHipsterLandscape landscape = JHipsterLandscape.from(moduleResources(defaultModuleResource()));

    assertThat(landscape.levels().get())
      .usingRecursiveFieldByFieldElementComparator()
      .containsExactly(landscapeLevel(noDependencyLandscapeModule("slug")));
  }

  @Test
  void shouldBuildOneLevelLandscapeFromOneFeatureWithOneModule() {
    JHipsterLandscape landscape = JHipsterLandscape.from(moduleResources(defaultModuleResourceBuilder().feature("my-feature").build()));

    assertThat(landscape.levels().get())
      .usingRecursiveFieldByFieldElementComparator()
      .containsExactly(landscapeLevel(landscapeFeature("my-feature", noDependencyLandscapeModule("slug"))));
  }

  @Test
  void shouldBuildOneLevelLandscapeFromOneFeatureWithTwoModules() {
    JHipsterModuleResource firstModule = defaultModuleResourceBuilder().slug("first").feature("my-feature").build();
    JHipsterModuleResource secondModule = defaultModuleResourceBuilder().slug("second").feature("my-feature").build();

    JHipsterLandscape landscape = JHipsterLandscape.from(moduleResources(firstModule, secondModule));

    assertThat(landscape.levels().get())
      .usingRecursiveFieldByFieldElementComparator()
      .containsExactly(
        landscapeLevel(landscapeFeature("my-feature", noDependencyLandscapeModule("first"), noDependencyLandscapeModule("second")))
      );
  }

  @Test
  void shouldBuildTwoLevelsLandscapeFromTwoModules() {
    JHipsterModuleResource firstModule = defaultModuleResourceBuilder().slug("first").build();
    JHipsterModuleResource secondModule = defaultModuleResourceBuilder().slug("second").moduleDependency("first").build();

    JHipsterLandscape landscape = JHipsterLandscape.from(moduleResources(firstModule, secondModule));

    assertThat(landscape.levels().get())
      .usingRecursiveFieldByFieldElementComparator()
      .containsExactly(
        landscapeLevel(noDependencyLandscapeModule("first")),
        landscapeLevel(oneModuleDependencyLandscapeModule("second", "first"))
      );
  }

  @Test
  void shouldBuildTwoLevelsLandscapeFromTwoModulesWithModuleDependencyInsideAFeature() {
    JHipsterModuleResource firstModule = defaultModuleResourceBuilder().slug("first").feature("root").build();
    JHipsterModuleResource secondModule = defaultModuleResourceBuilder().slug("second").moduleDependency("first").build();

    JHipsterLandscape landscape = JHipsterLandscape.from(moduleResources(firstModule, secondModule));

    assertThat(landscape.levels().get())
      .usingRecursiveFieldByFieldElementComparator()
      .containsExactly(
        landscapeLevel(landscapeFeature("root", noDependencyLandscapeModule("first"))),
        landscapeLevel(oneModuleDependencyLandscapeModule("second", "first"))
      );
  }

  @Test
  void shouldBuildThreeLevelsLandscapeFromFourModules() {
    JHipsterModuleResource firstModule = defaultModuleResourceBuilder().slug("first").build();
    JHipsterModuleResource secondModule = defaultModuleResourceBuilder().slug("second").feature("my-feature").build();
    JHipsterModuleResource thirdModule = defaultModuleResourceBuilder()
      .slug("third")
      .feature("my-feature")
      .moduleDependency("first")
      .build();
    JHipsterModuleResource forthModule = defaultModuleResourceBuilder().slug("forth").featureDependency("my-feature").build();

    JHipsterLandscape landscape = JHipsterLandscape.from(moduleResources(forthModule, secondModule, thirdModule, firstModule));

    assertThat(landscape.levels().get())
      .usingRecursiveFieldByFieldElementComparator()
      .containsExactly(
        landscapeLevel(noDependencyLandscapeModule("first")),
        landscapeLevel(
          landscapeFeature("my-feature", noDependencyLandscapeModule("second"), oneModuleDependencyLandscapeModule("third", "first"))
        ),
        landscapeLevel(
          JHipsterLandscapeModule
            .builder()
            .module("forth")
            .operation("operation")
            .dependencies(List.of(new JHipsterFeatureDependency(new JHipsterFeatureSlug("my-feature"))))
        )
      );
  }

  @Test
  void shouldSortLevelElements() {
    JHipsterModuleResource firstModule = defaultModuleResourceBuilder().slug("first").build();
    JHipsterModuleResource secondModule = defaultModuleResourceBuilder().slug("second").build();
    JHipsterModuleResource thirdModule = defaultModuleResourceBuilder().slug("third").moduleDependency("first").build();

    JHipsterLandscape landscape = JHipsterLandscape.from(moduleResources(firstModule, secondModule, thirdModule));

    JHipsterLandscapeLevel rootLevel = landscape.levels().get().iterator().next();
    assertThat(rootLevel.elements())
      .extracting(JHipsterLandscapeElement::slug)
      .containsExactly(new JHipsterModuleSlug("second"), new JHipsterModuleSlug("first"));
  }

  @Test
  void shouldRemoveNestedDependencies() {
    JHipsterModuleResource firstModule = defaultModuleResourceBuilder().slug("first").build();
    JHipsterModuleResource secondModule = defaultModuleResourceBuilder().slug("second").moduleDependency("first").build();
    JHipsterModuleResource thirdModule = defaultModuleResourceBuilder()
      .slug("third")
      .moduleDependency("second")
      .moduleDependency("first")
      .build();
    JHipsterModuleResource forth = defaultModuleResourceBuilder()
      .slug("forth")
      .moduleDependency("third")
      .moduleDependency("second")
      .moduleDependency("first")
      .build();

    JHipsterLandscape landscape = JHipsterLandscape.from(moduleResources(firstModule, secondModule, thirdModule, forth));

    assertThat(landscape.levels().get())
      .usingRecursiveFieldByFieldElementComparator()
      .containsExactly(
        landscapeLevel(noDependencyLandscapeModule("first")),
        landscapeLevel(oneModuleDependencyLandscapeModule("second", "first")),
        landscapeLevel(oneModuleDependencyLandscapeModule("third", "second")),
        landscapeLevel(oneModuleDependencyLandscapeModule("forth", "third"))
      );
  }
}

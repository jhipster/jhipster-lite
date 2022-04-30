<template>
  <div id="v-pills-init" class="tab-pane fade show active" role="tabpanel" aria-labelledby="v-pills-home-tab">
    <div class="d-flex flex-column gap-3">
      <div>
        <DefaultButtonVue id="init" :label="'Init'" :data-selector="selectorPrefix + '.init-button'" @click.prevent="initProject" />
      </div>
      <div>
        <DefaultButtonVue
          v-if="buildTool === 'maven'"
          id="maven"
          :label="'Maven'"
          :data-selector="selectorPrefix + '.add-maven-button'"
          @click.prevent="addMaven"
        />
        <DefaultButtonVue id="jacoco" :label="'JaCoCo'" :data-selector="selectorPrefix + '.add-jacoco-button'" @click.prevent="addJaCoCo" />
        <DefaultButtonVue
          id="sonar-backend"
          :label="'Sonar Backend'"
          :data-selector="selectorPrefix + '.add-sonar-backend-button'"
          @click.prevent="addSonarBackend"
        />
        <DefaultButtonVue
          id="sonar-backend-frontend"
          :label="'Sonar Backend+Frontend'"
          :data-selector="selectorPrefix + '.add-sonar-backend-frontend-button'"
          @click.prevent="addSonarBackendFrontend"
        />
      </div>
      <div>
        <DefaultButtonVue
          id="javabase"
          :label="'Java Base'"
          :data-selector="selectorPrefix + '.add-java-base-button'"
          @click.prevent="addJavaBase"
        />
      </div>
      <div>
        <DefaultButtonVue
          id="frontend-maven-plugin"
          :label="'Frontend Maven Plugin'"
          :data-selector="selectorPrefix + '.add-frontend-maven-plugin-button'"
          @click.prevent="addFrontendMavenPlugin"
        />
      </div>
      <div>
        <DefaultButtonVue
          v-if="project.folder !== ''"
          id="download"
          :label="'Download'"
          :data-selector="selectorPrefix + '.download-button'"
          @click.prevent="download"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./ProjectGenerator.component.ts"></script>

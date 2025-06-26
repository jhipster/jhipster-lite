package tech.jhipster.lite.generator.server.javatool.frontendmaven.domain;

import static tech.jhipster.lite.module.domain.JHipsterModule.pluginExecution;
import static tech.jhipster.lite.module.domain.mavenplugin.MavenBuildPhase.GENERATE_RESOURCES;
import static tech.jhipster.lite.module.domain.mavenplugin.MavenBuildPhase.TEST;

import tech.jhipster.lite.module.domain.mavenplugin.MavenPluginExecution;
import tech.jhipster.lite.module.domain.mavenplugin.MavenPluginExecutionGoal;
import tech.jhipster.lite.module.domain.nodejs.NodePackageManager;

final class MavenFrontendPluginExecutions {

  private static final MavenPluginExecutionGoal COREPACK = new MavenPluginExecutionGoal("corepack");

  private MavenFrontendPluginExecutions() {}

  static MavenPluginExecution.MavenPluginExecutionOptionalBuilder installNode(NodePackageManager nodePackageManager) {
    return switch (nodePackageManager) {
      case NPM -> pluginExecution()
        .goals("install-node-and-npm")
        .id("install-node-and-npm")
        .configuration(
          """
          <nodeVersion>${node.version}</nodeVersion>
          <npmVersion>${npm.version}</npmVersion>
          """
        );
      case PNPM -> pluginExecution()
        .goals("install-node-and-corepack")
        .id("install-node-and-corepack")
        .configuration(
          """
          <nodeVersion>${node.version}</nodeVersion>
          """
        );
    };
  }

  static MavenPluginExecution.MavenPluginExecutionOptionalBuilder installPackages(NodePackageManager nodePackageManager) {
    return switch (nodePackageManager) {
      case NPM -> pluginExecution().goals("npm").id("npm install");
      case PNPM -> pluginExecution().goals(COREPACK).id("pnpm install").configuration("<arguments>pnpm install</arguments>");
    };
  }

  static MavenPluginExecution.MavenPluginExecutionOptionalBuilder buildFront(NodePackageManager nodePackageManager) {
    return switch (nodePackageManager) {
      case NPM -> pluginExecution()
        .goals("npm")
        .id("build front")
        .phase(GENERATE_RESOURCES)
        .configuration(
          """
          <arguments>run build</arguments>
          <environmentVariables>
            <APP_VERSION>${project.version}</APP_VERSION>
          </environmentVariables>
          <npmInheritsProxyConfigFromMaven>false</npmInheritsProxyConfigFromMaven>
          """
        );
      case PNPM -> pluginExecution()
        .goals(COREPACK)
        .id("build front")
        .phase(GENERATE_RESOURCES)
        .configuration(
          """
          <arguments>pnpm build</arguments>
          <environmentVariables>
            <APP_VERSION>${project.version}</APP_VERSION>
          </environmentVariables>
          """
        );
    };
  }

  static MavenPluginExecution.MavenPluginExecutionOptionalBuilder testFront(NodePackageManager nodePackageManager) {
    return switch (nodePackageManager) {
      case NPM -> pluginExecution()
        .goals("npm")
        .id("front test")
        .phase(TEST)
        .configuration(
          """
          <arguments>run test:coverage</arguments>
          <npmInheritsProxyConfigFromMaven>false</npmInheritsProxyConfigFromMaven>
          """
        );
      case PNPM -> pluginExecution()
        .goals(COREPACK)
        .id("front test")
        .phase(TEST)
        .configuration(
          """
          <arguments>pnpm test:coverage</arguments>
          """
        );
    };
  }
}

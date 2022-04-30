<template>
  <div id="v-pills-springboot" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-profile-tab">
    <div class="d-flex flex-column gap-3">
      <div>
        <GeneratorButtonVue
          :label="'Spring Boot'"
          :service="'spring-boot'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBoot"
        />
        <GeneratorButtonVue
          :label="'Spring MVC Tomcat'"
          :service="'spring-boot-mvc-with-tomcat'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootMvcTomcat"
        />
        <GeneratorButtonVue
          :label="'Spring Webflux Netty'"
          :service="'spring-boot-webflux-netty'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootWebfluxNetty"
        />
        <GeneratorButtonVue
          :label="'Spring Boot Actuator'"
          :service="'spring-boot-actuator'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootActuator"
        />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'AOP Logging'"
          :service="'aop-logging'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootAopLogging"
        />
        <GeneratorButtonVue
          :label="'Logstash'"
          :service="'logstash'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootLogstash"
        />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'Security JWT'"
          :service="'spring-boot-jwt'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootSecurityJWT"
        />
        <GeneratorButtonVue
          :label="'Security JWT Basic Auth'"
          :service="'spring-boot-jwt-with-basic-authentication'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addSpringBootSecurityJWTBasicAuth"
        />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'PostgreSQL'"
          :service="'postgresql'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addPostgreSQL"
        />
        <GeneratorButtonVue :label="'MySQL'" :service="'mysql'" :selector-prefix="selectorPrefix" @click.prevent="addMySQL" />
        <GeneratorButtonVue :label="'MariaDB'" :service="'mariadb'" :selector-prefix="selectorPrefix" @click.prevent="addMariaDB" />
        <GeneratorButtonVue :label="'MongoDB'" :service="'mongodb'" :selector-prefix="selectorPrefix" @click.prevent="addMongoDB" />
      </div>
      <div>
        <GeneratorButtonVue
          :label="'Mongock'"
          :service="'mongock'"
          :selector-prefix="selectorPrefix"
          @click.prevent="addMongock"
        />
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./SpringBootGenerator.component.ts"></script>

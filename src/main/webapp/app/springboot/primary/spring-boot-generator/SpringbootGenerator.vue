<template>
  <div id="v-pills-springboot" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-profile-tab">
    <button
      id="springboot"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-button'"
      @click.prevent="addSpringBoot"
    >
      Spring Boot
    </button>

    <button
      id="springbootmvctomcat"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-mvc-tomcat-button'"
      @click.prevent="addSpringBootMvcTomcat"
    >
      Spring MVC Tomcat
    </button>

    <button
      id="springbootwebfluxnetty"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-webflux-netty-button'"
      @click.prevent="addSpringBootWebfluxNetty"
    >
      Spring Webflux Netty
    </button>

    <button
      id="springboot-actuator"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-actuator-button'"
      @click.prevent="addSpringBootActuator"
    >
      Spring Boot Actuator
    </button>
    <br />
    <br />
    <button
      id="springboot-aop"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-aop-button'"
      @click.prevent="addSpringBootAopLogging"
    >
      AOP Logging
    </button>

    <button
      id="springboot-logstash"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-logstash-button'"
      @click.prevent="addSpringBootLogstash"
    >
      Logstash
    </button>
    <br />
    <br />
    <button
      id="springboot-jwt"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-jwt-button'"
      @click.prevent="addSpringBootSecurityJWT"
    >
      Security JWT
    </button>

    <button
      id="springboot-jwt-basic-auth"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-jwt-basic-auth-button'"
      @click.prevent="addSpringBootSecurityJWTBasicAuth"
    >
      Security JWT Basic Auth
    </button>
    <br />
    <br />
    <button
      id="springboot-database-postgresql"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-database-postgresql-button'"
      @click.prevent="addPostgreSQL"
    >
      PostgreSQL
    </button>

    <button
      id="springboot-database-mysql"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-database-mysql-button'"
      @click.prevent="addMySQL"
    >
      MySQL
    </button>

    <button
      id="springboot-database-mariadb"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-database-mariadb-button'"
      @click.prevent="addMariaDB"
    >
      MariaDB
    </button>

    <button
      id="springboot-database-mongodb"
      class="btn btn-outline-primary"
      :data-selector="selectorPrefix + '.add-spring-boot-database-mongodb-button'"
      @click.prevent="addMongoDB"
    >
      MongoDB
    </button>
  </div>
</template>

<script lang="ts" src="./SpringBootGenerator.component.ts"></script>

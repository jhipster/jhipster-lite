#!/bin/bash

show_syntax() {
  echo "Usage: $0 <application>" >&2
  exit 1
}

if [ "$#" -ne 1 ]; then
  show_syntax
fi

application=$1

if test -f config/"$application".json; then
  filename=config/"$application".json
elif test -f tests-ci/config/"$application".json; then
  filename=tests-ci/config/"$application".json
else
  echo "The application" "$application" "does not exist!"
  exit 1
fi

callApi() {
  local api="$1"
  curl -X POST \
    -H "accept: */*" \
    -H "Content-Type: application/json" -d @"$filename" \
    "http://localhost:7471""$api"
}

springboot() {
  callApi "/api/projects/init"
  callApi "/api/build-tools/maven"
  callApi "/api/servers/java/base"
  callApi "/api/servers/java/jacoco-minimum-coverage"
  callApi "/api/servers/spring-boot"
  callApi "/api/servers/spring-boot/mvc/web/tomcat"
  callApi "/api/servers/spring-boot/mvc/web/actuator"
}

springboot_undertow() {
  callApi "/api/projects/init"
  callApi "/api/build-tools/maven"
  callApi "/api/servers/java/base"
  callApi "/api/servers/java/jacoco-minimum-coverage"
  callApi "/api/servers/spring-boot"
  callApi "/api/servers/spring-boot/mvc/web/undertow"
  callApi "/api/servers/spring-boot/mvc/web/actuator"
}

sonar_back() {
  callApi "/api/servers/sonar/java-backend"
}

sonar_back_front() {
  callApi "/api/servers/sonar/java-backend-and-frontend"
}

if [[ $application == 'full-default' ]]; then
  springboot
  sonar_back_front

  callApi "/api/servers/spring-boot/async"
  callApi "/api/servers/spring-boot/devtools"
  callApi "/api/servers/spring-boot/logging/aop"
  callApi "/api/servers/spring-boot/logging/logstash"
  callApi "/api/servers/spring-boot/banner/jhipster-v7"
  callApi "/api/servers/spring-boot/docker/jib"

  callApi "/api/servers/spring-boot/mvc/security/jwt"
  callApi "/api/servers/spring-boot/mvc/security/jwt/basic-auth"
  callApi "/api/servers/spring-boot/mvc/springdoc/init-with-security-jwt"
  callApi "/api/servers/spring-boot/mvc/dummy"

  callApi "/api/servers/spring-boot/databases/postgresql"
  callApi "/api/servers/spring-boot/user/postgresql"
  callApi "/api/servers/spring-boot/databases/migration/liquibase/init"
  callApi "/api/servers/spring-boot/databases/migration/liquibase/user"

  callApi "/api/servers/java/arch"

  callApi "/api/servers/spring-boot/cache/ehcache/java-configuration"
  callApi "/api/setup/codespaces"

  callApi "/api/frontend-maven-plugin"
  callApi "/api/vite/vue"

elif [[ $application == 'springboot' ]]; then
  springboot

elif [[ $application == 'tomcat-mysql-ehcachexml' ]]; then
  springboot
  sonar_back

  callApi "/api/servers/spring-boot/mvc/springdoc/init"
  callApi "/api/servers/spring-boot/databases/mysql"
  callApi "/api/servers/spring-boot/user/mysql"
  callApi "/api/servers/spring-boot/databases/migration/flyway/init"
  callApi "/api/servers/spring-boot/databases/migration/flyway/user"

  callApi "/api/servers/spring-boot/mvc/security/jwt"
  callApi "/api/servers/spring-boot/mvc/security/jwt/basic-auth"
  callApi "/api/servers/spring-boot/mvc/springdoc/init-with-security-jwt"

  callApi "/api/servers/spring-boot/cache/ehcache/xml-configuration"

elif [[ $application == 'undertow-simplecache' ]]; then
  springboot_undertow
  sonar_back

  callApi "/api/servers/spring-boot/banner/jhipster-v3"
  callApi "/api/servers/spring-boot/mvc/dummy"

  callApi "/api/servers/spring-boot/databases/mysql"
  callApi "/api/servers/spring-boot/user/mysql"
  callApi "/api/servers/spring-boot/databases/migration/liquibase/init"
  callApi "/api/servers/spring-boot/databases/migration/liquibase/user"
  callApi "/api/servers/spring-boot/cache/simple"

elif [[ $application == 'tomcat-configserver' ]]; then
  springboot
  sonar_back
  callApi "/api/servers/spring-boot/spring-cloud/config-client"
  callApi "/api/servers/sonar/java-backend"

elif [[ $application == 'tomcat-eureka' ]]; then
  springboot
  sonar_back
  callApi "/api/servers/spring-boot/spring-cloud/eureka-client"

elif [[ $application == 'undertow-consul' ]]; then
  springboot_undertow
  sonar_back
  callApi "/api/servers/spring-boot/spring-cloud/consul"

elif [[ $application == 'tomcat-mongodb' ]]; then
  springboot
  sonar_back
  callApi "/api/servers/spring-boot/databases/mongodb"

elif [[ $application == 'flywayapp' ]]; then
  springboot
  sonar_back
  callApi "/api/servers/spring-boot/databases/postgresql"
  callApi "/api/servers/spring-boot/user/postgresql"
  callApi "/api/servers/spring-boot/databases/migration/flyway/init"
  callApi "/api/servers/spring-boot/databases/migration/flyway/user"

elif [[ $application == 'angularapp' ]]; then
  springboot
  sonar_back_front
  callApi "/api/frontend-maven-plugin"
  callApi "/api/angular"

elif [[ $application == 'reactapp' ]]; then
  springboot
  sonar_back_front
  callApi "/api/frontend-maven-plugin"
  callApi "/api/vite/react"

elif [[ $application == 'vueapp' ]]; then
  springboot
  sonar_back_front
  callApi "/api/frontend-maven-plugin"
  callApi "/api/vite/vue/styled"

else
  echo "*** Unknown configuration..."
  exit 1
fi

echo ""
cat "$filename"
echo ""
sleep 5

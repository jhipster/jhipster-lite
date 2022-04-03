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
  status_code=$(curl -o /dev/null -s -w "%{http_code}\n" \
    -X POST \
    -H "accept: */*" \
    -H "Content-Type: application/json" -d @"$filename" \
    "http://localhost:7471""$api")

  if [[ $status_code == '40'* || $status_code == '50'* ]]; then
    echo "Error when calling API:" "$status_code"
    exit 1
  fi;
}

springboot() {
  callApi "/api/projects/init"
  callApi "/api/build-tools/maven"
  callApi "/api/github-actions/maven"
  callApi "/api/servers/java/base"
  callApi "/api/servers/java/jacoco-minimum-coverage"
  callApi "/api/servers/spring-boot"
  callApi "/api/servers/spring-boot/mvc/web/tomcat"
  callApi "/api/servers/spring-boot/mvc/web/actuator"
}

springboot_undertow() {
  callApi "/api/projects/init"
  callApi "/api/build-tools/maven"
  callApi "/api/github-actions/maven"
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

if [[ $application == 'springboot' ]]; then
  springboot
  sonar_back

elif [[ $application == 'fullstack' ]]; then
  springboot
  sonar_back_front

elif [[ $application == 'fullapp' ]]; then
  springboot
  sonar_back_front

  callApi "/api/servers/spring-boot/async"
  callApi "/api/servers/spring-boot/devtools"
  callApi "/api/servers/spring-boot/logging/aop"
  callApi "/api/servers/spring-boot/logging/logstash"
  callApi "/api/servers/spring-boot/banner/jhipster-v7"
  callApi "/api/servers/spring-boot/docker/jib"
  callApi "/api/servers/spring-boot/docker/dockerfile"
  callApi "/api/servers/java/arch"
  callApi "/api/setup/codespaces"

  callApi "/api/servers/spring-boot/mvc/security/jwt"
  callApi "/api/servers/spring-boot/mvc/security/jwt/basic-auth"
  callApi "/api/servers/spring-boot/mvc/springdoc/init-with-security-jwt"
  callApi "/api/servers/spring-boot/mvc/dummy"

  callApi "/api/servers/spring-boot/databases/postgresql"
  callApi "/api/servers/spring-boot/user/postgresql"
  callApi "/api/servers/spring-boot/databases/migration/liquibase/init"
  callApi "/api/servers/spring-boot/databases/migration/liquibase/user"

  callApi "/api/servers/spring-boot/cache/ehcache/java-configuration"

  callApi "/api/frontend-maven-plugin"
  callApi "/api/vue"

elif [[ $application == 'oauth2app' ]]; then
  springboot
  sonar_back

  callApi "/api/servers/spring-boot/mvc/security/oauth2"
  callApi "/api/servers/spring-boot/mvc/security/oauth2/account"

elif [[ $application == 'mysqlapp' ]]; then
  springboot
  sonar_back

  callApi "/api/servers/spring-boot/mvc/springdoc/init"
  callApi "/api/servers/spring-boot/mvc/dummy"

  callApi "/api/servers/spring-boot/databases/mysql"
  callApi "/api/servers/spring-boot/user/mysql"
  callApi "/api/servers/spring-boot/databases/migration/liquibase/init"
  callApi "/api/servers/spring-boot/databases/migration/liquibase/user"

  callApi "/api/servers/spring-boot/cache/ehcache/xml-configuration"

elif [[ $application == 'mariadbapp' ]]; then
  springboot
  sonar_back

  callApi "/api/servers/spring-boot/mvc/springdoc/init"
  callApi "/api/servers/spring-boot/mvc/dummy"

  callApi "/api/servers/spring-boot/databases/mariadb"
  callApi "/api/servers/spring-boot/user/mariadb"
  callApi "/api/servers/spring-boot/databases/migration/liquibase/init"
  callApi "/api/servers/spring-boot/databases/migration/liquibase/user"

  callApi "/api/servers/spring-boot/cache/ehcache/xml-configuration"

elif [[ $application == 'flywayapp' ]]; then
  springboot
  sonar_back

  callApi "/api/servers/spring-boot/databases/postgresql"
  callApi "/api/servers/spring-boot/user/postgresql"
  callApi "/api/servers/spring-boot/databases/migration/flyway/init"
  callApi "/api/servers/spring-boot/databases/migration/flyway/user"

elif [[ $application == 'undertowapp' ]]; then
  springboot_undertow
  sonar_back

  callApi "/api/servers/spring-boot/databases/mysql"
  callApi "/api/servers/spring-boot/user/mysql"
  callApi "/api/servers/spring-boot/databases/migration/flyway/init"
  callApi "/api/servers/spring-boot/databases/migration/flyway/user"

  callApi "/api/servers/spring-boot/cache/simple"

elif [[ $application == 'eurekaapp' ]]; then
  springboot
  sonar_back

  callApi "/api/servers/spring-boot/spring-cloud/eureka-client"
  callApi "/api/servers/spring-boot/spring-cloud/config-client"

elif [[ $application == 'consulapp' ]]; then
  springboot_undertow
  sonar_back

  callApi "/api/servers/spring-boot/spring-cloud/consul"

elif [[ $application == 'mongodbapp' ]]; then
  springboot
  sonar_back

  callApi "/api/servers/spring-boot/databases/mongodb"

elif [[ $application == 'angularapp' ]]; then
  springboot
  sonar_back_front

  callApi "/api/frontend-maven-plugin"
  callApi "/api/angular"

elif [[ $application == 'reactapp' ]]; then
  springboot
  sonar_back_front

  callApi "/api/frontend-maven-plugin"
  callApi "/api/react/styled"
  callApi "/api/react/cypress"

elif [[ $application == 'vueapp' ]]; then
  springboot
  sonar_back_front

  callApi "/api/frontend-maven-plugin"
  callApi "/api/vue/styled"
  callApi "/api/vue/pinia"

elif [[ $application == 'svelteapp' ]]; then
  springboot
  sonar_back_front

  callApi "/api/frontend-maven-plugin"
  callApi "/api/svelte/styled"

elif [[ $application == 'kafkaapp' ]]; then
  springboot
  sonar_back

  callApi "/api/servers/spring-boot/brokers/kafka"
  callApi "/api/servers/spring-boot/brokers/kafka/dummy-producer"
  callApi "/api/servers/spring-boot/brokers/kafka/akhq"

else
  echo "*** Unknown configuration..."
  exit 1
fi

echo ""
cat "$filename"
echo ""
sleep 5

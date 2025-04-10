#!/usr/bin/env bash

curl -u admin:admin -X POST \
  "http://localhost:9001/api/users/change_password" \
  -d "login=admin&previousPassword=admin&password=NewSecurePassword_123"

SONAR_TOKEN=$(curl -u admin:NewSecurePassword_123 -X POST "http://localhost:9001/api/user_tokens/generate" -d "name=beer-token" | jq -r .token)
echo $SONAR_TOKEN

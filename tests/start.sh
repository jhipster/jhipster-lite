#!/bin/bash

echo "Copy JHipster Light Jar file"
cp ../target/*.jar .

echo "Starting JHipster Light..."
java \
  -jar *.jar \
  --logging.level.ROOT=OFF & > /dev/null
echo $! > .pid-jhlight

#!/bin/bash

echo "Copy JHipster Lite Jar file"
cp ../target/*.jar .

echo "Starting JHipster Lite..."
java \
  -jar *.jar \
  --logging.level.ROOT=OFF & > /dev/null
echo $! > .pid-jhlite

echo "Waiting 10sec..."
sleep 10

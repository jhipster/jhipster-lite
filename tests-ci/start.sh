#!/bin/bash

echo "Waiting 5sec to be sure the Jar is here"
sleep 5

echo "Copy JHipster Lite Jar file"
cp ../target/*.jar .

echo "Starting JHipster Lite..."
java \
  -jar *.jar \
  --logging.level.ROOT=OFF & > /dev/null
echo $! > .pid-jhlite

echo "Waiting 15sec..."
sleep 15

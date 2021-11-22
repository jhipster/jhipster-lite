#!/bin/bash

echo "Stopping JHipster Light in 5sec..."
sleep 5
kill $(cat .pid-jhlight)

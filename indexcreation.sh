#!/bin/bash

for (( c=1; c<=100; c++ ))
do
   echo "add index"
   curl 'localhost:8080/testcreate'
done

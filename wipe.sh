#!/bin/bash

curl 'localhost:9200/_cat/indices?v' | cut -d' ' -f6 | while read line ; do curl -XDELETE "localhost:9200/$line"; done

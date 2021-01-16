#!/bin/bash

for filename in docker/*; do
    [ -e "$filename" ] || continue
    tag=$(echo "$filename" | tr '[:upper:]' '[:lower:]' | sed 's:.*/::')
    echo "Build docker image $filename - $tag" 
    docker build -f "$filename" . -t "$tag"
done


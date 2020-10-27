#!/bin/bash

DIR=$(realpath -s "$(dirname "$0")")

docker run -v "$DIR":"$DIR" --entrypoint clang++ clang:latest "$@"

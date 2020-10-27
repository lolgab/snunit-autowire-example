#!/bin/bash

set -e

# When we switch between different operative systems
# we need to manually wipe the Scala Native c files
# compilation cache
rm -rf example/native/target/scala-2.11/native/**.o

# We are using our clang scripts that call clang inside
# docker so we can compile linux binaries from Mac
sbtn "exampleJS/fastOptJS; \
      set example.native / nativeClang := file(\"./clang.sh\"); \
      set example.native / nativeClangPP := file(\"./clang++.sh\"); \
      exampleNative/nativeLink"

docker build -t example-app .

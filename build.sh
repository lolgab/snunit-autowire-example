#!/bin/bash

set -e

export SBT_NATIVE_CLIENT=true

# When we switch between different operative systems
# we need to manually wipe the Scala Native c files
# compilation cache
find backend/native/target/scala-2.13/native -type f -name '*.o' -exec rm {} +

# clang container if it's running
clang_container=$(docker ps -f ancestor=lolgab/snunit-clang:0.0.2 --format {{.ID}})

if [ -z $clang_container ]; then
     # run a docker image with linux clang
     clang_container=$(docker run -d -i --rm -v "$PWD":"$PWD" lolgab/snunit-clang:0.0.2)
fi

echo '#!/bin/bash
docker exec '$clang_container' clang "$@"' > target/clang.sh

chmod +x target/clang.sh

echo '#!/bin/bash
docker exec '$clang_container' clang++ "$@"' > target/clang++.sh

chmod +x target/clang++.sh

# We are using our clang scripts that call clang inside
# docker so we can compile linux binaries from Mac
sbt frontend/fastLinkJS
sbt "set backend.native / nativeClang := file(\"./target/clang.sh\")"
sbt "set backend.native / nativeClangPP := file(\"./target/clang++.sh\")"
sbt backendNative/nativeLink

config='{'
config+='  "listeners": {'
config+='    "*:'$port'": {'
config+='      "pass": "routes"'
config+='    }'
config+='  },'
config+='  "routes": ['
config+='    {'
config+='      "action": {'
config+='        "share": "'$PWD/www'$uri",'
config+='        "fallback": {'
config+='          "share": "'$PWD/target/www'$uri",'
config+='          "fallback": {'
config+='            "pass": "applications/todos"'
config+='          }'
config+='        }'
config+='      }'
config+='    }'
config+='  ],'
config+='  "applications": {'
config+='    "todos": {'
config+='      "type": "external",'
config+='      "executable": "'$PWD'/target/app/example"'
config+='    }'
config+='  }'
config+='}'

curl -X PUT \
     --data-binary "$config" \
     --unix-socket /usr/local/var/run/unit/control.sock \
     http://localhost/config

docker build -t example-app .

#!/bin/bash

set -e

export SBT_NATIVE_CLIENT=true
port=8081

# When we switch between different operative systems
# we need to manually wipe the Scala Native C files
# compilation cache using these commands:

# sbt reload
# find example/native/target/scala-2.13/native -type f -name '*.o' -exec rm {} +

# sbt frontend/fastLinkJS
# sbt backendNative/nativeLink

rm -rf target/www target/app
mkdir -p target/www target/app
cp backend/native/target/scala-2.13/backend-out target/app/example
cp -r frontend/target/scala-2.13/frontend-fastopt/ target/www/

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
config+='            "pass": "applications/example"'
config+='          }'
config+='        }'
config+='      }'
config+='    }'
config+='  ],'
config+='  "applications": {'
config+='    "example": {'
config+='      "type": "external",'
config+='      "executable": "'$PWD'/target/app/example"'
config+='    }'
config+='  }'
config+='}'

curl -X PUT \
     --data-binary "$config" \
     --unix-socket /usr/local/var/run/unit/control.sock \
     http://localhost/config

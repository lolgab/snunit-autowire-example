#!/bin/bash

set -e

port=8080

# When we switch between different operative systems
# we need to manually wipe the Scala Native c files
# compilation cache using these commands:

# sbtn reload
# find example/native/target/scala-2.11/native -type f -name '*.o' -exec rm {} +

sbtn "exampleJS/fastOptJS;exampleNative/nativeLink"

rm -rf target/www target/app
mkdir -p target/www target/app
cp example/native/target/scala-2.11/example-out target/app/example
cp example/js/target/scala-2.13/example-fastopt.js target/www/index.js

config='{'
config+='  "listeners": {' 
config+='    "*:'$port'": {'
config+='      "pass": "routes"'
config+='    }'
config+='  },'
config+='  "routes": ['
config+='    {'
config+='      "action": {'
config+='        "share": "'$PWD/www'",'
config+='        "fallback": {'
config+='          "share": "'$PWD/target/www'",'
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
config+='      "working_directory": "'$PWD/target/app'",'
config+='      "executable": "example"'
config+='    }'
config+='  }'
config+='}'

curl -X PUT \
     --data-binary "$config" \
     --unix-socket /usr/local/var/run/unit/control.sock \
     http://localhost/config

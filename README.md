# Example SNUnit application

This is an example basic TODO application using the following technologies:

* [Scala Native](https://github.com/scala-native/scala-native)
* [NGINX Unit](https://unit.nginx.org/)
* [SNUnit](https://github.com/lolgab/snunit)
* [Autowire](https://github.com/lihaoyi/autowire)
* [Scala.js](https://www.scala-js.org/)
* [Laminar](https://laminar.dev/)

## Getting Started

You need to make sure you have Sbt 1.4+ with `sbtn` in your `PATH`.

### Running locally

You need to install Unit and run it as daemon:

```bash
unitd
```

Then you can build and run locally using the build-dev.sh script:

```bash
./build-dev.sh
```

You can then open your browser on `http://127.0.0.1:8080`

### Running on Docker

You can build a Docker image running:

```bash
# To create the clang docker image used to link a Scala Native
# Linux binary from Mac too:
docker build -t clang --file Dockerfile.clang .

# To build the actual application:
docker build -t example-app .
```

You can the run the application with:

```bash
docker run --rm -p 8080:8080 example-app
```

You can then open your browser on `http://127.0.0.1:8080`

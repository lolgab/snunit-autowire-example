# Example SNUnit application

This is an example basic TODO application using the following technologies:

* [Scala Native](https://github.com/scala-native/scala-native)
* [NGINX Unit](https://unit.nginx.org/)
* [SNUnit](https://github.com/lolgab/snunit)
* [Autowire](https://github.com/lihaoyi/autowire)
* [Scala.js](https://www.scala-js.org/)
* [Laminar](https://laminar.dev/)

## Getting Started

You need to make sure you have Sbt 1.4.0 with sbtn in your `PATH`.
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
docker run --rm -p 80:80 example-app
```

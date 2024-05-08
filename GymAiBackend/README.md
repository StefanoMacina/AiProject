# Getting Started

### Reference Documentation
* create new image with dockerfile :
  docker build . --tag kishieel/spring-hot-reloading --target dev

* run new container from this image
  docker run --rm -it -v $(pwd):/app -p 8080:8080 kishieel/spring-hot-reloading


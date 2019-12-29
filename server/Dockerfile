FROM adoptopenjdk/openjdk10:x86_64-alpine-jdk-10.0.2.13 
WORKDIR /
MAINTAINER jody@aporeto.com

RUN apk update

# Generic
RUN apk add bash curl bind-tools jq certbot

RUN mkdir -p /noderelay

ADD src/main/docker/run /noderelay
ADD src/main/docker/logging.xml /noderelay
ADD src/main/docker/debug-logging.xml /noderelay
ADD src/main/docker/example_conf /noderelay
ADD target/noderelay-server.jar /noderelay/

RUN chmod 755 /noderelay/run

CMD ["/noderelay/run"]

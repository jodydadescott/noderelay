BUILD_NUMBER := latest
PROJECT_NAME := noderelay
DOCKER_REGISTRY := jodydadescott
DOCKER_IMAGE_NAME?=$(PROJECT_NAME)
DOCKER_IMAGE_TAG?=$(BUILD_NUMBER)

build:
	$(MAKE) maven
	$(MAKE) docker

maven:
	mvn clean package

docker:
	docker build -t $(DOCKER_REGISTRY)/$(DOCKER_IMAGE_NAME):$(DOCKER_IMAGE_TAG) .

shell:
	docker run -it --entrypoint /bin/bash $(DOCKER_REGISTRY)/$(PROJECT_NAME):$(BUILD_NUMBER)||:

push:
	docker push $(DOCKER_REGISTRY)/$(DOCKER_IMAGE_NAME):$(DOCKER_IMAGE_TAG)

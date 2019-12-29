SRC_OUT := src/main/java
GEN_GRPC_JAVA_BIN := /usr/local/bin/protoc-gen-grpc-java
PROTOC_BIN := /usr/local/bin/protoc
PROTOC_FILE := src/main/proto/NodeReflector.proto
GRPC_FILE := src/main/java/noderelayclient/NodeServiceGrpc.java

build:
	$(MAKE) proto
	mvn clean install

proto:
	$(PROTOC_BIN) --grpc-java_out=$(SRC_OUT) --plugin=protoc-gen-grpc-java=$(GEN_GRPC_JAVA_BIN) --java_out=$(SRC_OUT) $(PROTOC_FILE)
	cat $(GRPC_FILE) | sed 's/javax.annotation.Generate/javax.annotation.processing.Generate/g' > target/grpc.temp
	cat target/grpc.temp > $(GRPC_FILE)
	$(RM) target/grpc.temp

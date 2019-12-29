package noderelay.server.grpc;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import noderelay.db.NodeReflectorApi;

public class GrpcServer {

	private static final Logger LOG = LoggerFactory.getLogger(GrpcServer.class);

	private final Server server;

	public GrpcServer(GrpcConfig config, NodeReflectorApi api) {

		LOG.info("Starting");

		assert config != null;
		assert api != null;

		int port = config.getPort();

		try {
			server = ServerBuilder.forPort(port).addService(new GrpcHandler(api)).build().start();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		LOG.info("Started on port {}", port);
	}

	public void shutdown() {
		LOG.info("Shutting down");
		this.server.shutdown();
	}

}

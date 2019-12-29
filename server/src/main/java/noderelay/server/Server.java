package noderelay.server;

import noderelay.db.DatabaseServer;
import noderelay.server.grpc.GrpcServer;
import noderelay.server.rest.RestServer;

public class Server {

	private final DatabaseServer databaseServer;
	private final GrpcServer grpcServer;
	private final RestServer restServer;

	public static Server newInstance(ServerConfig mainConfig) {
		return new Server(mainConfig);
	}

	private Server(ServerConfig mainConfig) {

		assert mainConfig != null;
		databaseServer = DatabaseServer.newInstance(mainConfig.getDatabaseConfig());

		if (mainConfig.isGrpcEnabled()) {
			this.grpcServer = new GrpcServer(mainConfig.getGrpcConfig(), databaseServer.getNodeReflectorApi());
		} else {
			this.grpcServer = null;
		}

		if (mainConfig.isRestEnabled()) {
			this.restServer = new RestServer(mainConfig.getRestConfig(), databaseServer.getNodeReflectorApi());
		} else {
			this.restServer = null;
		}

	}

	public void shutdown() {
		if (this.restServer != null) this.restServer.shutdown();
		if (this.grpcServer != null) this.grpcServer.shutdown();
		this.databaseServer.shutdown();
	}

}

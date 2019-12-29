package noderelay.server;

import noderelay.db.DatabaseConfig;
import noderelay.server.grpc.GrpcConfig;
import noderelay.server.rest.RestConfig;

public class ServerConfig {

	private DatabaseConfig databaseConfig = new DatabaseConfig();
	private GrpcConfig grpcConfig = new GrpcConfig();
	private RestConfig restConfig = new RestConfig();
	private boolean grpcEnabled;
	private boolean restEnabled;

	public DatabaseConfig getDatabaseConfig() {
		return this.databaseConfig;
	}

	public ServerConfig setDatabaseConfig(DatabaseConfig databaseConfig) {
		if (databaseConfig == null) {
			this.databaseConfig = new DatabaseConfig();
		} else {
			this.databaseConfig = databaseConfig;
		}
		return this;
	}

	public GrpcConfig getGrpcConfig() {
		return grpcConfig;
	}

	public ServerConfig setGrpcConfig(GrpcConfig grpcConfig) {
		if (grpcConfig == null) {
			this.grpcConfig = new GrpcConfig();
		} else {
			this.grpcConfig = grpcConfig;
		}
		return this;
	}

	public boolean isGrpcEnabled() {
		return this.grpcEnabled;
	}

	public ServerConfig enableGrpc(boolean grpcEnabled) {
		this.grpcEnabled = grpcEnabled;
		return this;
	}

	public RestConfig getRestConfig() {
		return this.restConfig;
	}

	public ServerConfig setRestConfig(RestConfig restConfig) {
		if (restConfig == null) {
			this.restConfig = new RestConfig();
		} else {
			this.restConfig = restConfig;
		}
		return this;
	}

	public boolean isRestEnabled() {
		return this.restEnabled;
	}

	public ServerConfig enableRest(boolean restEnabled) {
		this.restEnabled = restEnabled;
		return this;
	}

}

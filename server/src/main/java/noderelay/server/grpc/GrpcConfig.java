package noderelay.server.grpc;

public class GrpcConfig implements Cloneable {

	private Integer port;

	public GrpcConfig() {

	}

	public GrpcConfig(GrpcConfig grpcConfig) {
		if (grpcConfig != null) {
			if (grpcConfig.hasPort()) {
				this.port = grpcConfig.getPort();
			}
		}
	}

	public boolean hasPort() {
		return port != null;
	}

	public int getPort() {
		nullCheck("port", this.port);
		return port;
	}

	public GrpcConfig setPort(Integer port) {
		this.port = port;
		return this;
	}

	private void nullCheck(String field, Object o) {
		if (o == null) {
			throw new NullPointerException(field + " is null");
		}
	}

	@Override
	public GrpcConfig clone() {
		return new GrpcConfig(this);
	}

}

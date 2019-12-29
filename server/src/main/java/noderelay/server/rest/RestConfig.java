package noderelay.server.rest;

public class RestConfig {

	private Integer port;

	public RestConfig() {

	}

	public boolean hasPort() {
		return port != null;
	}

	public int getPort() {
		nullCheck("port", this.port);
		return port;
	}

	public RestConfig setPort(Integer port) {
		this.port = port;
		return this;
	}

	private void nullCheck(String field, Object o) {
		if (o == null) {
			throw new NullPointerException(field + " is null");
		}
	}

}

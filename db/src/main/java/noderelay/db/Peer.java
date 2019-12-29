package noderelay.db;

public class Peer {

	public static final String PORT_DELIMITER = ":";

	private String hostname;
	private Integer port;

	public Peer(String peerString) {
		assert peerString != null;

		try {
			String[] peerStringArray = peerString.split(PORT_DELIMITER);
			this.hostname = peerStringArray[0];
			this.port = Integer.valueOf(peerStringArray[1]);
		} catch (IndexOutOfBoundsException e) {
			throw new NumberFormatException("Invalid format " + peerString + "; valid format is hostname:port");
		}

	}

	public Peer() {

	}

	public String getHostname() {
		Tools.nullCheck("hostname", hostname);
		return hostname;
	}

	public Peer setHostname(String hostname) {
		this.hostname = hostname;
		return this;
	}

	public Integer getPort() {
		Tools.nullCheck("port", port);
		return port;
	}

	public Peer setPort(Integer port) {
		this.port = port;
		return this;
	}

	@Override
	public String toString() {
		return "Peer [hostname=" + hostname + ", port=" + port + "]";
	}

}

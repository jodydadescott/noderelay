package noderelay.db;

import java.util.Collection;
import java.util.HashSet;

public class DatabaseConfig {

	private Integer port;
	private Collection<Peer> peers = new HashSet<>();
	private Collection<String> netInterfaces = new HashSet<>();
	private Long ttl;
	private String publicIp;

	public boolean hasPort() {
		return this.port != null;
	}

	public int getPort() {
		Tools.nullCheck("port", this.port);
		return this.port;
	}

	public DatabaseConfig setPort(Integer port) {
		this.port = port;
		return this;
	}

	public boolean hasTtl() {
		return this.ttl != null;
	}

	public long getTtl() {
		Tools.nullCheck("ttl", this.ttl);
		return this.ttl;
	}

	public DatabaseConfig setTtl(Long ttl) {
		this.ttl = ttl;
		return this;
	}

	public Collection<Peer> getPeers() {
		return this.peers;
	}

	public boolean hasPublicIp() {
		return this.publicIp != null;
	}

	public String getPublicIp() {
		Tools.nullCheck("publicIp", this.publicIp);
		return this.publicIp;
	}

	public DatabaseConfig setPublicIp(String publicIp) {
		this.publicIp = publicIp;
		return this;
	}

	public Collection<String> getNetInterfaces() {
		return this.netInterfaces;
	}

}

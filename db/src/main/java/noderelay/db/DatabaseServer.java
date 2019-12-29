package noderelay.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.ReplicatedMap;
import com.hazelcast.flakeidgen.FlakeIdGenerator;

public class DatabaseServer {

	private static final String DB_NAME = "noderelay";

	private static final Logger LOG = LoggerFactory.getLogger(DatabaseServer.class);

	private static final long DEFAULT_TTL = 3600;
	private static final int DEFAULT_PORT = 9001;

	public static DatabaseServer newInstance(DatabaseConfig databaseConfig) {
		return new DatabaseServer(databaseConfig);
	}

	private final HazelcastInstance hazelcastInstance;
	private final NodeReflectorApi nodeReflectorApi;
	private long ttl;

	private DatabaseServer(DatabaseConfig databaseConfig) {

		LOG.info("Starting");

		assert databaseConfig != null;

		if (databaseConfig.hasTtl()) {
			this.ttl = databaseConfig.getTtl();
			LOG.info("TTL set to {} (config)", databaseConfig.getTtl());
		} else {
			this.ttl = DEFAULT_TTL;
			LOG.info("TTL set to {} (default)", DEFAULT_TTL);
		}

		com.hazelcast.config.Config hazelcastConfig = new com.hazelcast.config.Config();

		hazelcastConfig.setProperty("hazelcast.logging.type", "slf4j");

		hazelcastConfig.getGroupConfig().setName(DB_NAME);
		// hazelcastConfig.getGroupConfig().setPassword("NOT A GOOD SECRET");

		// Network discovery
		hazelcastConfig.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
		hazelcastConfig.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(true);

		for (String netInterface : databaseConfig.getNetInterfaces()) {
			LOG.info("Adding network interface {}", netInterface);
			hazelcastConfig.getNetworkConfig().getInterfaces().addInterface(netInterface);
			hazelcastConfig.getNetworkConfig().getInterfaces().setEnabled(true);
		}

		if (databaseConfig.hasPublicIp()) {
			LOG.info("Public IP set to {}", databaseConfig.getPublicIp());
			hazelcastConfig.getNetworkConfig().setPublicAddress(databaseConfig.getPublicIp());
		}

		int port = 0;

		if (databaseConfig.hasPort()) {
			port = databaseConfig.getPort();
		} else {
			port = DEFAULT_PORT;
		}

		hazelcastConfig.getNetworkConfig().setPort(port);
		LOG.debug("Port set to {} (default)", DEFAULT_PORT);

		if (databaseConfig.getPeers().isEmpty()) {
			LOG.warn("No peers configured. Seems a little strange.");
		} else {
			LOG.debug("Adding peers");

			for (Peer peer : databaseConfig.getPeers()) {
				LOG.debug("Adding peer {}", peer);
				hazelcastConfig.getNetworkConfig().getJoin().getTcpIpConfig()
						.addMember(peer.getHostname() + ":" + peer.getPort());
			}
		}

		HazelcastInstance tempHazelcastInstance = Hazelcast.newHazelcastInstance(hazelcastConfig);
		// If the above throws an exception
		this.hazelcastInstance = tempHazelcastInstance;

		LOG.info("Started on port {}", port);

		this.nodeReflectorApi = new NodeReflectorApi(this);

	}

	<K, V> IMap<K, V> getMap(String name) {
		assert name != null;
		return hazelcastInstance.getMap(name);
	}

	<K, V> ReplicatedMap<K, V> getReplicatedMap(String name) {
		assert name != null;
		return hazelcastInstance.getReplicatedMap(name);
	}

	FlakeIdGenerator getIdGen(String name) {
		return hazelcastInstance.getFlakeIdGenerator(name);
	}

	long getTtl() {
		return this.ttl;
	}

	public NodeReflectorApi getNodeReflectorApi() {
		return this.nodeReflectorApi;
	}

	public void shutdown() {
		if (this.hazelcastInstance == null) {
			LOG.warn("Request to shutdown but server is not running");
		} else {
			LOG.info("Shutting down");
			this.hazelcastInstance.shutdown();
		}
	}

}

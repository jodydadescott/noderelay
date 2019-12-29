package noderelay.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TXTRecord;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

import com.thescottsweb.ipaddr.IpAddr;

import noderelay.db.Peer;

public class Main {

	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	public static final String ENV_DB_PORT = "DB_PORT";
	public static final String ENV_DB_PEERS = "DB_PEERS";
	public static final String ENV_DB_TTL = "DB_TTL";
	public static final String COLON = ":";

	public static final String ENV_GRPC_PORT = "GRPC_PORT";

	public static final String ENV_REST_PORT = "REST_PORT";

	public static final String ENV_SERVICE_NAME = "SERVICE_NAME";
	public static final String ENV_PEERS = "PEERS";

	public static final String ENV_NET_INTERFACES = "NET_INTERFACES";
	public static final String COMMA = ",";

	public static final String ENV_PUBLIC_IP_OR_URL = "PUBLIC_IP_OR_URL";

	private static final String PUBLIC_IP_INFO_URL_DEFAULT = "https://ipinfo.io/ip";

	public static void main(String[] args) {
		new Main(args);
	}

	private Server server;

	private Main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				shutdown();
			}
		});
		ServerConfig config = new ServerConfig();

		Map<String, String> map = System.getenv();

		LOG.info("Loading config");

		if (map.containsKey(ENV_DB_PORT)) {
			config.getDatabaseConfig().setPort(Integer.valueOf(map.get(ENV_DB_PORT)));
		}

		if (map.containsKey(ENV_DB_PEERS)) {
			for (String peerString : map.get(ENV_DB_PEERS).split(COLON)) {
				config.getDatabaseConfig().getPeers().add(new Peer(peerString));
			}
		}

		if (map.containsKey(ENV_DB_TTL)) {
			config.getDatabaseConfig().setTtl(Long.valueOf(map.get(ENV_DB_TTL)));
		}

		if (map.containsKey(ENV_GRPC_PORT)) {
			config.getGrpcConfig().setPort(Integer.valueOf(map.get(ENV_GRPC_PORT)));
			config.enableGrpc(true);
		}

		if (map.containsKey(ENV_REST_PORT)) {
			config.getRestConfig().setPort(Integer.valueOf(map.get(ENV_REST_PORT)));
			config.enableRest(true);
		}

		if (map.containsKey(ENV_NET_INTERFACES)) {
			for (String netInterface : map.get(ENV_NET_INTERFACES).split(COMMA)) {
				config.getDatabaseConfig().getNetInterfaces().add(netInterface);
			}
		}

		IpAddr publicIpAddr = null;
		if (map.containsKey(ENV_PUBLIC_IP_OR_URL)) {
			publicIpAddr = getIpAddrFromUrlOrIpString(map.get(ENV_PUBLIC_IP_OR_URL));
		} else {
			publicIpAddr = getIpAddrFromUrlOrIpString(PUBLIC_IP_INFO_URL_DEFAULT);
		}

		config.getDatabaseConfig().setPublicIp(publicIpAddr.toString());

		if (map.containsKey(ENV_PEERS)) {
			for (String peerString : map.get(ENV_PEERS).split(COMMA)) {
				config.getDatabaseConfig().getPeers().add(new Peer(peerString));
			}
		}

		if (map.containsKey(ENV_SERVICE_NAME)) {

			String serviceName = map.get(ENV_SERVICE_NAME);

			LOG.info("Looking up records for {}", serviceName);

			try {
				Record[] records = new Lookup(serviceName, Type.TXT).run();

				if (records.length == 0) {
					LOG.warn("No peers found in DNS lookup");
				} else {
					for (Record genericRecord : records) {
						TXTRecord tXTRecord = (TXTRecord) genericRecord;
						for (Object peerObject : tXTRecord.getStrings()) {

							String hostnamePort = String.valueOf(peerObject);
							String[] hostnamePortArray = hostnamePort.split(":");

							String hostname = null;
							Integer port = null;

							if (hostnamePortArray.length >= 2) {
								if (!hostnamePortArray[0].equals("")) {
									hostname = hostnamePortArray[0];
								}
								try {
									port = Integer.valueOf(hostnamePortArray[1]);
								} catch (NumberFormatException e) {
								}
							}

							if (hostname == null && port == null) {
								LOG.warn("hostnamePort {} is invalid. expected format is hostname:port", hostnamePort);
							} else if (hostname == null) {
								LOG.warn(
										"hostnamePort {} is invalid. expected format is hostname:port (missing hostname)");
							} else if (port == null) {
								LOG.warn(
										"hostnamePort {} is invalid. expected format is hostname:port (missing or invalid port)");
							} else {

								LOG.info("Resolving hostname {}", hostname);
								try {
									InetAddress address = InetAddress.getByName(hostname);
									String ip = address.getHostAddress();

									if (publicIpAddr.equals(IpAddr.valueOf(ip))) {
										LOG.info("IP {} is our public IP. Not adding to members", publicIpAddr);
									} else {
										LOG.info("IP of hostname {} is {}", hostname, ip);
										config.getDatabaseConfig().getPeers()
												.add(new Peer().setHostname(ip).setPort(port));
									}

								} catch (UnknownHostException e) {
									LOG.info("Unable to resolve hostname {}", hostname);
									// e.printStackTrace();
								}

							}

						}
					}
				}

			} catch (TextParseException e) {
				throw new RuntimeException(e);
			}

		}

		LOG.info("Starting");
		server = Server.newInstance(config);

	}

	private void shutdown() {
		LOG.info("Shutting down");
		if (server != null) {
			server.shutdown();
		}
	}

	private IpAddr getIpAddrFromUrlOrIpString(String urlOrIp) {
		assert urlOrIp != null;
		IpAddr result = null;

		int c = 0;
		while (true) {
			c++;
			result = _getIpAddrFromUrlOrIpString(urlOrIp);
			if (result != null) {
				LOG.debug("Got IP on try {} of {}", c, 3);
				break;
			}
			if (c > 3) {
				break;
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
		}

		LOG.debug("Unable to get IP in {} tries", c);

		if (result == null) {
			throw new RuntimeException("Unable to resolve Public IP with " + urlOrIp);
		}

		return result;

	}

	private IpAddr _getIpAddrFromUrlOrIpString(String urlOrIp) {
		assert urlOrIp != null;

		IpAddr ipAddr = null;

		if (IpAddr.validate(urlOrIp)) {
			LOG.debug("{} is an IP Address", urlOrIp);
			ipAddr = IpAddr.valueOf(urlOrIp);
		} else if (urlOrIp.contains("http")) {
			LOG.debug("{} is a URL; looking up IP", urlOrIp);

			HttpURLConnection conn = null;

			try {

				conn = (HttpURLConnection) new URL(urlOrIp).openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/text");

				if (conn.getResponseCode() != 200) {
					LOG.debug("URL {} returned error code {}; unable to get IP", urlOrIp, conn.getResponseCode());
					return null;
				}

				try (BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {

					StringBuilder sb = new StringBuilder();
					String s;
					while ((s = br.readLine()) != null) {
						sb.append(s);
					}

					String ipString = sb.toString();

					if (IpAddr.validate(ipString)) {
						LOG.debug("String {} is a valid IP", ipString);
						return IpAddr.valueOf(ipString);
					} else {
						LOG.debug("String {} is not a valid IPV4 address", ipString);
					}

				}

			} catch (MalformedURLException e) {

				LOG.debug(e.toString());

			} catch (IOException e) {

				LOG.debug(e.toString());

			} finally {
				if (conn != null) {
					conn.disconnect();
				}
			}

		} else {
			throw new RuntimeException("{} is not a valid IP or URL");
		}

		return ipAddr;
	}

}

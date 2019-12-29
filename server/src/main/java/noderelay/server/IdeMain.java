package noderelay.server;

public class IdeMain {

	private static final int DB_PORT = 9001;
	private static final long DB_TTL = 500;

	private static final int GRPC_PORT = 7001;

	private static final int REST_PORT = 8080;

	public static void main(String[] args) {
		new IdeMain(args);
	}

	private Server server;

	private IdeMain(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (server != null) {
					server.shutdown();
				}
			}
		});

		ServerConfig config = new ServerConfig();

		config.getDatabaseConfig().setPort(DB_PORT).setTtl(DB_TTL);

		config.enableGrpc(true).enableRest(true);

		config.getGrpcConfig().setPort(GRPC_PORT);

		config.getRestConfig().setPort(REST_PORT);

		server = Server.newInstance(config);
	}

}

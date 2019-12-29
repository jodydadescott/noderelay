package noderelayclient;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import noderelayclient.NodeReflector.Node;

public class GrpcClient {

	private final ManagedChannel channel;
	private final NodeServiceGrpc.NodeServiceBlockingStub blockingStub;

	/**
	 * Construct client connecting to HelloWorld server at {@code host:port}.
	 */
	public GrpcClient(String host, int port) {
		this(ManagedChannelBuilder.forAddress(host, port)
				// Channels are secure by default (via SSL/TLS). For the example
				// we disable TLS to avoid
				// needing certificates.
				.usePlaintext().build());
	}

	/**
	 * Construct client for accessing HelloWorld server using the existing channel.
	 */
	GrpcClient(ManagedChannel channel) {
		this.channel = channel;
		blockingStub = NodeServiceGrpc.newBlockingStub(channel);
	}

	public void shutdown() throws InterruptedException {
		channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
	}

	public Node put(String userdata, String secret) {

		Node request = Node.newBuilder().setUserdata(userdata).setSecret(secret).build();
		try {
			return blockingStub.put(request);

		} catch (StatusRuntimeException e) {
			throw new RuntimeException(e);
		}
	}

}

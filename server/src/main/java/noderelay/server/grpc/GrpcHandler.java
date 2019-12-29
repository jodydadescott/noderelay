package noderelay.server.grpc;

import io.grpc.stub.StreamObserver;
import noderelay.db.NodeReflector.Node;
import noderelay.db.NodeReflector.NodeFilter;
import noderelay.db.NodeReflectorApi;
import noderelay.db.NodeServiceGrpc.NodeServiceImplBase;

public class GrpcHandler extends NodeServiceImplBase {

	private final NodeReflectorApi api;

	public GrpcHandler(NodeReflectorApi api) {
		assert api != null;
		this.api = api;
	}

	@Override
	public void get(Node request, StreamObserver<Node> responseObserver) {
		assert request != null;
		assert responseObserver != null;
		responseObserver.onNext(api.getNode(request));
		responseObserver.onCompleted();
	}

	@Override
	public void search(NodeFilter request, StreamObserver<Node> responseObserver) {
		assert request != null;
		assert responseObserver != null;
		for (Node e : api.searchNode(request)) {
			responseObserver.onNext(e);
		}
		responseObserver.onCompleted();
	}

	@Override
	public void put(Node request, StreamObserver<Node> responseObserver) {
		assert request != null;
		assert responseObserver != null;
		responseObserver.onNext(api.putNode(request));
		responseObserver.onCompleted();
	}

	@Override
	public void delete(Node request, StreamObserver<Node> responseObserver) {
		assert request != null;
		assert responseObserver != null;
		responseObserver.onNext(api.deleteNode(request));
		responseObserver.onCompleted();
	}

}

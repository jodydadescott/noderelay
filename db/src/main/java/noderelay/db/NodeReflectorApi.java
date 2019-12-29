package noderelay.db;

import java.util.Collection;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import com.hazelcast.core.IMap;

import noderelay.db.NodeReflector.Node;
import noderelay.db.NodeReflector.NodeFilter;
import us.jodyscott.commonutils.FString;

public class NodeReflectorApi {

	// ========================================================================

	private static final Logger LOG = LoggerFactory.getLogger(NodeReflectorApi.class);

	private static final String EMPTY_STRING = new String();

	public static String protoToJson(MessageOrBuilder m) {
		if (m == null) {
			return null;
		}
		try {
			return JsonFormat.printer().omittingInsignificantWhitespace().print(m);
		} catch (InvalidProtocolBufferException e) {
			// This is never expected
			throw new AssertionError(e);
		}
	}

	public static Node.Builder jsonToNode(String json) {
		if (json == null) {
			return null;
		}
		Node.Builder result = Node.newBuilder();
		try {
			JsonFormat.parser().ignoringUnknownFields().merge(json, result);
		} catch (InvalidProtocolBufferException e) {
			throw new IllegalArgumentException(e);
		}
		return result;
	}

	public static NodeFilter.Builder jsonToNodeFilter(String json) {
		if (json == null) {
			return null;
		}
		NodeFilter.Builder result = NodeFilter.newBuilder();
		try {
			JsonFormat.parser().ignoringUnknownFields().merge(json, result);
		} catch (InvalidProtocolBufferException e) {
			throw new IllegalArgumentException(e);
		}
		return result;
	}

	// ========================================================================

	private IMap<String, Node> map;

	NodeReflectorApi(DatabaseServer databaseServer) {
		LOG.trace(EMPTY_STRING);
		assert databaseServer != null;
		this.map = databaseServer.getMap(getClass().getSimpleName() + ":this.map");
	}

	public Node getNode(Node node) {

		if (node == null)
			throw new IllegalArgumentException(FString.format("{} is null", Node.class.getSimpleName()));

		if (node.getSecret().equals(EMPTY_STRING))
			throw new IllegalArgumentException(FString.format("secret is required for {}", Node.class.getSimpleName()));

		if (node.getUserdata().equals(EMPTY_STRING))
			throw new IllegalArgumentException(
					FString.format("userdata is required for {}", Node.class.getSimpleName()));

		Node result = map.get(node.getUserdata() + node.getMetadata());

		if (result == null) {
			result = Node.newBuilder().build();
		}

		return result;
	}

	public Collection<Node> searchNode(NodeFilter nodeFilter) {

		if (nodeFilter == null)
			throw new IllegalArgumentException(FString.format("{} is null", NodeFilter.class.getSimpleName()));

		if (nodeFilter.getSecret().equals(EMPTY_STRING))
			throw new IllegalArgumentException(
					FString.format("secret is required for {}", NodeFilter.class.getSimpleName()));

		Collection<Node> results = new HashSet<>();
		NodeMatcher nodeMatcher = new NodeMatcher(nodeFilter);

		for (Node n : map.values()) {
			if (nodeFilter.getSecret().equals(n.getSecret())) {
				if (nodeMatcher.nodeMatch(n)) {
					results.add(n);
				}
			}
		}
		return results;
	}

	public Node putNode(Node node) {

		if (node == null)
			throw new IllegalArgumentException(FString.format("{} is null", Node.class.getSimpleName()));

		if (node.getSecret().equals(EMPTY_STRING))
			throw new IllegalArgumentException(FString.format("secret is required for {}", Node.class.getSimpleName()));

		if (node.getUserdata().equals(EMPTY_STRING))
			throw new IllegalArgumentException(
					FString.format("userdata is required for {}", Node.class.getSimpleName()));

		Node.Builder newNodeBuilder = node.toBuilder();

		Node newNode = newNodeBuilder.build();
		Node oldNode = map.put(node.getUserdata(), newNode);

		if (oldNode == null) {
			LOG.info("Install {} : installed : {}", Node.class.getSimpleName(), newNode);
		} else {
			if (oldNode.equals(newNode)) {
				LOG.info("Install {} : updated (TTL Only) : {}", Node.class.getSimpleName(), newNode);
			} else {
				LOG.info("Install {} : updated : {}", Node.class.getSimpleName(), newNode);
			}
		}

		return newNode;
	}

	public Node deleteNode(Node node) {
		if (node == null)
			throw new IllegalArgumentException(FString.format("{} is null", Node.class.getSimpleName()));

		if (node.getSecret().equals(EMPTY_STRING))
			throw new IllegalArgumentException(FString.format("secret is required for {}", Node.class.getSimpleName()));

		if (node.getUserdata().equals(EMPTY_STRING))
			throw new IllegalArgumentException(
					FString.format("userdata is required for {}", Node.class.getSimpleName()));

		Node removeNode = map.remove(node.getUserdata());
		Node.Builder newNodeBuilder = null;

		if (removeNode == null) {
			LOG.info("Delete {} : not found : {}", Node.class.getSimpleName(), node);
			newNodeBuilder = Node.newBuilder();
		} else {
			LOG.info("Delete {} : deleted : {}", Node.class.getSimpleName(), removeNode);
			newNodeBuilder = removeNode.toBuilder();
		}

		return newNodeBuilder.build();
	}

	public void shutdown() {

	}

}

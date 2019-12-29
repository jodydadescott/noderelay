package noderelay.server.rest;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import noderelay.db.NodeReflectorApi;
import spark.Request;
import spark.Spark;
import noderelay.db.NodeReflector.Node;
import noderelay.db.NodeReflector.NodeFilter;
import us.jodyscott.commonutils.FString;

public class RestServer {

	private static final Logger LOG = LoggerFactory.getLogger(RestServer.class);

	private static final int HTTP_CLIENT_FAIL = 401;

	private static final String INDEX_HTML = "<html><a href=\"https://github.com/aporeto-se/noderelaym\">API Documentation</a></html>";

	public RestServer(RestConfig restConfig, NodeReflectorApi api) {

		LOG.info("Starting");

		assert restConfig != null;
		assert api != null;

		Spark.port(restConfig.getPort());

		Spark.notFound((req, res) -> {
			res.type("application/json");
			res.status(HTTP_CLIENT_FAIL);
			JsonException e = JsonException.generic(FString.format("Endpoint {} not found", req.pathInfo()));
			return e.toString();
		});

		Spark.exception(Exception.class, (e, req, res) -> {
			res.type("application/json");
			res.status(HTTP_CLIENT_FAIL);
			res.body(e.toString());
		});

		Spark.exception(NullPointerException.class, (e, req, res) -> {
			res.type("application/json");
			res.status(HTTP_CLIENT_FAIL);
			res.body(e.toString());
		});

		// ----------------------------------------------------------

		get("/", (req, res) -> {
			res.type("text/html");
			return INDEX_HTML;
		});

		get("/get", (req, res) -> {
			res.type("application/json");
			Node node = api.getNode(jsonToNode(req));
			return NodeReflectorApi.protoToJson(node);
		});

		get("/search", (req, res) -> {
			res.type("application/json");
			return setToSb(api.searchNode(jsonToNodeFilter(req))).toString();
		});

		post("/get", (req, res) -> {
			res.type("application/json");
			Node node = api.getNode(jsonToNode(req));
			return NodeReflectorApi.protoToJson(node);
		});

		post("/search", (req, res) -> {
			res.type("application/json");
			return setToSb(api.searchNode(jsonToNodeFilter(req))).toString();
		});

		post("/put", (req, res) -> {
			res.type("application/json");
			Node node = jsonToNode(req);
			node = api.putNode(node);
			return NodeReflectorApi.protoToJson(node);
		});

		post("/delete", (req, res) -> {
			res.type("application/json");
			Node node = jsonToNode(req);
			node = api.deleteNode(node);
			return NodeReflectorApi.protoToJson(node);
		});

		LOG.info("Started on port {}", restConfig.getPort());
	}

	public void shutdown() {
		LOG.info("Shutting down");
		Spark.stop();
	}

	private Node jsonToNode(Request req) throws JsonException {
		assert req != null;
		try {
			return NodeReflectorApi.jsonToNode(req.body()).build();
		} catch (Throwable t) {
			throw JsonException.generic("Object malformed");
		}
	}

	private NodeFilter jsonToNodeFilter(Request req) throws JsonException {
		assert req != null;
		try {
			return NodeReflectorApi.jsonToNodeFilter(req.body()).build();
		} catch (Throwable t) {
			throw JsonException.generic("Object malformed");
		}
	}

	private StringBuilder setToSb(Collection<Node> nodes) {
		assert nodes != null;

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		boolean flag = true;
		for (Node e : nodes) {
			if (flag) {
				flag = false;
				sb.append(NodeReflectorApi.protoToJson(e));
			} else {
				sb.append(",");
				sb.append(NodeReflectorApi.protoToJson(e));
			}
		}
		sb.append("]");
		return sb;
	}

}

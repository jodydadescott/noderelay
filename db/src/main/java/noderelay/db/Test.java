package noderelay.db;

import noderelay.db.NodeReflector.Node;
import noderelay.db.NodeReflector.NodeFilter;

public class Test {

	public static void main(String[] args) {

		Node a = Node.newBuilder().setUserdata("abc123").setPod("apple").build();
		Node b = Node.newBuilder().setUserdata("abc123").setPod("orange").build();
		Node c = Node.newBuilder().setUserdata("xyz123").setPod("apple").build();
		Node d = Node.newBuilder().setUserdata("xyz123").setPod("orange").build();

		NodeFilter n = NodeFilter.newBuilder().setUserdata("abc123").setNegatepod("apple").build();

		NodeMatcher nodeMatcher = new NodeMatcher(n);

		print(nodeMatcher.nodeMatch(a));
		print(nodeMatcher.nodeMatch(b));
		print(nodeMatcher.nodeMatch(c));
		print(nodeMatcher.nodeMatch(d));

	}

	private static void print(Object o) {
		System.out.println(o);
	}

}

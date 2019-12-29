package noderelay.db;

import java.util.regex.Pattern;

import noderelay.db.NodeReflector.Node;
import noderelay.db.NodeReflector.NodeFilter;

public class NodeMatcher {

	private static final String EMPTY_STRING = new String();

	private Pattern matchUserdataPattern;
	private Pattern matchMetadataPattern;
	private Pattern matchNamespacePattern;
	private Pattern matchPodPattern;

	private Pattern negateUserdataPattern;
	private Pattern negateMetadataPattern;
	private Pattern negateNamespacePattern;
	private Pattern negatePodPattern;

	NodeMatcher(NodeFilter pattern) {
		assert pattern != null;

		if (!pattern.getUserdata().equals(EMPTY_STRING)) {
			this.matchUserdataPattern = Pattern.compile(pattern.getUserdata());
		}

		if (!pattern.getMetadata().equals(EMPTY_STRING)) {
			this.matchMetadataPattern = Pattern.compile(pattern.getMetadata());
		}

		if (!pattern.getNamespace().equals(EMPTY_STRING)) {
			this.matchNamespacePattern = Pattern.compile(pattern.getNamespace());
		}

		if (!pattern.getPod().equals(EMPTY_STRING)) {
			this.matchPodPattern = Pattern.compile(pattern.getPod());
		}

		if (!pattern.getNegateuserdata().equals(EMPTY_STRING)) {
			this.negateUserdataPattern = Pattern.compile(pattern.getNegateuserdata());
		}

		if (!pattern.getNegatemetadata().equals(EMPTY_STRING)) {
			this.negateMetadataPattern = Pattern.compile(pattern.getNegatemetadata());

		}

		if (!pattern.getNegatenamespace().equals(EMPTY_STRING)) {
			this.negateNamespacePattern = Pattern.compile(pattern.getNegatenamespace());

		}

		if (!pattern.getNegatepod().equals(EMPTY_STRING)) {
			this.negatePodPattern = Pattern.compile(pattern.getNegatepod());
		}

	}

	public boolean nodeMatch(Node node) {
		assert node != null;

		if (!matchUserdata(node))
			return false;

		if (!matchMetadata(node))
			return false;

		if (!matchNamespace(node))
			return false;

		if (!matchPod(node))
			return false;

		if (!negateUserdata(node))
			return false;

		if (!negateMetadata(node))
			return false;

		if (!negateNamespace(node))
			return false;

		if (!negatePod(node))
			return false;

		return true;
	}

	private boolean matchUserdata(Node node) {
		assert node != null;

		if (matchUserdataPattern == null)
			return true;

		if (node.getUserdata().equals(EMPTY_STRING))
			return true;

		return matchUserdataPattern.matcher(node.getUserdata()).find();
	}

	private boolean matchMetadata(Node node) {
		assert node != null;

		if (matchMetadataPattern == null)
			return true;

		if (node.getMetadata().equals(EMPTY_STRING))
			return true;

		return matchMetadataPattern.matcher(node.getMetadata()).find();
	}

	private boolean matchNamespace(Node node) {
		assert node != null;

		if (matchNamespacePattern == null)
			return true;

		if (node.getNamespace().equals(EMPTY_STRING))
			return true;

		return matchNamespacePattern.matcher(node.getNamespace()).find();
	}

	private boolean matchPod(Node node) {
		assert node != null;

		if (matchPodPattern == null)
			return true;

		if (node.getPod().equals(EMPTY_STRING))
			return true;

		return matchPodPattern.matcher(node.getPod()).find();
	}

	private boolean negateUserdata(Node node) {
		assert node != null;

		if (negateUserdataPattern == null)
			return true;

		if (node.getUserdata().equals(EMPTY_STRING))
			return true;

		return !negateUserdataPattern.matcher(node.getUserdata()).find();
	}

	private boolean negateMetadata(Node node) {
		assert node != null;

		if (negateMetadataPattern == null)
			return true;

		if (node.getMetadata().equals(EMPTY_STRING))
			return true;

		return !negateMetadataPattern.matcher(node.getMetadata()).find();
	}

	private boolean negateNamespace(Node node) {
		assert node != null;

		if (negateMetadataPattern == null)
			return true;

		if (node.getNamespace().equals(EMPTY_STRING))
			return true;

		return !negateNamespacePattern.matcher(node.getNamespace()).find();
	}

	private boolean negatePod(Node node) {
		assert node != null;

		if (negatePodPattern == null)
			return true;

		if (node.getPod().equals(EMPTY_STRING))
			return true;

		return !negatePodPattern.matcher(node.getPod()).find();
	}

}

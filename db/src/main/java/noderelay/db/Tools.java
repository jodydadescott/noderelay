package noderelay.db;

public class Tools {

	public static void nullCheck(String field, Object o) {
		if (o == null) {
			throw new NullPointerException(field + " is null");
		}
	}

}

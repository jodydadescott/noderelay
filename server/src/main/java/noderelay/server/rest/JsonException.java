package noderelay.server.rest;

public class JsonException extends Exception {

	private static final long serialVersionUID = 1L;

	public final static int GENERIC_EXCEPTION = 1;

	public static JsonException generic(String message) {
		return new JsonException(GENERIC_EXCEPTION, message);
	}

	public static JsonException generic(Exception e) {
		return new JsonException(GENERIC_EXCEPTION, e.getMessage());
	}

	private final String message;
	private final int code;

	private JsonException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "{\"message\":\"" + message + "\",\"code\":" + code + "}";
	}

}

package noderelay.server;

public class JTest {

	public static void main(String[] args) {

		String pattern = "1.1.1.1:9000";
		// String pattern = ":9000";
		// String pattern = "1.1.1.1";
		// String pattern = "1.1.1.1:AB";

		String hostnamePort = String.valueOf(pattern);

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
		} else {

		}

		if (hostname == null && port == null) {
			System.out.println("hostnamePort format is invalid. expected format is hostname:port");
		} else if (hostname == null) {
			System.out.println("hostnamePort format is invalid. expected format is hostname:port (missing hostname)");
		} else if (port == null) {
			System.out.println(
					"hostnamePort format is invalid. expected format is hostname:port (missing or invalid port)");
		} else {
			System.out.println("hostname " + hostname);
			System.out.println("port " + port);
		}

	}

}

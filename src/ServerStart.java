
import java.io.IOException;
import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;


public class ServerStart {

	HttpServer server;

	public static void main(String[] args) {
		System.out.println("POS Web Server is starting .... ");

		ServerStart serverStart = new ServerStart();
		serverStart.init();
	}

	public void init() {
		try {
			server = HttpServer.create(new InetSocketAddress("localhost", 3000), 0);
			server.createContext("/posapi", new Api());
			server.start();

			System.out.println("Server started on port 3000");
		} catch(IOException ex) {
			System.out.println("IO Exception on web server " + ex);
		}
	}

}

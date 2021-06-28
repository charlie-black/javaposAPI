
import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public class Api implements HttpHandler {

	@Override    
	public void handle(HttpExchange httpExchange) {
		System.out.println("Reached handle");

		String sInput = httpExchange.getRequestURI().toString();
		System.out.println(sInput);

		handleResponse(httpExchange);
	}


	private void handleResponse(HttpExchange httpExchange) {
		DbConnection dbConnection = new DbConnection();
		dbConnection.connect();
		String resp = dbConnection.readItems();
		dbConnection.close();


		try {
			OutputStream outputStream = httpExchange.getResponseBody();
			StringBuilder htmlBuilder = new StringBuilder();
			htmlBuilder.append(resp);

			// encode HTML content 
			String htmlResponse = htmlBuilder.toString();

			// this line is a must
			httpExchange.sendResponseHeaders(200, htmlResponse.length());

			outputStream.write(htmlResponse.getBytes());
			outputStream.flush();
			outputStream.close();
		} catch(IOException ex) {
			System.out.println("IO Error on server response " + ex);
		}
	}

}



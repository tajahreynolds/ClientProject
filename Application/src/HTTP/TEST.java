// CSE 383 - E  HTTP Server Assignment  Tingzhao Wang wangt24

package HTTP;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TEST {

	public static void main(String[] args) {
		try {
			int port = 4444;
			ServerSocket serverSocket;
			serverSocket = new ServerSocket(port);
			System.err.println("Server listening on the port: " + port);

			// repeatedly wait for connections, and process
			while (true) {
				Socket clientSocket = serverSocket.accept();
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
				Date date = new Date();  
				String message = "Hello from wangt24 - the time is " + date.toString();

				// send header and content to the client
				out.write("HTTP/1.0 200 OK\r\n");
				out.write("Content-Type: text/plain\r\n");
				out.write("Connection: close\r\n");
				out.write("X-Powered-By: Tingzhao Wang\r\n");
				out.write("Content-Length: " + message.length() + "\r\n");
				out.write("\r\n");
				out.write(message);
				out.flush();

				out.close();
				clientSocket.close();
			}
		} catch (IOException e) {
			System.err.println("Error occurs during listening");
			System.err.println(e.toString());
		}
	}
}

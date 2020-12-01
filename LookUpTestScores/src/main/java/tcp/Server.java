package tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import connection.ConfigurationProvider;
import connection.ConfigurationProviderImpl;
import connection.ConnectionManager;
import connection.ConnectionManagerImpl;

public class Server {
	private ServerSocket serverSocket = null;
	private List<Socket> listSockets = new ArrayList<>();
	private Socket socket = null;
	private final ConfigurationProvider provider;
	private final Properties pro;
	private final int port;

	private static final ConnectionManager con;

	static {
		con = new ConnectionManagerImpl();
	}

	public Server() {
		provider = new ConfigurationProviderImpl();
		pro = provider.getProperties();
		port = Integer.parseInt(pro.getProperty("PORT"));

		try {
			System.out.println("Binding to port " + port + ", please wait  ...");
			serverSocket = new ServerSocket(port);
			System.out.println("Server started: " + serverSocket);
			System.out.println("Waiting for a client ...");
			System.out.println("========================");

			while (true) {
				try {
					socket = serverSocket.accept();
					listSockets.add(socket);
					new Thread(() -> {
						int location = listSockets.size();
						System.out.println("- Client[" + location + "] accepted: " + socket);
						System.out.println("=> [Number of Clients: " + location + "]");
						new ServerHandler(socket, location);
					}).start();
				} catch (IOException e) {
					System.err.println("1. Connection Error: " + e);
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		con.getConnection();
		new Server();
	}
}
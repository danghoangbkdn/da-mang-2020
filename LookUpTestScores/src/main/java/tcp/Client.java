package tcp;

import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

import javax.swing.JOptionPane;

import connection.ConfigurationProvider;
import connection.ConfigurationProviderImpl;
import view.FrameClient;

public class Client {
	private final ConfigurationProvider provider;
	private final Properties pro;
	private final String ip;
	private final int port;
	private Socket socket = null;

	public Client() {
		provider = new ConfigurationProviderImpl();
		pro = provider.getProperties();
		ip = pro.getProperty("IP");
		port = Integer.parseInt(pro.getProperty("PORT"));

		try {
			socket = new Socket(ip, port);
			System.out.println("Connected: " + socket);
			new FrameClient(socket).setVisible(true);
		} catch (IOException ie) {
			JOptionPane.showConfirmDialog(null, "Could not connect to the Server!", "Connection Failed",
					JOptionPane.CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
			System.out.println("Can't connect to server");
		}
	}

	public static void main(String[] args) {
		new Client();
	}
}
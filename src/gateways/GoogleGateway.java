package gateways;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class GoogleGateway implements IConexionGateway {

	private String ip = "0.0.0.0";
	private int port = 35601;

	@Override
	public boolean login(String email) {
		boolean dev = false;
		// TODO Auto-generated method stub
		// Declaration of the socket to send/receive information to/from the server (an
		// IP and a Port are needed)
		try (Socket tcpSocket = new Socket(this.ip, this.port);
				// Streams to send and receive information are created from the Socket
				DataInputStream in = new DataInputStream(tcpSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(tcpSocket.getOutputStream())) {

			// Send request (a Srting) to the server
			out.writeUTF(email + "#" + "12345");
			// Read response (a String) from the server
			String data = in.readUTF();
			System.out.println(" - TCPSocketClient: Received data from '" + tcpSocket.getInetAddress().getHostAddress()
					+ ":" + tcpSocket.getPort() + "' -> '" + data + "'");
			if (data.equalsIgnoreCase("OK#OK")) {
				dev = true;
			}
		} catch (UnknownHostException e) {
			System.err.println("# TCPSocketClient: Socket error: " + e.getMessage());
		} catch (EOFException e) {
			System.err.println("# TCPSocketClient: EOF error: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("# TCPSocketClient: IO error: " + e.getMessage());
		}

		return dev;
	}

	public static void main(String[] args) {
		GoogleGateway gg = new GoogleGateway();
		gg.login("david.g.p@opendeusto.es");
	}

}

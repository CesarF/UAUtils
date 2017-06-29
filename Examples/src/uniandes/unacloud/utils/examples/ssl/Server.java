package uniandes.unacloud.utils.examples.ssl;

import java.io.File;

import uniandes.unacloud.utils.security.KeyManager;
import uniandes.unacloud.utils.security.net.SSLUnaServerClientSocket;
import uniandes.unacloud.utils.security.net.SSLUnaServerSocket;

public class Server extends Thread {
	
	@Override
	public void run() {
		try {
			letsdoit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void letsdoit() throws Exception {
		KeyManager.generateKeyStore("RSA", "unacloud", "server.jks", "uniandes", "unacloud", "uniandes", "Bogotá", "Colombia", "FFFFFF");
		KeyManager.generatePublicKey("server.jks", "unacloud", "UnaCloudPublicKey2.cer", "FFFFFF");
		SSLUnaServerSocket serverSocket = new SSLUnaServerSocket(10000, "JKS", "server.jks", "FFFFFF");
		SSLUnaServerClientSocket ssl = serverSocket.acceptClient();		
		ssl.write("Hello I am Server");
		System.out.println(ssl.read());
		ssl.sendFile(new File("path_to_file"), 10001);
		ssl.close();
		serverSocket.close();
		
	}

}

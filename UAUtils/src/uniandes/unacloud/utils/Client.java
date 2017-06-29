package uniandes.unacloud.utils;

import java.io.File;

import uniandes.unacloud.utils.security.KeyManager;
import uniandes.unacloud.utils.security.net.SSLUnaClientSocket;

public class Client extends Thread {
	
	@Override
	public void run() {
		try {
			hagale();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void hagale() throws Exception {
		KeyManager.generateKeyStore("RSA", "unacloud", "client.jks", "uniandes", "unacloud", "uniandes", "Bogotá", "Colombia", "FFFFFF");
		Thread.sleep(5000);
		KeyManager.addTrustedCerts("unacloud_server", "UnaCloudPublicKey.cer", "client.jks", "FFFFFF");
		SSLUnaClientSocket ssl = new SSLUnaClientSocket(10000, "157.253.195.22", "JKS", "client.jks", "FFFFFF");
		System.out.println(ssl.read());
		ssl.write("Como vas soy cliente");
		File f = ssl.readFile(10001, "");
		f.createNewFile();
		ssl.close();
	}

}

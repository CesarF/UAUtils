package uniandes.unacloud.utils;

public class Test {
	
	public static void main(String[] args) {
		Server sl = new Server();
		Client cl = new Client();
		sl.start();
		cl.start();
	}

}

package Socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Servidor {
	
	public static Vector usuarios = new Vector();
	
	public static void main(String args[]) {
		
		ServerSocket servidorSocket = null;
		
		try {
			servidorSocket = new ServerSocket(8000);
			
			while (true) {
				Socket cliente = servidorSocket.accept();
				Flujo f = new Flujo(cliente);
				Thread t = new Thread(f);
				t.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

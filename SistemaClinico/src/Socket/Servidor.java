package Socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	static ObjectInputStream Lectura;
	static FileOutputStream respaldoServidor;
	static ObjectOutputStream guardandoRespaldo;
	
	public static void main(String args[]) {
		
		ServerSocket servidorSocket = null;
		
		try {
			//Creacion de servidor
			servidorSocket = new ServerSocket(8000);
			
			while (true) {
				Socket cliente = servidorSocket.accept();
				Lectura = new ObjectInputStream((cliente.getInputStream()));
				respaldoServidor = new FileOutputStream("Respaldo/SistemaClinicoRespaldo.dat");
				guardandoRespaldo = new ObjectOutputStream(respaldoServidor);
				
				guardandoRespaldo.writeObject(Lectura.readObject());
				
				guardandoRespaldo.close();
			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

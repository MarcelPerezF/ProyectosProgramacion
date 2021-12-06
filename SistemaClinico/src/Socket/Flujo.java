package Socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Enumeration;

public class Flujo extends Thread{
	Socket hiloCliente;
	ObjectInputStream Lectura;
	ObjectOutputStream Escritura;
	
	public Flujo(Socket cliente) {
		
		hiloCliente = cliente;
		
		try {
			Escritura = new ObjectOutputStream(hiloCliente.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
	
	public void run() {
		
		Servidor.usuarios.add((Object)this);
		
		while(true) {
			
			try {
				Lectura = new ObjectInputStream(hiloCliente.getInputStream());
				
				int cantidadUsuarios = Lectura.readInt();
				broadCastCantidadObjectos(cantidadUsuarios);
				for (int i = 0; i < cantidadUsuarios; i++) {
					Object usuario = Lectura.readObject();
					broadCastObjectos(usuario);
				}
				
				int cantidadVacunas = Lectura.readInt();
				broadCastCantidadObjectos(cantidadVacunas);
				for (int i = 0; i < cantidadVacunas; i++) {
					Object vacuna = Lectura.readObject();
					broadCastObjectos(vacuna);
				}
				
				int cantidadEnfermedades= Lectura.readInt();
				broadCastCantidadObjectos(cantidadEnfermedades);
				for (int i = 0; i < cantidadEnfermedades; i++) {
					Object enfermedad = Lectura.readObject();
					broadCastObjectos(enfermedad);
				}
				
				int cantidadCitasMedicas = Lectura.readInt();
				broadCastCantidadObjectos(cantidadCitasMedicas);
				for (int i = 0; i < cantidadCitasMedicas; i++) {
					Object citamedica = Lectura.readObject();
					broadCastObjectos(citamedica);
				}
				
				int cantidadPaciente = Lectura.readInt();
				broadCastCantidadObjectos(cantidadPaciente);
				for (int i = 0; i < cantidadPaciente; i++) {
					Object paciente = Lectura.readObject();
					broadCastObjectos(paciente);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	public void broadCastCantidadObjectos(int cantidadObjetos) {		
		synchronized (Servidor.usuarios) {			
			Enumeration e = Servidor.usuarios.elements();
			while(e.hasMoreElements()) {
				
				Flujo f = (Flujo)e.nextElement();
				synchronized (f.Escritura) {
					try {
						f.Escritura.writeInt(cantidadObjetos);
					} catch (IOException e1) {
						System.out.println("Error: "+e1.getMessage());
					}					
				}				
			}			
		}		
	}
	
	public void broadCastObjectos(Object Objetos) {		
		synchronized (Servidor.usuarios) {			
			Enumeration e = Servidor.usuarios.elements();
			while(e.hasMoreElements()) {				
				Flujo f = (Flujo)e.nextElement();
				synchronized (f.Escritura) {
					try {
						f.Escritura.writeObject(Objetos);
					} catch (IOException e1) {
						System.out.println("Error: "+e1.getMessage());
					}					
				}				
			}			
		}		
	}
	
	

}

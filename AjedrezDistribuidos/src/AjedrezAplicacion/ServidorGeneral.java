package AjedrezAplicacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorGeneral {
	public static void main(String[] args) {

		int numPet = 0;
		HashMap<String, Integer> listaUsuarios = new HashMap<String, Integer>();

		ExecutorService pool = Executors.newCachedThreadPool();
		try (ServerSocket ss = new ServerSocket(6666)) {
			while (true) {
				try {

					Socket s = ss.accept();
					numPet++;
					System.out.println("Peticion numero = " + numPet);

					if (!listaUsuarios.containsKey(s.getInetAddress().getHostAddress())) {
						listaUsuarios.put(s.getInetAddress().getHostAddress(), s.getPort());
					}

					AtenderJugador hilo = new AtenderJugador(s, listaUsuarios);
					pool.execute(hilo);
					System.out.println("Final peticion " + numPet);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
	}
}

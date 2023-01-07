// CREADO POR: SARA CAMISÓN PERAITA Y FIDEL RUIZ ALCORTA.

package AjedrezAplicacion;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorGeneral {
	public static void main(String[] args) {

		HashMap<String, Integer> listaUsuarios = new HashMap<String, Integer>();

		ExecutorService pool = Executors.newCachedThreadPool();
		try (ServerSocket ss = new ServerSocket(6666)) {
			while (true) {
				try {

					Socket s = ss.accept();
					DataInputStream dis = new DataInputStream(s.getInputStream());
					int puerto = dis.readInt();
					if (!listaUsuarios.containsKey(s.getInetAddress().getHostAddress())) {
						listaUsuarios.put(s.getInetAddress().getHostAddress(), puerto);
					}

					Atender hilo = new Atender(s, listaUsuarios);
					pool.execute(hilo);

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

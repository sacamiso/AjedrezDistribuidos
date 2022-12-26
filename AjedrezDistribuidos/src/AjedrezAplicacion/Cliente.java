package AjedrezAplicacion;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Cliente {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce tu puerto");
		int puerto1 = entrada.nextInt();

		try (Socket s = new Socket("localhost", 6666);
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());) {
			try {

				dos.writeInt(puerto1);
				dos.flush();
				ObjectInputStream in = new ObjectInputStream(s.getInputStream()); // Hay que cerrarlo pero no tengo muy
																					// claro como hacerlo, está abierto
																					// ahi porque si no se generaba un
																					// bloqueo
				HashMap<String, Integer> lista = (HashMap<String, Integer>) in.readObject();
				System.out.println(lista.toString());
				s.close();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(
				"Si desea esperar a que alguien le solicite una partida pulse 1 y si desea jugar con un usuario conectado concreto pulse 2: ");
		int opcion = entrada.nextInt();
		TableroIG mioTab = new TableroIG();
		if (opcion == 1) {

			try (ServerSocket ss = new ServerSocket(puerto1);) {
				try {
					Socket s = ss.accept();

					try (ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
							ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream())) {
						boolean partidaTrminada = false;
						TableroIG tablero;
						while (partidaTrminada == false) {
							tablero = (TableroIG) ois.readObject();
							mioTab.actualizarTablero(tablero);
							// Hace cosas
							oos.writeObject(mioTab);
						}

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			ExecutorService pool2 = Executors.newFixedThreadPool(2);
			System.out.println("Introduce la ip con la que se desea conectar: ");
			String ip = entrada.nextLine();
			ip = entrada.nextLine();
			System.out.println("Introduzca su puerto:");
			int puerto = entrada.nextInt();
			try (Socket sMio = new Socket(ip, puerto);
					ObjectInputStream ois = new ObjectInputStream(sMio.getInputStream());
					ObjectOutputStream oos = new ObjectOutputStream(sMio.getOutputStream())) {

				boolean partidaTrminada = false;
				TableroIG tablero;
				while (partidaTrminada == false) {
					// Hace cosas
					oos.writeObject(mioTab);
					tablero = (TableroIG) ois.readObject();
					mioTab.actualizarTablero(tablero);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				pool2.shutdown();
			}

		}
	}
}

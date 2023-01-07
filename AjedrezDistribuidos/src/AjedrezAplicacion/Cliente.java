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

import javax.crypto.spec.RC2ParameterSpec;

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

		if (opcion == 1) {

			try (ServerSocket ss = new ServerSocket(puerto1);) {

				while (true) {
					try (Socket s = ss.accept();) {
						ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
						ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
						boolean partidaTrminada = false;
						TableroIG tablero = new TableroIG();
						TableroIG mioTab = new TableroIG("Negro");
						mioTab.bloquear();
						while (partidaTrminada == false) {

							mioTab.actualizarTablero((TableroIG) ois.readObject());
							if (mioTab.getJaqueMate()) {
								partidaTrminada = true;
							} else {
								mioTab.desbloquear();
								while (mioTab.getTurno().equals("Negro")) {

								}
								mioTab.comprobarJaqueMate();
								mioTab.bloquear();
								oos.writeObject(mioTab);
								oos.flush();
								oos.reset();

								if (mioTab.getJaqueMate()) {
									partidaTrminada = true;
								}
							}
						}
						
						if (mioTab.getGanador() != null && mioTab.getGanador().equals("Negro")) {
							Mate ventana = new Mate(null, true, "ganador");
							ventana.setVisible(true);
						} else {
							Mate ventana = new Mate(null, true, "perdedor");
							ventana.setVisible(true);
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Introduce la ip con la que se desea conectar: ");
			String ip = entrada.nextLine();
			ip = entrada.nextLine();
			System.out.println("Introduzca su puerto:");
			int puerto = entrada.nextInt();
			try (Socket sMio = new Socket(ip, puerto);
					ObjectOutputStream oos = new ObjectOutputStream(sMio.getOutputStream());
					ObjectInputStream ois = new ObjectInputStream(sMio.getInputStream());) {

				boolean partidaTrminada = false;
				TableroIG tablero = new TableroIG();
				TableroIG mioTab = new TableroIG("Blanco");
				boolean turno = true;
				while (partidaTrminada == false) {

					turno = true;
					while (turno) {
						turno = mioTab.getTurno().equals("Blanco");
						System.out.println();
					}
					mioTab.comprobarJaqueMate();
					mioTab.bloquear();
					oos.writeObject(mioTab);
					oos.flush();
					oos.reset();

					if (mioTab.getJaqueMate()) {
						partidaTrminada = true;
					} else {
						tablero = (TableroIG) ois.readObject();
						mioTab.actualizarTablero(tablero);
						if (mioTab.getJaqueMate()) {
							partidaTrminada = true;
						} else {
							tablero = null;
							mioTab.desbloquear();
						}

					}

				}
				
				if (mioTab.getGanador() != null && mioTab.getGanador().equals("Blanco")) {
					Mate ventana = new Mate(null, true, "ganador");
					ventana.setVisible(true);
				} else {
					Mate ventana = new Mate(null, true, "perdedor");
					ventana.setVisible(true);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}
}

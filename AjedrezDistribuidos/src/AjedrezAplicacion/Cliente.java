package AjedrezAplicacion;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.HashMap;

public class Cliente {
	public static void main(String[] args) {
		try (Socket s = new Socket("localhost", 6666);
				ObjectInputStream in = new ObjectInputStream(s.getInputStream());) {
			try {
				HashMap<String, Integer> lista = (HashMap<String, Integer>) in.readObject();
				s.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

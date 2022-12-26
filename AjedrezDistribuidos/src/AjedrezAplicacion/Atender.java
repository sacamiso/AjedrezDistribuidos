package AjedrezAplicacion;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class Atender implements Runnable {
    private Socket s;
    private HashMap<String, Integer> m;

    public Atender(Socket s, HashMap<String, Integer> m) {
        this.s = s;
        this.m = m;
    }

    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream())) {
            out.writeObject(this.m);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

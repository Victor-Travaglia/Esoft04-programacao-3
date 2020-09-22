package servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class MainServidor {

    public static void main(String[] args) throws Exception {

        System.out.println("---- Iniciando Servidor ----");

        ServerSocket servidor = new ServerSocket(12345);

        while (true) {
            Socket socket = servidor.accept();
            System.out.println("Aceitando novo cliente na porta " + socket.getPort());

            Task task = new Task(socket);
            Thread thread = new Thread(task);
            thread.start();
        }
    }
}

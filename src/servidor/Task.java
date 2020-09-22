package servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Task implements Runnable {

    private Socket socket;

    public Task(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            Scanner entradaCliente = new Scanner(socket.getInputStream());

            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            int contagemDePings = 0;
            int contagemDePongs = 0;

            while (entradaCliente.hasNextLine() && !entradaCliente.nextLine().equals("end")) {
                contagemDePings ++;
                contagemDePongs ++;
            }

            saidaCliente.println("Pongs Enviados para o cliente " + contagemDePongs);

            System.out.println("Pings recebidos pelo servidor " + contagemDePings);

            saidaCliente.close();
            entradaCliente.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

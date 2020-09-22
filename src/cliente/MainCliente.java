package cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class MainCliente {

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost", 12345);

        System.out.println("Conexão Estabelecida");

        Thread threadEnviaComando = new Thread(() -> {

            try {
                System.out.println("Enviando 1000 Pings");

                PrintStream saida = new PrintStream(socket.getOutputStream());

                for (int i = 0; i <= 999; i++) {
                    saida.println("ping");
                }

                saida.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Thread threadRecebeResposta = new Thread(() -> {

            try {
                System.out.println("Recebendo dados do servidor");
                Scanner respostaServidor = new Scanner(socket.getInputStream());

                while (respostaServidor.hasNextLine()) {

                    String linha = respostaServidor.nextLine();
                    System.out.println(linha);
                }

                respostaServidor.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        threadRecebeResposta.start();
        threadEnviaComando.start();

        threadEnviaComando.join();

        System.out.println("Fechando o socket do cliente");

        socket.close();

    }
}

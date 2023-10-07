package ranaufal041023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    private static final int server_port = 12345;
    private static final String server_address = "localhost";

    public static void main(String[] args) {
        
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan Nama Anda : ");
        String username = scanner.nextLine();
        try (Socket socket = new Socket(server_address, server_port)) {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Connect ke server, silahkan kirim pesan(exit untuk Keluar)");

            new PesanServer(socket, in).start();

            String message;
            while (true) {
                System.out.print(username + " : ");
                message = scanner.nextLine();
                message = username + " : " + message;

                out.println(message);
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class PesanServer extends Thread {
        Socket socket;
        BufferedReader in;
        String message;

        public PesanServer(Socket socket, BufferedReader in) {
            this.socket = socket;
            this.in = in;
        }

        @Override
        public void run() {
            try {
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
}

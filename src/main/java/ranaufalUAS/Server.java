/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ranaufalUAS;

/**
 *
 * @author ranaufal
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private List<PrintWriter> clientOutputStreams;
    private JFrame frame;
    private JTextArea textArea;

    public Server() {
        clientOutputStreams = new ArrayList<>();
        createAndShowGUI();
        startServer();
    }

    private void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ClientHandler implements Runnable {
        private Socket clientSocket;
        private BufferedReader reader;

        public ClientHandler(Socket socket) {
            try {
                clientSocket = socket;
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    updateUI("Received: " + message);
                    tellEveryone(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        private void updateUI(String data) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(data + "\n");
        });
    }
    }
    
    

    private void tellEveryone(String message) {
        for (PrintWriter writer : clientOutputStreams) {
            try {
                writer.println(message);
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createAndShowGUI() {
        frame = new JFrame("Chat Server");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Server());
    }
}

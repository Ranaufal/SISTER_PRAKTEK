/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ranaufal251023;

/**
 *
 * @author ranaufal
 */
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class Server {
    private BlockingQueue<String> dataQueue;

    public Server() {
        this.dataQueue = new LinkedBlockingQueue<>();
    }

    public void sendData(String data) {
        try {
            dataQueue.put(data);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void startServer() {
        Thread serverThread = new Thread(() -> {
            while (true) {
                try {
                    String data = dataQueue.take();
                    // Menampilkan data ke GUI
                    updateUI(data);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        serverThread.start();
    }

    private void updateUI(String data) {
        // Simpan data untuk ditampilkan
        SwingUtilities.invokeLater(() -> {
            // Update UI di sini, contoh menampilkan dalam JOptionPane
            JOptionPane.showMessageDialog(null, "Data from server: " + data);
        });
    }
}

public class ChatApp {
    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();

        // Meniru pengiriman data dari server (dapat diganti dengan koneksi nyata ke server)
        for (int i = 0; i < 5; i++) {
            String data = "Data " + i;
            server.sendData(data);
            try {
                Thread.sleep(1000); // Meniru jeda antara data yang dikirim dari server
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // GUI initialization (tidak ada pengaruh langsung pada pengiriman data)
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Server Data Display");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);

            JButton dataButton = new JButton("Send Data to Server");
            dataButton.addActionListener(e -> {
                // Meniru pengiriman data dari pengguna ke server
                String userInput = JOptionPane.showInputDialog("Enter data to send:");
                if (userInput != null && !userInput.isEmpty()) {
                    server.sendData(userInput);
                }
            });

            frame.add(dataButton, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}

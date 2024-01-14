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

public class Client {
    private BufferedReader reader;
    private PrintWriter writer;
    private JFrame frame;
    private JTextArea textArea;
    private JTextField textField;

    public Client() {
        createAndShowGUI();
        connectToServer();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Chat Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textField = new JTextField(20);
        textField.addActionListener(e -> {
            sendMessage(textField.getText());
            textField.setText("");
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(textField, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("127.0.0.1", 12345);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(socket.getOutputStream());
            Thread readerThread = new Thread(new IncomingReader());
            readerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String message) {
        try {
            writer.println(message);
            writer.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    textArea.append(message + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client());
    }
}

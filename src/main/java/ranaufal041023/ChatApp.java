/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ranaufal041023;

/**
 *
 * @author ranaufal
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatApp extends JFrame {
    private JTextArea receivedMessages;
    private JTextArea sentMessages;
    private JButton sendButton;

    public ChatApp() {
        setTitle("Chat App with Auto-Reload");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        receivedMessages = new JTextArea(10, 30);
        receivedMessages.setEditable(false);
        JScrollPane receivedScrollPane = new JScrollPane(receivedMessages);

        sentMessages = new JTextArea(4, 30);
        JScrollPane sentScrollPane = new JScrollPane(sentMessages);

        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.add(receivedScrollPane, BorderLayout.NORTH);
        messagePanel.add(sentScrollPane, BorderLayout.CENTER);
        messagePanel.add(sendButton, BorderLayout.SOUTH);

        add(messagePanel, BorderLayout.CENTER);

        // Timer untuk auto-reload pesan
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadMessages();
            }
        });
        timer.start();
    }

    private void sendMessage() {
        String message = sentMessages.getText();
        receivedMessages.append("You: " + message + "\n");
        sentMessages.setText("");
    }

    private void reloadMessages() {
        // Implementasikan kode untuk memeriksa server dan memuat pesan baru di sini
        // Misalnya, Anda dapat mengambil pesan dari server dan menambahkannya ke receivedMessages
        // Contoh sederhana: 
        // receivedMessages.append("Pengguna Lain: Pesan Baru\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ChatApp().setVisible(true);
            }
        });
    }
}

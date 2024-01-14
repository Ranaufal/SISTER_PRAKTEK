/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ranaufalUAS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

/**
 *
 * @author ranaufal
 */
public class ChatApp extends javax.swing.JFrame {
    private BufferedReader in;
    private PrintWriter out;
    String username = "";    
    int server = 0;

    
    
    public ChatApp() {
        initComponents();
        
        edtMsg.setEnabled(false);
        btnSend.setEnabled(false);
        chatArea.setEditable(false);
        setServer.setEnabled(false);
        btnSetServer.setEnabled(false);
        chatArea.setEnabled(false);
        
        
    }
    
    private void connectToServer() {
        try {
            Socket socket = new Socket("127.0.0.1", server);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            in = new BufferedReader(streamReader);
            out = new PrintWriter(socket.getOutputStream());
            new PesanServer(socket, in).start();
            Thread readerThread = new Thread(new IncomingReader());
            readerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void sendDataToServer(String message){
        
        try {
            out.println(message);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    class IncomingReader implements Runnable {
        public void run() {
            String message;
            try {
                while ((message = in.readLine()) != null) {
                    chatArea.append(message + "\n");
                    System.out.println(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static String ende_krip(String text){
        String hasilEnDe_krip = "";
        
        int key = 2;
        for(int i=0; i<text.length();i++){
            int h = (int) (text.charAt(i));
            char t = (char) (h ^ key);
            hasilEnDe_krip += t;
        }
        
        return hasilEnDe_krip;
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
                    System.out.println("From Server : "+ende_krip(message));
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSend = new javax.swing.JButton();
        setName = new javax.swing.JTextField();
        edtMsg = new javax.swing.JTextField();
        btnSetName = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        setServer = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSetServer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnSetName.setText("Set");
        btnSetName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetNameActionPerformed(evt);
            }
        });

        jLabel1.setText("username :");

        jLabel2.setText("pesan :");

        jLabel3.setFont(new java.awt.Font("Kannada MN", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Chat App Ranaufal Muha");

        chatArea.setColumns(20);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        jLabel4.setText("port :");

        btnSetServer.setText("Set");
        btnSetServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetServerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(edtMsg)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSend))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(setName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetName))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(setServer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSetServer))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSetName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setName, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSetServer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setServer, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        // TODO add your handling code here:
        String data = edtMsg.getText();
        if (!data.isEmpty()) {
            sendDataToServer(username + " : " + ende_krip(data));
            edtMsg.setText("");
        }
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnSetNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetNameActionPerformed
        username = setName.getText();
        if (!username.isEmpty()) {
//            sendDataToServer(username);
            setName.setEnabled(false);
            btnSetName.setEnabled(false);

            setServer.setEnabled(true);
            btnSetServer.setEnabled(true);
        
        }
    }//GEN-LAST:event_btnSetNameActionPerformed

    private void btnSetServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetServerActionPerformed
        // TODO add your handling code here:
        server = Integer.parseInt(setServer.getText());
        if(server != 0){
            setServer.setEnabled(false);
            btnSetServer.setEnabled(false);
            
            edtMsg.setEnabled(true);
            btnSend.setEnabled(true);
            chatArea.setEnabled(true);
            
            connectToServer();
        }
    }//GEN-LAST:event_btnSetServerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ChatApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChatApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChatApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChatApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ChatApp().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnSetName;
    private javax.swing.JButton btnSetServer;
    private javax.swing.JTextArea chatArea;
    private javax.swing.JTextField edtMsg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField setName;
    private javax.swing.JTextField setServer;
    // End of variables declaration//GEN-END:variables
}
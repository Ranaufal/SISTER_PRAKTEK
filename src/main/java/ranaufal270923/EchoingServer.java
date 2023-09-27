/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ranaufal270923;

/**
 *
 * @author ranaufal
 */
import java.net.*;
import java.io.*;

public class EchoingServer {
    public static void main(String[] args){
        ServerSocket server = null;
        Socket client;
        String test;
        try {
            server = new ServerSocket(1234);
        } catch (IOException ie) {      
            System.out.println("Cannot open socket.");
            System.exit(1);
        }
        
        while(true) {
            try {
                client = server.accept();
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);
//                InputStream clientIn = client.getInputStream();
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn)); 
                test = br.readLine();
                System.out.println(test);
                pw.println(test);
                
                
            } catch (IOException ie) {
            } 
        }
    }
}



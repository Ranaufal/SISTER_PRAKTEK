/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ranaufal270923;

import java.net.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import static ranaufal270923.ChatServer.angka;

/**
 *
 * @author ranaufal
 */
public class TrivialServer {
    static String kiriman = "";
    static String jawaban = "";
    static boolean isMenjawab = false;
    static boolean isBertanya = false;
    static int angka = 0;
    public static void main(String[] args) {

        ServerSocket server = null;
        Socket client;
        String test;
        
        try {
            server = new ServerSocket(1234);
        } catch (IOException ie) {
            System.out.println("Cannot open socket.");
            System.exit(1);
        }

        while (true) {
            try {
                client = server.accept();
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);
//                InputStream clientIn = client.getInputStream();
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));
                test = br.readLine();
                response(test);
                System.out.println(angka);
                pw.println(kiriman);

            } catch (IOException ie) {
            }
        }
    }
    
    public static void response(String msg){
        
        if(msg.equals("permintaan")){
            kiriman = pertanyaan();
            isMenjawab = false;
            isBertanya = true;
        }else if(msg.equals("jawaban")){
            jawaban();
            isMenjawab = true;
            isBertanya = false;
        }else if(isBertanya && !isMenjawab){
            kiriman = "SILAHAKAN KETIK = 'jawaban' ";
        }else if (isMenjawab){
            if(jawaban.equals(msg)){
                kiriman = "Kerja Bagus";
            }else{
                kiriman = "Maaf Salah";
            }
            isMenjawab = false;
        }else{
            kiriman = "SILAHAKAN KETIK = 'permintaan' ";
        }
    }
    
    public static String pertanyaan(){
        Random rand = new Random();
        angka = rand.nextInt(5);
        String pertanyaan;
        if(angka == 1){
            pertanyaan = "1#Apa kepanjangan PNP?";
        }else if(angka == 2){
            pertanyaan = "2#Apa itu PC?";
        }else if(angka == 3){
            pertanyaan = "3#Apa itu Speaker?";
        }else if(angka == 4){
            pertanyaan = "4#Apa itu Monitor?";
        }else if(angka == 5){
            pertanyaan = "5#Apa itu Laptop?";
        }else{
            pertanyaan = "6#Jam berapa sekarang?";
        }
        return pertanyaan;
    }
    
    public static void jawaban(){
        Scanner in = new Scanner(System.in);
        kiriman = "Berikan jawabanmu dengan format: <nomor pertanyaanr>#<jawaban anda>";
        if(angka == 1){
            jawaban = "1#Politeknik Negeri Padang";
        }else if(angka == 2){
            jawaban = "2#Personal Computer";
        }else if(angka == 3){
            jawaban = "3#Pengeras Suara";
        }else if(angka == 4){
            jawaban = "4#Untuk Nonton";
        }else if(angka == 5){
            jawaban = "5#Untuk Buat Tugas";
        }else{
            jawaban = "6#jam 4";
        }
        
        
    }
}

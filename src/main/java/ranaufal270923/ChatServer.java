/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ranaufal270923;

import java.net.*;
import java.util.*;

public class ChatServer {
    static int angka;

    public static void main(String args[]) throws Exception {
        MulticastSocket server = new MulticastSocket(1234);
        InetAddress group = InetAddress.getByName("234.5.6.7");
        //getByName – Mengembalikan alamat IP yang diberikan oleh Host
        server.joinGroup(group);
        boolean infinite = true;
        /* Server terus-menerus menerima data dan mencetak mereka */
        while (infinite) {
            byte buf[] = new byte[1024];
            DatagramPacket data = new DatagramPacket(buf,
                    buf.length);
            server.receive(data);
            String msg = new String(data.getData()).trim();
//            System.out.println(msg);
            response(msg);
        }
        server.close();
    }
    
    public static void response(String msg){
        
        if(msg.equals("permintaan")){
            System.out.println(pertanyaan());
        }else if(msg.equals("jawaban")){
            jawaban();
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
        System.out.println("Berikan jawabanmu dengan format: <nomor pertanyaanr>#<jawaban anda>");
        String jawabanClient = "";
        String jawaban;
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
        
        if(jawaban.equals(jawabanClient)){
            System.out.println("Kerja Bagus");
        }else{
            System.out.println("Maaf Salah");
        }
    }
    
}

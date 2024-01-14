/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ranaufal111023;

/**
 *
 * @author ranaufal
 */
public class Enkripsi2 {
    public static void main(String [] args){
        String text = "Selamat Datang";
        String temp = "";
        int key = 2;
        for(int i=0; i<text.length();i++){
            int h = (int) (text.charAt(i));
            char t = (char) (h ^ key);
            temp += t;
        }
        System.out.println("Sebelum : "+text);      
        System.out.println("Sesudah : "+temp);
        
        for(int i=0; i<text.length();i++){
            int h = (int) (temp.charAt(i));
            char t = (char) (h ^ key);
            temp += t;
        }     
        System.out.println("Sesudah : "+temp);
    }
}

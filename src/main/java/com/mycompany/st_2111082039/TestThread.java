/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.st_2111082039;

/**
 *
 * @author ranaufal
 */
public class TestThread {
    public static void main(String[] args){
        System.out.println("Mulai");
        PrintNameThread pnt1 = new PrintNameThread("A");        
        PrintNameThread pnt2 = new PrintNameThread("B");
        PrintNameThread pnt3 = new PrintNameThread("C");
        PrintNameThread pnt4 = new PrintNameThread("D");
        System.out.println("Selesai");
    }
}

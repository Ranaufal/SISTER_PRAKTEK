/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.st_2111082039;

/**
 *
 * @author ranaufal
 */
class PrintNameThread extends Thread {
    PrintNameThread(String name) {
        super(name);
        start(); 
    } 
    
    public void run() {
        String name = getName();
        for (int i = 0; i < 100; i++) {
            System.out.print(name);
        }
    }
}

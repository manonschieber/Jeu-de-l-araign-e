/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Araignée;

import java.awt.EventQueue;
import javax.swing.JFrame; 
import javax.swing.UIManager;
/**
 *
 * @author manonschieber
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        fenetre f = new fenetre();
        f.pack();  //la taille de la fenêtre s'adapte à son contenu
        f.setVisible(true);  //afficher la fenêtre
        f.setSize(500, 500);
        f.setResizable(false); 
        f.setDefaultCloseOperation(JFrame. EXIT_ON_CLOSE);
        f.setLayout(null);
    
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Araignée;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame; 
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author manonschieber
 */
public class fenetre extends JFrame{
    private int nb;
    String croix = "X";   //joueur 1
    String rond = "O";   //joueur 2
    Boolean joueur=true;  //c'est le tour du joueur 1
    JLabel label = new JLabel("C'est le tour du joueur 1");
    
    JButton[][] boutons=new JButton[8][8];
    int[][] tableau=new int[8][8];
    
    public fenetre(){
        this.setTitle("Jeu de l'araignée");
        this.setSize(800,800);
        this.setLayout(null);
        
        int dim= 50;

        for(int i=0; i < 8; i++) {   //on parcourt les lignes 
        for(int j=0; j<8; j++)  {  //on parcourt les colonnes
            tableau[i][j]=0;    //initalement, il n'y a pas de jeton dans le tableau 
            }
        }
        
        //Couleur du joueur
        JLabel joueur1= new JLabel("Joueur 1 : 'X'");
        add(joueur1);  //important 
        joueur1.setBounds(0,0,300,25); 
        joueur1.setFont(new Font(Font.SANS_SERIF,Font.BOLD,12));  
        
        JLabel joueur2= new JLabel("Joueur 2 : 'O'");
        add(joueur2);
        joueur2.setBounds(0,15,300,25); 
        joueur2.setFont(new Font(Font.SANS_SERIF,Font.BOLD,12));  
        
        label.setBounds(200,0,400,25);
        label.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        add(label);
        
        //Grille 
        Grille grid = new Grille();
        grid.setBounds(10,10,452,465);
        add(grid);
        
        for(int i=0; i<8; i++) {   //on parcourt les lignes 
            for(int j=0; j<8; j++)  {  //on parcourt les colonnes
                boutons[i][j] = new JButton();
                boutons[i][j].setBounds((j+1)*dim, (i+1)*dim,dim,dim);
                add(boutons[i][j]);
                boutons[i][j].addActionListener(new TraiteBouton(boutons[i][j]));
            
        }
        
 }
}

    class TraiteBouton implements ActionListener{
        JButton button;
        int i;
        int j;
        int dim = 50;
        int N;
   
        
        private TraiteBouton(JButton b1){
            button=b1;
            Point p = b1.getLocation();
            i=(p.x)/dim-1;
            j=(p.y)/dim-1;
            N=0;
         
        }
            
        @Override
        public void actionPerformed(ActionEvent e) {  //déclenché quand on clique sur un bouton
            N=0;
            //On compte le nombre de jetons dans le tableau
            for(int k=0;k<8;k++){
                for(int l=0;l<8;l++){
                    if (tableau[k][l] != 0){
                        N=N+1;                      
                    }
                }
            }
            
            if(N<6){   //PHASE 1
                
            if (tableau[i][j] != 0){  //il y a un jeton dans cette case
                JFrame f = new JFrame("OptionPane test");
                JOptionPane.showMessageDialog(f, "Il y a déjà un jeton dans cette case !", "Erreur", JOptionPane.WARNING_MESSAGE);
            }
                
            else{  //il n'y a pas de jeton dans cette case
                if (joueur) {   //c'est le tour du joueur 1
                    button.setText(croix);
                    tableau[i][j]=1;
                    joueur= !joueur;   
                    label.setText("C'est le tour du joueur 2");
                    if (victoire(tableau,!joueur)){
                        label.setText("C'est gagné pour le joueur 1!"); }
                }
                else {  //c'est le tour du joueur 2
                    button.setText(rond);
                    tableau[i][j]=2;  
                    joueur= !joueur;
                    label.setText("C'est le tour du joueur 1");
                    if (victoire(tableau,!joueur)){
                        label.setText("C'est gagné pour le joueur 2!"); }
                }           
            }
         
        }
            else {   //PHASE 2
                if (tableau[i][j] == 0){  //il n'y a pas de jeton dans cette case
                    JFrame f = new JFrame("OptionPane test");
                    JOptionPane.showMessageDialog(f, "Il n'y a pas de jeton à déplacer dans cette case !", "Erreur", JOptionPane.WARNING_MESSAGE);}
                
                else{  //il y a bien un jeton dans cette case
                    if(joueur){  //c'est le tour du joueur 1
                        if (tableau[i][j]==2){  //un jeton du joueur 2 est sélectionné
                            JFrame f = new JFrame("OptionPane test");
                            JOptionPane.showMessageDialog(f, "C'est le tour du joueur 1! Veuillez sélectionner une croix.", "Erreur", JOptionPane.WARNING_MESSAGE);
                        }
                        if(tableau[i][j]==1){
                            button.setText("");
                            tableau[i][j]=0;   
                        }
                        if (victoire(tableau,joueur)){
                            label.setText("C'est gagné pour le joueur 1!");
            }
                    }
                    
                    else{  //c'est le tour du joueur 2
                        if (tableau[i][j]==1){  //un jeton du joueur 1 est sélectionné
                            JFrame f = new JFrame("OptionPane test");
                            JOptionPane.showMessageDialog(f, "C'est le tour du joueur 2! Veuillez sélectionner un rond.", "Erreur", JOptionPane.WARNING_MESSAGE);
                    }
                                     
                        else{   //jeton du joueur 2 à déplacer 
                            button.setText("");
                            tableau[i][j]=0; 
                }
                        if (victoire(tableau,joueur)){
                            label.setText("C'est gagné pour le joueur 2!");
            }
                }
                
            }
        }

    }
        public boolean victoire(int[][] tableau,boolean joueur){
            
            //Dans  cette fonction, on va regarder s'il y a trois jetons du même joueur alignés dans la grille (gra e à la fonction compte en dessous)
            
            int jetonjoueur;
            if (joueur){
                jetonjoueur=1;
            }
            else{
                jetonjoueur=2;
            }
            
            for(int a=0;a<8;++a){
                for(int b=0;b<8;++b){
                    int jetonCase=tableau[a][b];
                    
                    if(jetonCase==jetonjoueur){
                        if((compte(tableau,a,b,1,-1) == 3)  || (compte(tableau,a,b,0,1) == 3)  || (compte(tableau,a,b,1,1) == 3)  ||   (compte(tableau,a,b,1,0) == 3) ||   (compte(tableau,a,b,0,-1) == 3)){   
                            return true;
                        }
                        
                }
            }
        }
            return false;}
        
        public int compte(int[][] tableau,int i, int j, int a, int b){
            
            //Dans cette fonction, on compte le nombre de jetons du même joueur qui sont alignés dans la grille
            
            int k=0;
            int ligne = i;
            int colonne = j;
            
            while(tableau[ligne][colonne]==tableau[i][j] && 0<ligne && ligne<8 && 0<colonne && colonne<8) { //tant que la couleur du pion est celle du pion de  départ
                k=k+1; //on a trouvé un autre jeton de la même couleur aligné
                ligne = ligne +a;  //on va dans la direction a
                colonne = colonne +b;}  //on va dans la direction b
            
            return k;
                        
                        
        }
}
}
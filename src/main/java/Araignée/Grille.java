/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Araignée;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 *
 * @author manonschieber
 */
public class Grille extends JComponent{
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g ;
        int CASE_DIM = 50;
        
        g2.setPaint(Color.GRAY);
        g2.setStroke(new BasicStroke(2));
        g2.draw(new Rectangle2D.Double(40,40,8*CASE_DIM,8*CASE_DIM));
        
        //légendes
        int c;  
        for(int i=0; i<8; i++)  {
            c=i+1+'0';
            g2.drawString(""+(char)c, 15, (i+1.5f)*CASE_DIM);
        }
        for(int j=0; j<8; j++)  {
            c='A'+j;
            g2.drawString(""+(char)c, (j+1.5f)*CASE_DIM-10, 35);
            
           }
        g2.dispose();
    }
}

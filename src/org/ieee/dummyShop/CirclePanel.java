package org.ieee.dummyShop;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CirclePanel extends JPanel 
{ 
  public static void main(String[] args) throws Exception 
  { 
      JFrame f = new JFrame(); 

      f.setContentPane(new CirclePanel()); 
      f.setSize(700,500); 
      f.setVisible(true); 
  } 

  public void paint(Graphics g) 
  { 
      super.paint(g); 
      //Draws the line 
      g.drawOval(0,0,this.getWidth(), this.getHeight()); 

      //draws filled circle 
      g.setColor(Color.red);  
      //g.fillOval(0,0,this.getWidth(), this.getHeight()); 
  } 
} 


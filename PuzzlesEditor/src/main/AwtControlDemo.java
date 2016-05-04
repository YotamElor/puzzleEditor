package main;

import java.awt.*;
import java.awt.event.*;

public class AwtControlDemo {
   private Frame mainFrame;
   private Panel controlPanel;

   public AwtControlDemo(){
      prepareGUI();
   }

   public static void main(String[] args){
      AwtControlDemo  awtControlDemo = new AwtControlDemo();
      awtControlDemo.showCanvasDemo();
   }

   private void prepareGUI(){
      mainFrame = new Frame("Java AWT Examples");
      mainFrame.setSize(550,550);
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });
      controlPanel = new Panel();
      controlPanel.setLayout(new FlowLayout());
      mainFrame.add(controlPanel);
      mainFrame.setVisible(true);  
   }

   private void showCanvasDemo(){
      controlPanel.add(new MyCanvas());
      mainFrame.setVisible(true);  
   } 

   class MyCanvas extends Canvas implements MouseListener{
	   Puzzle m_puzzle;
	   public MyCanvas () {
		   setBackground(Color.GRAY);
		   setSize(500,500);
		   m_puzzle = new Puzzle();
		   addMouseListener(this);
	   }
	   public void paint (Graphics g) {
		   m_puzzle.draw(g);
	   }
		@Override
		public void mouseClicked(MouseEvent e) {
			m_puzzle.onTouch(e.getX(), e.getY());
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}
   	}
}
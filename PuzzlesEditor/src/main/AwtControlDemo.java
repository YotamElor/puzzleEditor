package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AwtControlDemo extends JComponent implements MouseListener{
	private Puzzle m_puzzle;
	AwtControlDemo() {
		m_puzzle = new Puzzle();
		addMouseListener(this);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		m_puzzle.draw(g);
	}

	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
	public static void main(String args[]) {
		JFrame mainFrame = new JFrame("Graphics demo");
		mainFrame.getContentPane().add(new AwtControlDemo());
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		m_puzzle.onTouch(e.getX(), e.getY());
		repaint();
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
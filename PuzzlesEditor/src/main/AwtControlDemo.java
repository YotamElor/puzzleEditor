package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AwtControlDemo extends JComponent implements MouseListener, ActionListener{
	private JTextField m_textField;
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
		return new Dimension(500, 550);
	}
	
	public static void main(String args[]) {
		JFrame mainFrame = new JFrame("Graphics demo");
		mainFrame.setSize(500, 550);
		
		AwtControlDemo awtControlDemo = new AwtControlDemo();
		awtControlDemo.setOpaque(true);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setSize(500,50);
		
		JButton loadButton = new JButton("load");
		loadButton.setVerticalTextPosition(AbstractButton.CENTER);
		loadButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		loadButton.setActionCommand("load");
		loadButton.addActionListener(awtControlDemo);
		buttonPanel.add(loadButton);		

		JButton saveButton = new JButton("save");
		saveButton.setVerticalTextPosition(AbstractButton.CENTER);
		saveButton.setHorizontalTextPosition(AbstractButton.LEADING); //aka LEFT, for left-to-right locales
		saveButton.setActionCommand("save");
		saveButton.addActionListener(awtControlDemo);
		buttonPanel.add(saveButton);
		
		JTextField textField = new JTextField(20);
		awtControlDemo.m_textField = textField;
		buttonPanel.add(textField);
		
		mainFrame.getContentPane().add(buttonPanel);
		mainFrame.getContentPane().add(awtControlDemo);
		
        //Display the window.
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
	@Override
	public void actionPerformed(ActionEvent e) {
		String filename = m_textField.getText();
		if ("load".equals(e.getActionCommand())) {
			System.out.println("load "+filename);
			try {
				m_puzzle.save(filename);
			} catch (IOException e1) {
				
			}
		} else if ("save".equals(e.getActionCommand())) {
			System.out.println("save "+filename);
		}
	}
}
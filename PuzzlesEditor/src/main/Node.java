package main;

import java.awt.*;
import java.util.ArrayList;


public class Node {
    private Point m_position;
    private int m_radius;
    public Node(int x, int y){
        m_position = new Point(x,y);
        m_radius = 10;
    }
    public Node(Point position){
        m_position = position.clone();
        m_radius = 10;
    }
    public void draw(Graphics g) {
    	g.setColor(Color.black);
        g.drawOval(m_position.x()-m_radius/2, m_position.y()-m_radius/2, m_radius, m_radius);
    }
    public int x() {
        return m_position.x();
    }
    public int y() {
        return m_position.y();
    }
    public Point position() {
    	return m_position;
    }
    public boolean equals(Node n) {
    	return m_position.equals(n.position());
    }
    public void to_stream(ArrayList<Integer> arr) {
    	arr.add(m_position.x());
    	arr.add(m_position.y());
    }
}

package main;

import java.awt.*;


public class Node {
    private Point m_position;
    private int m_radius;
    public Node(int x, int y){
        m_position = new Point(x,y);
        m_radius = 10;
    }
    public void draw(Graphics g) {
    	g.setColor(Color.black);
        g.drawOval(m_position.x()-m_radius/2, m_position.y()-m_radius/2, m_radius, m_radius);
    }
    public int position_x() {
        return m_position.x();
    }
    public int position_y() {
        return m_position.y();
    }
}

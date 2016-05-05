package main;

public class Point {
    private int m_x, m_y;
    public Point(int x, int y) {
        m_x = x;
        m_y = y;
    }
    public int x() {
        return m_x;
    }
    public int y() {
        return m_y;
    }
    public boolean equals(Point p) {
    	return (Math.abs(p.x()-m_x)<5) && (Math.abs(p.y()-m_y)<5);
    }
    public void add(Point p) {
    	m_x += p.x();
    	m_y += p.y();
    }
    public Point clone() {
    	return new Point(m_x, m_y);
    }
}

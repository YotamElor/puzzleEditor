package main;

import java.awt.*;

public class Edge {
    final private Node m_node0, m_node1;
    private EdgeType m_type;

    public Edge(Node n0, Node n1, EdgeType type) {
        m_node0 = n0;
        m_node1 = n1;
        m_type = type;
    }

    public void draw(Graphics g) {
    	if (m_type==EdgeType.regularEdge) {
    		g.setColor(Color.black);
    	} else if (m_type==EdgeType.borderEdge) {
    		g.setColor(Color.green);
    	} else if (m_type==EdgeType.solutionEdge) {
    		g.setColor(Color.red);
    	}
        g.drawLine(m_node0.position_x(), m_node0.position_y(), m_node1.position_x(), m_node1.position_y());
    }
    public double calcDistance(int x, int y) {
        if (m_type == EdgeType.borderEdge) {
            return 100000.;
        }
        double x0 = m_node0.position_x();
        double y0 = m_node0.position_y();
        double x1 = m_node1.position_x();
        double y1 = m_node1.position_y();
        //xt = x0 + (x1-x0)t
        //yt = y0 + (y1-y0)t
        //d = (x-xt)^2 + (y-yt)^2
        //dd/dt = (x-xt)(x1-x0)+(y-yt)(y1-y0) = 0
        //x(x1-x0)+y(y1-y0) = xt(x1-x0)+yt(y1-y0)
        //x(x1-x0)+y(y1-y0) = x0(x1-x0) + (x1-x0)^2*t+y0(y1-y0) + (y1-y0)^2*t
        //((x-x0)(x1-x0)+(y-y0)(y1-y0))/((x1-x0)^2+(y1-y0)^2) = t
        double t = ((x-x0)*(x1-x0)+(y-y0)*(y1-y0)) / ((x1-x0)*(x1-x0)+(y1-y0)*(y1-y0));
        t = Math.max(t,0.);
        t = Math.min(t,1.);
        double xt = x0 + (x1-x0)*t;
        double yt = y0 + (y1-y0)*t;
        double d = Math.sqrt((x-xt)*(x-xt) + (y-yt)*(y-yt));
        return d;
    }
    public void flip() {
    	if (m_type==EdgeType.regularEdge) {
    		m_type = EdgeType.borderEdge;
    	} else if (m_type==EdgeType.borderEdge) {
    		m_type = EdgeType.solutionEdge;
    	} else if (m_type==EdgeType.solutionEdge) {
    		m_type = EdgeType.regularEdge;
    	}
    }
}

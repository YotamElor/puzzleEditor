package main;

import java.awt.*;
import java.util.ArrayList;

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
    		g.setColor(Color.green);
    	} else if (m_type==EdgeType.borderEdge) {
    		g.setColor(Color.black);
    	} else if (m_type==EdgeType.solutionEdge) {
    		g.setColor(Color.red);
    	}
        g.drawLine(m_node0.x(), m_node0.y(), m_node1.x(), m_node1.y());
    }
    
    public double calcDistance(int x, int y) {
        double x0 = m_node0.x();
        double y0 = m_node0.y();
        double x1 = m_node1.x();
        double y1 = m_node1.y();
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
    
    public boolean equals(Edge e) {
    	return (m_node0.equals(e.m_node0)&&m_node1.equals(e.m_node1)) || (m_node0.equals(e.m_node1)&&m_node1.equals(e.m_node0)); 
    }
    public void to_stream(ArrayList<Integer> arr) {
    	m_node0.to_stream(arr);
    	m_node1.to_stream(arr);
    	if (m_type==EdgeType.regularEdge) {
    		arr.add(0);
    	} else if (m_type==EdgeType.borderEdge) {
    		arr.add(1);
    	} else if (m_type==EdgeType.solutionEdge) {
    		arr.add(2);
    	}
    }
}

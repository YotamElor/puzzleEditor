package main;

import java.awt.*;
import java.util.ArrayList;

public class Puzzle {
    private ArrayList<Node> m_nodes;
    private ArrayList<Edge> m_edges;
    double m_selectDistance;

    public Puzzle() {
        m_nodes = new ArrayList<Node>();
        m_edges = new ArrayList<Edge>();

        m_nodes.add( new Node(100,300) );
        m_nodes.add( new Node(200,200) );
        m_nodes.add( new Node(300,100) );

        m_edges.add( new Edge(m_nodes.get(0),m_nodes.get(1), EdgeType.regularEdge) );
        m_edges.add( new Edge(m_nodes.get(1),m_nodes.get(2), EdgeType.regularEdge) );
    }
    
    public void draw(Graphics g) {
        for (Edge e : m_edges) {
            e.draw(g);
        }
        for (Node n : m_nodes) {
            n.draw(g);
        }
    }
    public void onTouch(int x, int y) {
        Edge selectedEdge = null;
        double minDistance = m_selectDistance;
        for (Edge e : m_edges) {
            double d = e.calcDistance(x,y);
            if (d<minDistance) {
                minDistance = d;
                selectedEdge = e;
            }
        }
        if (selectedEdge!=null) {
            selectedEdge.flip();
        }
    }
}

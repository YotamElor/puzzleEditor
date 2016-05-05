package main;

import java.awt.*;
import java.util.ArrayList;

public class Puzzle {
    private ArrayList<Node> m_nodes;
    private ArrayList<Edge> m_edges;
    double m_selectDistance;

    public Puzzle() {
        m_selectDistance = 500;
        createGrid();
        ArrayList<Integer> arr = new ArrayList<Integer>();
        to_stream(arr);
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
    
    public void createGrid() {
    	final int edgeSize = 50;
    	final int canvasSize = 500;
    	ArrayList<Point> neighbors = new ArrayList<Point>();
    	neighbors.add( new Point(edgeSize,edgeSize) );
    	neighbors.add( new Point(edgeSize,-edgeSize) );
    	neighbors.add( new Point(-edgeSize,edgeSize) );
    	neighbors.add( new Point(-edgeSize,-edgeSize) );
    	
        m_nodes = new ArrayList<Node>();
        m_edges = new ArrayList<Edge>();
        m_nodes.add( new Node(0,0) );
        
        for (int node_idx=0 ; node_idx<m_nodes.size(); node_idx++) {
        	Node current = m_nodes.get(node_idx);
            for (Point neighbor : neighbors) {
            	Point optional_new_position = current.position().clone();
            	optional_new_position.add(neighbor);
            	Node optional_new_node = new Node( optional_new_position );
            	boolean add_node = true;
            	if (optional_new_node.x()<0 || optional_new_node.x()>canvasSize || optional_new_node.y()<0 || optional_new_node.y()>canvasSize) {
            		add_node = false;
            	}
                for (Node n : m_nodes) {
                	if (n.equals(optional_new_node)) {
                		add_node = false;
        				boolean add_edge = true;
                		Edge optional_new_edge = new Edge(current, optional_new_node, EdgeType.regularEdge);
                		for (Edge e : m_edges) {
                			if (e.equals(optional_new_edge)) {
                				add_edge = false;
                			}
                		}
                		if (add_edge) {
                			m_edges.add(optional_new_edge);
                		}
                	}
                }
                if (add_node) {
                	m_nodes.add(optional_new_node);
                	m_edges.add( new Edge(m_nodes.get(node_idx), m_nodes.get(m_nodes.size()-1), EdgeType.regularEdge) );
                }
            }
        }
    }
    
    public void to_stream(ArrayList<Integer> arr) {
    	arr.add(m_nodes.size());
    	for (Node n : m_nodes) {
    		n.to_stream(arr);
    	}
    	arr.add(m_edges.size());
    	for (Edge e : m_edges) {
    		e.to_stream(arr);
    	}
    }
}

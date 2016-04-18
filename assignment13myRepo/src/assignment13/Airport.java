package assignment13;

import java.util.LinkedList;

public class Airport {
	
	public String name; // used to id the Airport
	public double dist; //Cost
	public Airport prev; //Previous Airport on shortest path
	public LinkedList<Flight> adj; // adjacency list of adjacent Airports
	public int scratch; //Extra variable used in Dijkstra's algorithm
	

	public Airport(String _name) {
		name = _name;
		adj = new LinkedList<Flight>();
		reset();
	}
	
	public void reset() {
		dist = Double.MAX_VALUE;
		prev = null;
		scratch = 0;
	}
}

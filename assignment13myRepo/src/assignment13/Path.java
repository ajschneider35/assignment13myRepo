package assignment13;

/**
 * Class representing an entry in the PQ for Dijkstra's algorithm.
 * 
 * @author Andrew Schneider
 * @author <Partner's Name>
 * @version 4/28/2016
 *
 */
public class Path implements Comparable<Path> {

	public Airport dest; // w
	public double cost; // d(w)

	public Path(Airport d, double c) {

		dest = d;
		cost = c;
	}

	@Override
	public int compareTo(Path rhs) {

		double otherCost = rhs.cost;

		return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
	}

}

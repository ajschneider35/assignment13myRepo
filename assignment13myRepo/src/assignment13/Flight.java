package assignment13;

import java.util.LinkedList;

public class Flight {

	Airport orig;
	Airport dest; // 2nd vertex in Edge
	double cost; // Edge weight or cost
	public LinkedList<String> carriers; // List of carriers that provide this
										// flight
	double distance;
	double time;
	double delay;
	double canceled;

	public Flight(Airport o, Airport d, double c, LinkedList<String> car, double dis, double t, double de, double can) {
		orig = o;
		dest = d;
		cost = c;
		carriers = car;
		distance = dis;
		time = t;
		delay = de;
		canceled = can;

	}

	public Airport getOtherVertex() {
		return dest;
	}
}

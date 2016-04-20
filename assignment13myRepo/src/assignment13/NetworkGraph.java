/**
 * 
 */
package assignment13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * <p>
 * This class represents a graph of flights and airports along with specific
 * data about those flights. It is recommended to create an airport class and a
 * flight class to represent nodes and edges respectively. There are other ways
 * to accomplish this and you are free to explore those.
 * </p>
 * 
 * <p>
 * Testing will be done with different criteria for the "best" path so be sure
 * to keep all information from the given file. Also, before implementing this
 * class (or any other) draw a diagram of how each class will work in relation
 * to the others. Creating such a diagram will help avoid headache and confusion
 * later.
 * </p>
 * 
 * <p>
 * Be aware also that you are free to add any member variables or methods needed
 * to completed the task at hand
 * </p>
 * 
 * @author CS2420 Teaching Staff - Spring 2016
 */
public class NetworkGraph {

	private Map<String, Airport> airportMap;
	private Map<String, Flight> flightMap;
	private Hashtable<String, LinkedList<String>> network;

	/**
	 * <p>
	 * Constructs a NetworkGraph object and populates it with the information
	 * contained in the given file. See the sample files or a randomly generated
	 * one for the proper file format.
	 * </p>
	 * 
	 * <p>
	 * You will notice that in the given files there are duplicate flights with
	 * some differing information. That information should be averaged and
	 * stored properly. For example some times flights are canceled and that is
	 * represented with a 1 if it is and a 0 if it is not. When several of the
	 * same flights are reported totals should be added up and then reported as
	 * an average or a probability (value between 0-1 inclusive).
	 * </p>
	 * 
	 * @param flightInfoPath
	 *            - The path to the file containing flight data. This should be
	 *            a *.csv(comma separated value) file
	 * 
	 * @throws FileNotFoundException
	 *             The only exception that can be thrown in this assignment and
	 *             only if a file is not found.
	 */
	public NetworkGraph(String flightInfoPath) throws FileNotFoundException {
		// TODO: Implement a constructor that reads in the file and stores the
		// information
		// appropriately in this object.

		airportMap = new HashMap<String, Airport>();
		flightMap = new HashMap<String, Flight>();
		network = new Hashtable<String, LinkedList<String>>();
		parseCSVFile(flightInfoPath);
	}

	/**
	 * This method returns a BestPath object containing information about the
	 * best way to fly to the destination from the origin. "Best" is defined by
	 * the FlightCriteria parameter <code>enum</code>. This method should throw
	 * no exceptions and simply return a BestPath object with information
	 * dictating the result. For example, if a destination or origin is not
	 * contained in this instance of NetworkGraph simply return a BestPath with
	 * no path (not a <code>null</code> path). If origin or destination are
	 * <code>null</code> return a BestPath object with null as origin or
	 * destination (which ever is <code>null</code>.
	 * 
	 * @param origin
	 *            - The starting location to find a path from. This should be a
	 *            3 character long string denoting an airport.
	 * 
	 * @param destination
	 *            - The destination location from the starting airport. Again,
	 *            this should be a 3 character long string denoting an airport.
	 * 
	 * @param criteria
	 *            - This enum dictates the definition of "best". Based on this
	 *            value a path should be generated and return.
	 * 
	 * @return - An object containing path information including origin,
	 *         destination, and everything in between.
	 */
	public BestPath getBestPath(String origin, String destination, FlightCriteria criteria) {
		// TODO: First figure out what kind of path you need to get (HINT: Use a
		// switch!) then
		// Search for the shortest path using Dijkstra's algorithm.

		switch (criteria) {

		case COST:
			break;

		case DELAY:
			break;

		case DISTANCE:
			break;

		case CANCELED:
			break;

		case TIME:
			break;

		}
		return null;
	}

	/**
	 * <p>
	 * This overloaded method should do the same as the one above only when
	 * looking for paths skip the ones that don't match the given airliner.
	 * </p>
	 * 
	 * @param origin
	 *            - The starting location to find a path from. This should be a
	 *            3 character long string denoting an airport.
	 * 
	 * @param destination
	 *            - The destination location from the starting airport. Again,
	 *            this should be a 3 character long string denoting an airport.
	 * 
	 * @param criteria
	 *            - This enum dictates the definition of "best". Based on this
	 *            value a path should be generated and return.
	 * 
	 * @param airliner
	 *            - a string dictating the airliner the user wants to use
	 *            exclusively. Meaning no flights from other airliners will be
	 *            considered.
	 * 
	 * @return - An object containing path information including origin,
	 *         destination, and everything in between.
	 */
	public BestPath getBestPath(String origin, String destination, FlightCriteria criteria, String airliner) {
		// TODO:

		switch (criteria) {

		case COST:
			break;

		case DELAY:
			break;

		case DISTANCE:
			break;

		case CANCELED:
			break;

		case TIME:
			break;

		}
		return null;
	}

	/**
	 * Single source, weighted shortest path algorithm.
	 * 
	 * @param startName
	 * @throws Exception
	 */
	public void dijkstras(String startName) throws Exception {
		// NOTE: NOT YET OPTIMIZED FOR FINDING PATH BETWEEN 2 AIRPORTS, JUST
		// FINDS
		// SHORTEST PATH TO ALL OF THE AIRPORTS FROM STARTING AIRPORT.

		PriorityQueue<Path> pq = new PriorityQueue<Path>();

		Airport start = airportMap.get(startName);

		if (start == null) {
			throw new NoSuchElementException();
		}

		clearAll();

		pq.add(new Path(start, 0));
		start.dist = 0;

		int airportsVisited = 0;
		while (!pq.isEmpty() && airportsVisited < airportMap.size()) {
			Path vrec = pq.remove();
			Airport a = vrec.dest;
			if (a.scratch != 0) { // already processed a
				continue;
			}

			a.scratch = 1;
			airportsVisited++;

			for (Flight f : a.adj) {
				Airport w = f.dest;
				double caw = f.cost;

				if (caw < 0) {
					throw new Exception("Graph has negative edges");
				}

				if (w.dist > a.dist) {
					w.dist = a.dist + caw;
					w.prev = a;
					pq.add(new Path(w, w.dist));
				}
			}
		}
	}

	/**
	 * Adds a new flight between the airports.
	 * 
	 * @param airport1
	 * @param airport2
	 * @param cost
	 */
	public void addFlight(String airport1, String airport2, double cost, LinkedList<String> carriers, double distance,
			double time, double delay, double canceled) {

		Airport a = getAirport(airport1);
		Airport w = getAirport(airport2);
		a.adj.add(new Flight(a, w, cost, carriers, distance, time, delay, canceled));
	}

	/**
	 * If airportName is not present, add it to the airport map. In either case
	 * return the Aiport.
	 * 
	 * @param airportName
	 * @return a -- an Airport
	 */
	private Airport getAirport(String airportName) {
		Airport a = airportMap.get(airportName);
		if (a == null) {
			a = new Airport(airportName);
			airportMap.put(airportName, a);
		}
		return a;
	}

	/**
	 * Initializes the Airport output info prior to running the shortest path
	 * algorithm.
	 */
	private void clearAll() {
		for (Airport a : airportMap.values()) {
			a.reset();
		}
	}

	private void parseCSVFile(String path) {
		long lineNumber = 0;
		File file = new File(path);
		// InputStreamReader inputStreamReader;
		try {

			Scanner inputStream = new Scanner(new BufferedReader(new FileReader(file)));

			LinkedList<String> originList;
			LinkedList<String> destinationList;
			LinkedList<String> carrierList;
			LinkedList<String> delayList;
			LinkedList<String> canceledList;
			LinkedList<String> timeList;
			LinkedList<String> distanceList;
			LinkedList<String> costList;

			while (inputStream.hasNext()) {
				String data = inputStream.nextLine(); // Gets a whole line
				String[] line = data.split(","); // Splits the line up into a
													// string array

				if (line.length > 1) {
					if (lineNumber == 0) {
						for (int i = 0; i < line.length; i++) {
							network.put(line[i], new LinkedList<String>());
						}
					} else {

						getAirport(line[0]); // Adds airport to the airportMap
												// if it is not added already
						originList = network.get("ORIGIN");
						originList.add(line[0]);
						network.replace("ORIGIN", originList);

						getAirport(line[1]); // Adds airport to the airportMap
												// if it is not added already
						destinationList = network.get("DESTINATION");
						destinationList.add(line[1]);
						network.put("DESTINATION", destinationList);

						carrierList = network.get("CARRIER");
						carrierList.add(line[2]);
						delayList = network.get("DELAY");
						delayList.add(line[3]);
						canceledList = network.get("CANCELED");
						canceledList.add(line[4]);
						timeList = network.get("TIME");
						timeList.add(line[5]);
						distanceList = network.get("DISTANCE");
						distanceList.add(line[6]);
						costList = network.get("COST");
						costList.add(line[7]);

						network.put("CARRIER", carrierList);
						network.put("DELAY", delayList);
						network.put("CANCELED", canceledList);
						network.put("TIME", timeList);
						network.put("DISTANCE", distanceList);
						network.put("COST", costList);
						
						if(!flightMap.containsKey(line[0] + " to " + line[1])) {
							flightMap.put(line[0] + " to " + line[1], new Flight(airportMap.get(line[0]), airportMap.get(line[1]), avgCriteria(costList),
									new LinkedList<String>(), avgCriteria(distanceList), avgCriteria(timeList), 
									avgCriteria(delayList), avgCriteria(canceledList)));
						} else {
							Flight currFlight = flightMap.get(line[0] + " to " + line[1]);
							currFlight.carriers.add(line[2]);
							currFlight.cost = avgCriteria(costList);
							currFlight.distance = avgCriteria(distanceList);
							currFlight.time = avgCriteria(timeList);
							currFlight.delay = avgCriteria(delayList);
							currFlight.canceled = avgCriteria(canceledList);
						}
					}

				}
				lineNumber++;
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private double avgCriteria(LinkedList<String> criteriaList) {
		double total = 0;
		for(String s : criteriaList) {
			total = total + Double.parseDouble(s);
		}
		return (total/criteriaList.size());
	}
}

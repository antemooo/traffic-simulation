package citySimulation;

import java.io.*;
//import java.util.Random;

import trafficSimulation.CrossRoad;
import trafficSimulation.TrafficLight.State;
import trafficSimulation.Vehicle;

public class SimulationReader {

	public TrafficFlowSimulation simulation;

	private interface LineParser {
		public void parse(String line, int lineIndex);
	}

	private class CityLineParser implements LineParser {
		public void parse(String line, int lineIndex) {
			String[] args = line.split(" ");
			if (args[0].equals("$")) {
				String cityName = args[1];
				int horizontalSize = Integer.parseInt(args[2]);
				int verticalSize = Integer.parseInt(args[3]);
				simulation.initiateCity(cityName, horizontalSize, verticalSize);
			} else if (args[0].equals("@")) {
				String from = args[1];
				String to = args[2];
				simulation.addRoadWork(from, to);
			} else {
				int row = lineIndex - 1;
				int verticalSize = args.length / 19;
				for (int column = 0; column < verticalSize; column += 1) {
					int offset = column * 19;
					String crossName = args[0 + offset];
					int horizontalGreen = Integer.parseInt(args[1 + offset]);
					int verticalGreen = Integer.parseInt(args[2 + offset]);
					// int horizontalRed = verticalGreen + 1;
					// int verticalRed = horizontalGreen + 1;
					int traveltimeNord = Integer.parseInt(args[3 + offset]);
					int traveltimeEast = Integer.parseInt(args[4 + offset]);
					int traveltimeSouth = Integer.parseInt(args[5 + offset]);
					int traveltimeWest = Integer.parseInt(args[6 + offset]);
					int flowNord = Integer.parseInt(args[7 + offset]);
					int flowEast = Integer.parseInt(args[8 + offset]);
					int flowSouth = Integer.parseInt(args[9 + offset]);
					int flowWest = Integer.parseInt(args[10 + offset]);
					int capacityStreetNorth = Integer.parseInt(args[11 + offset]);
					int capacityStreetEast = Integer.parseInt(args[12 + offset]);
					int capacityStreetSouth = Integer.parseInt(args[13 + offset]);
					int capacityStreetWest = Integer.parseInt(args[14 + offset]);
					int capacityTrafficLightNorth = Integer.parseInt(args[15 + offset]);
					int capacityTrafficLightEast = Integer.parseInt(args[16 + offset]);
					int capacityTrafficLightSouth = Integer.parseInt(args[17 + offset]);
					int capacityTrafficLightWest = Integer.parseInt(args[18 + offset]);
					
					// Create a crossroad here or pass all arguments
					CrossRoad crossRoad = new CrossRoad(crossName, State.RED, capacityTrafficLightNorth, flowNord,
							State.GREEN, capacityTrafficLightWest,flowWest, State.GREEN, capacityTrafficLightEast, flowEast,
							State.RED, capacityTrafficLightSouth, flowSouth, capacityStreetNorth, traveltimeNord,
							capacityStreetEast, traveltimeEast, capacityStreetWest, traveltimeWest,
							capacityStreetSouth, traveltimeSouth);
					
					// to set the time for the states Horizontal(North,South) and Vertical(West,East)
					crossRoad.getTrafficNorth().setTimeForState(State.GREEN, verticalGreen);
					crossRoad.getTrafficSouth().setTimeForState(State.GREEN, verticalGreen);
					crossRoad.getTrafficEast().setTimeForState(State.GREEN, horizontalGreen);
					crossRoad.getTrafficWest().setTimeForState(State.GREEN, horizontalGreen);
					
					simulation.setUpCrossroad(row, column, crossRoad);
				}
			}
		}
	}

	private class VehicleLineParser implements LineParser {
		public void parse(String line, int lineIndex) {
			String[] args = line.split(" ");
			String name = args[0];
			String start = args[1];
			String destination = args[2];
			boolean priority = (args[3].equals("1"));
			// Pass were needed in your implementation
//			boolean dynamicPath = (args[4].equals("1"));
			Vehicle v = new Vehicle(name, destination, priority);
//			String [] streets = { "TrafficNorth", "TrafficSouth", "TrafficWest","TrafficEast" };
//			Random rand = new Random();
//			int i = rand.nextInt(3);
			simulation.addVehicle(start, "TrafficNorth", v);
		}
	}

	public SimulationReader(TrafficFlowSimulation simulation) {
		this.simulation = simulation;
	}

	void read(String fileName, LineParser lineParser) {
		File inputFile = new File(fileName);
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFile));
			try {
				String line = null;
				int lineIndex = 0;
				while ((line = input.readLine()) != null) {
					if (!line.startsWith("#")) {
						lineParser.parse(line, lineIndex);
						lineIndex += 1;
					}
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	void readCity(String fileName) {
		read(fileName, new CityLineParser());
	}

	void readVehicles(String fileName) {
		read(fileName, new VehicleLineParser());
	}

}

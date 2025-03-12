// Author: Broc Weselmann
// NID: br142931
// Course: CS2
// Semester: Spring 2025

import java.io.File;
import java.util.*;

public class GreedyRobots {
    private List<Integer> robots;
    private List<Integer> buildings;

    private int buildingsRecieved;
    private int buildingsUnserved;

    public GreedyRobots(int numRobots, int numBuildings, String robotsPath, String buildingsPath) {
        robots = new ArrayList<>(numRobots);
        buildings = new ArrayList<>(numBuildings);
        buildingsUnserved = numBuildings;
        buildingsRecieved = 0;
        readFiles(robotsPath, buildingsPath);
    }

    public void assignDeliveries() {
        for (int i = 0; i < robots.size(); i++) {
            for (int j = 0; j < buildings.size(); j++) {
                int robot = robots.get(i);
                int building = buildings.get(j);
                if (robot >= building) {
                    robot -= building;
                    buildingsRecieved += 1;
                    buildingsUnserved -= 1;
                    buildings.remove(j);
                    j++;
                    if (robot <= 0) {
                        robots.remove(i);
                        j = 0;
                    }
                }
            }
        }
    }

    public void displayResults() {
        System.out.println("Successful Deliveries: " + buildingsRecieved);
        System.out.println("Unserved Buildings: " + buildingsUnserved);
    }

    // Why public?
    public void readFiles(String robotsPath, String buildingsPath) {
        File robotsFile = new File(robotsPath);
        File buildingsFile = new File(buildingsPath);
        Scanner input;

        try {
            input = new Scanner(robotsFile);
            while (input.hasNextInt()) {
                int robot = input.nextInt();
                robots.add(robot);
            }
            Collections.sort(robots, Comparator.reverseOrder());
            input.close();

            input = new Scanner(buildingsFile);
            while (input.hasNextInt()) {
                int building = input.nextInt();
                buildings.add(building);
            }
            Collections.sort(buildings, Comparator.reverseOrder());
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

// Dr. Ali
// COP3503 Spring 2025
// Programming Assignment 3


public class GreedyRobotsDriver {
    
    public static void main(String[] args) throws Exception {
        // Creating test cases with different input files
        GreedyRobots test1 = new GreedyRobots(5, 10, "robots1.txt", "buildings1.txt");
        GreedyRobots test2 = new GreedyRobots(20, 20, "robots2.txt", "buildings2.txt");
        GreedyRobots test3 = new GreedyRobots(90, 100, "robots3.txt", "buildings3.txt");
        GreedyRobots test4 = new GreedyRobots(2000, 2000, "robots4.txt", "buildings4.txt");
        GreedyRobots test5 = new GreedyRobots(8000, 5000, "robots5.txt", "buildings5.txt");

        // Running first test case
        System.out.println("Testing First Scenario...");
        test1.assignDeliveries();
        test1.displayResults();

        // Running second test case
        System.out.println("Testing Second Scenario...");
        test2.assignDeliveries();
        test2.displayResults();

        // Running third test case
        System.out.println("Testing Third Scenario...");
        test3.assignDeliveries();
        test3.displayResults();

        // Running fourth test case
        System.out.println("Testing Fourth Scenario...");
        test4.assignDeliveries();
        test4.displayResults();

        // Running fifth test case
        System.out.println("Testing Fifth Scenario...");
        test5.assignDeliveries();
        test5.displayResults();
    }
}

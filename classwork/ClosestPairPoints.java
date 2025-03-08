import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class ClosestPairPoints {

    // Point class to represent a point in 2D space
    static class Point {
        private int x; // x-coordinate of the point
        private int y; // y-coordinate of the point

        // Constructor to initialize a point
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // Getter for x-coordinate
        public int getX() {
            return x;
        }

        // Getter for y-coordinate
        public int getY() {
            return y;
        }

        // toString method for displaying the point
        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    // Comparator to sort points by x-coordinate
    static class CompareByX implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return Integer.compare(p1.getX(), p2.getX());
        }
    }

    // Comparator to sort points by y-coordinate
    static class CompareByY implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return Integer.compare(p1.getY(), p2.getY());
        }
    }

    // Class to store the result: the closest distance and the points forming the closest pair
    static class Result {
        double distance; // The closest distance
        Point p1;        // First point in the closest pair
        Point p2;        // Second point in the closest pair

        public Result(double distance, Point p1, Point p2) {
            this.distance = distance;
            this.p1 = p1;
            this.p2 = p2;
        }
    }
    
    // Method to calculate the distance between two points
    public static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.getX() - p2.getX()) * (p1.getX() - p2.getX()) +
                         (p1.getY() - p2.getY()) * (p1.getY() - p2.getY()));
    }

    public static Result bruteForce(ArrayList<Point> points) {
        double minDistance = Double.MAX_VALUE;
        Point closestP1 = null, closestP2 = null;
        // Compare every pair of points
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                double dist = distance(points.get(i), points.get(j));
                if (dist < minDistance) {
                    minDistance = dist;
                    closestP1 = points.get(i);
                    closestP2 = points.get(j);
                }
            }
        }

        return new Result(minDistance, closestP1, closestP2);
    }

    public static Result closestPair(ArrayList<Point> points) {
        Collections.sort(points, new CompareByX());
        return closestPairRecursive(points, 0, points.size() - 1);
    }

    public static Result closestPairRecursive(ArrayList<Point> points, int left, int right) {
        // Base case
        if (right - left <= 2) {
            ArrayList<Point> subset = new ArrayList<Point>(points.subList(left, right + 1));
            return bruteForce(subset);
        }

        // Find the midpoint
        int mid = (left + right) / 2;
        // Get its X
        int midX = points.get(mid).getX();
        
        // Call recursively for the left and right halves
        Result leftResult = closestPairRecursive(points, left, mid);
        Result rightResult = closestPairRecursive(points, mid + 1, right);

        Result deltaResult = leftResult.distance < rightResult.distance ? leftResult : rightResult;
        double delta = deltaResult.distance;

        // Create a strip of points close to the midline
        ArrayList<Point> strip = new ArrayList<Point>();
        // Add the points to our strip range
        for (int i = left; i <= right; i++) {
            if (Math.abs(points.get(i).getX() - midX) < delta) {
                strip.add(points.get(i));
            }
        }

        Collections.sort(strip, new CompareByY());

        // Find the closest pair in strip
        Point stripP1 = null, stripP2 = null;
        for (int i = 0; i < strip.size(); i++) {
            // Maximum 8 points, minimum between the actual size and 8
            for (int j = i + 1; j < Math.min(strip.size(), i + 7); j++) {
                double dist = distance(strip.get(i), strip.get(j));
                if (dist < delta) {
                    delta = dist;
                    stripP1 = strip.get(i);
                    stripP2 = strip.get(j);
                }
            }
        }

        if (stripP1 != null && stripP2 != null) {
            return new Result(delta, stripP1, stripP2);
        }
        else {
            return deltaResult;
        }
    }   

    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Point> points = new ArrayList<Point>();
        for (int i = 0; i < 100000; i++) {
            int randX = random.nextInt(5000);
            int randY = random.nextInt(5000);
            points.add(new Point(randX, randY));
        }

        // Find the closest pair using divide and conquer
        Result resultDC = closestPair(points);
        System.out.println("Closest Pair (Divide & Conquer): " + resultDC.p1 + " and " + resultDC.p2 +
                           " with distance: " + resultDC.distance);

        // Find the closest pair using brute force
        Result resultBF = bruteForce(points);
        System.out.println("Closest Pair (Brute Force): " + resultBF.p1 + " and " + resultBF.p2 +
                           " with distance: " + resultBF.distance);

    }
}

// Author: Broc Weselmann
// NID: br142931
// Course: CS2
// Semester: Spring 2025

import java.util.*;

public class CourseCombinations {
    // Map of Courses (Strings) to a TreeSet of Students
    // E.x.: CS101
    //       - ALICE BOB CHARLIE
    static HashMap<String, TreeSet<String>> courses = new HashMap<>();

    // Unique Pairs of Students
    static HashSet<String> uniqueStudentPairs = new HashSet<>();
    
    static void readFile() {
        Scanner scanner = new Scanner(System.in);
        int numStudents = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numStudents; i++) {
            String[] studentClassesSeperated = scanner.nextLine().split(" ");
            String studentName = studentClassesSeperated[0];
            for (String parsedClass : studentClassesSeperated) {
                // Ignore the student name
                if (studentName == parsedClass) {
                    continue;
                }

                // Create the entry for the class if absent and add the student to the set.     
                courses.computeIfAbsent(parsedClass, k -> new TreeSet<>()).add(studentName);
            }
        }
        scanner.close();
    }

    static void findUniquePairs() {
        // For each course
        for (Map.Entry<String, TreeSet<String>> entry : courses.entrySet()) {
            ArrayList<String> students = new ArrayList<>(entry.getValue());
            // Loop through all combinations of students
            for (int i = 0; i < students.size(); i++) {
                for (int j = i + 1; j < students.size(); j++) {
                    String first = students.get(i);
                    String second = students.get(j);
                    
                    // Ensures same order; (JOHN, ALICE) to (ALICE, JOHN)
                    if (first.compareTo(second) > 0) {
                        String temp = first;
                        first = second;
                        second = temp;
                    }
                    
                    // Add each pair found to HashSet
                    uniqueStudentPairs.add(first + " " + second);
                }
            }
        }
    }

    public static void main(String[] args) {
        readFile();
        findUniquePairs();
        System.out.println(uniqueStudentPairs.size());
    }
}
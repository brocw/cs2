import java.util.*;

public class ParenthesesCombinationsDriver {

    public static void main(String[] args) {
        ParenthesesCombinations obj = new ParenthesesCombinations();
        int pass = 0;
        
        System.out.println("-------------------------------------");
        System.out.println("Test Case 1 (n = 1)");

        List<String> test1 = obj.generateParentheses(1);
        List<String> expected1 = Arrays.asList("()");
        if (compareLists(test1, expected1)) {
            System.out.println("Test Case 1 Passed!");
            ++pass;
        } else {
            System.out.println("Test Case 1 Failed! Expected: " + expected1 + ", but got: " + test1);
        }

        System.out.println("-------------------------------------");

        System.out.println("Test Case 2 (n = 2)");

        List<String> test2 = obj.generateParentheses(2);
        List<String> expected2 = Arrays.asList("(())", "()()");
        if (compareLists(test2, expected2)) {
            System.out.println("Test Case 2 Passed!");
            ++pass;
        } else {
            System.out.println("Test Case 2 Failed! Expected: " + expected2 + ", but got: " + test2);
        }

        System.out.println("-------------------------------------");

        System.out.println("Test Case 3 (n = 3)");

        List<String> test3 = obj.generateParentheses(3);
        List<String> expected3 = Arrays.asList("((()))", "(()())", "(())()", "()(())", "()()()");
        if (compareLists(test3, expected3)) {
            System.out.println("Test Case 3 Passed!");
            ++pass;
        } else {
            System.out.println("Test Case 3 Failed! Expected: " + expected3 + ", but got: " + test3);
        }

        System.out.println("-------------------------------------");

        System.out.println("Test Case 4 (n = 4)");

        List<String> test4 = obj.generateParentheses(4);
        if (test4.size() == 14) {  // There should be exactly 14 valid combinations for n=4
            System.out.println("Test Case 4 Passed!");
            ++pass;
        } else {
            System.out.println("Test Case 4 Failed! Expected 14 elements but got " + test4.size());
        }

        System.out.println("-------------------------------------");

        System.out.println("Test Case 5 (n = 5)");

        List<String> test5 = obj.generateParentheses(5);
        if (test5.size() == 42) {  // There should be exactly 42 valid combinations for n=5
            System.out.println("Test Case 5 Passed!");
            ++pass;
        } else {
            System.out.println("Test Case 5 Failed! Expected 42 elements but got " + test5.size());
        }

        System.out.println("-------------------------------------");

        if (pass == 5)
            System.out.println("WOOHOO! All test cases passed!");
        else
            System.out.println("Oh no! Some test cases failed!");
    }

    // Helper function to compare lists regardless of order
    private static boolean compareLists(List<String> actual, List<String> expected) {
        Collections.sort(actual);
        Collections.sort(expected);
        return actual.equals(expected);
    }
}

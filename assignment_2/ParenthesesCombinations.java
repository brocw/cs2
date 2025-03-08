// Author: Broc Weselmann
// NID: br142931
// Course: CS2
// Semester: Spring 2025

import java.util.*;

public class ParenthesesCombinations {
    private List<String> combinations;

    public ParenthesesCombinations() {
        combinations = new ArrayList<>();
    }

    public List<String> generateParentheses(int n) {
        combinations.clear();
        char[] str = new char[n * 2];
        generateParenthesesRecursively(str, 0);
        return combinations;
    }

    private void generateParenthesesRecursively(char[] str, int index) {
        // Base Case: We've generated the string
        if (index == str.length) {
            String s = new String(str);
            if (validParentheses(s)) { // If valid add to list
                combinations.add(s);
            }
            return;
        }
        
        // Start with open parentheses
        str[index] = '(';
        generateParenthesesRecursively(str, index + 1);

        // Backtrack to closed parentheses
        str[index] = ')';
        generateParenthesesRecursively(str, index + 1);
    }

    // Detects whether a parentheses in String str is valid or not.
    // A valid parentheses has all sets be opened '(' and closed ')'.
    // This works by adding 1 when seeing '(' and -1 when seeing ')'.
    private boolean validParentheses(String str) {
        int score = 0;
        for (int i = 0; i < str.length(); i++) {
            char current_c = str.charAt(i);
            if (current_c == '(') {
                score += 1;
            }
            else if (current_c == ')') {
                score -= 1;
            }

            // This prevents ")(" from being a valid parentheses.
            // It must prematurely exit as at the end of the String
            // it would equal 0.
            if (score < 0) {
                return false;
            }
        }

        // Score must be 0 at the end to have all parentheses opened and closed.
        if (score != 0 || str.isEmpty()) {
            return false;
        }
        return true;
    }
}
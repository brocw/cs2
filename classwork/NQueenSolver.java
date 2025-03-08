import java.util.*;

public class NQueenSolver {
    private int n;

    private int board[][];

    private boolean[] usedRows;
    private boolean[] usedDescendingDiagonals;
    private boolean[] usedAscendingDiagonals;

    public NQueenSolver(int n) {
        this.n = n;
        this.usedRows = new boolean[n];
        this.usedAscendingDiagonals = new boolean[n * 2];
        this.usedDescendingDiagonals = new boolean[n * 2];
        this.board = new int[n][n];
    }

    public boolean solve() {
        return solveRecursively(0);
    }

    private boolean solveRecursively(int column) {
        for (int row = 0; row < n; ++row) {
            if (isPositionSafe(row, column)) {
                usedRows[row] = true;
                usedAscendingDiagonals[n - column + row] = true;
                usedDescendingDiagonals[n - column + row] = true;
                board[row][column] = 1;

                if (column == n - 1) {
                    return true;
                }

                if (solveRecursively(column + 1)) {
                    return true;
                }

                // Backtracking!
                usedRows[row] = false;
                usedAscendingDiagonals[n - column + row] = false;
                usedDescendingDiagonals[n - column + row] = false;
                board[row][column] = 0;
            }
        }

        return false;
    }

    private boolean isPositionSafe(int row, int column) {
        boolean isRowUsed = usedRows[row];
        boolean isAscendedDiagonalUsed = usedAscendingDiagonals[column + row];
        boolean isDescendedDiagonalUsed = usedDescendingDiagonals[n - column + row];

        return !(isRowUsed || isAscendedDiagonalUsed || isDescendedDiagonalUsed);
    }

    public static void main(String[] args) {
        
    }
}

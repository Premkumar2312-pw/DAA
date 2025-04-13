import java.util.Scanner;

public class NQueens {
    static int nqStep = 1;
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter N (number of queens): ");
        N = sc.nextInt();
        int[][] board = new int[N][N];
        System.out.println("\nSolving N-Queens for N = " + N);
        boolean hasSolution = solveNQueens(board, 0);
        if (!hasSolution) {
            System.out.println("No solution exists for N = " + N);
        }
        sc.close();
    }

    static boolean solveNQueens(int[][] board, int row) {
        if (row >= N) {
            printBoard(board, "Solution Found");
            return true;
        }
        boolean res = false;
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1;
                printBoard(board, "Step " + (nqStep++) + ": Placed at (" + row + ", " + col + ")");
                res = solveNQueens(board, row + 1) || res;
                board[row][col] = 0;
                printBoard(board, "Step " + (nqStep++) + ": Backtracked from (" + row + ", " + col + ")");
            }
        }
        return res;
    }

    static boolean isSafe(int[][] board, int row, int col) {
        for (int i = 0; i < row; i++) if (board[i][col] == 1) return false;
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) if (board[i][j] == 1) return false;
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) if (board[i][j] == 1) return false;
        return true;
    }

    static void printBoard(int[][] board, String msg) {
        System.out.println(msg);
        System.out.println("+---".repeat(N) + "+");
        for (int[] row : board) {
            for (int cell : row) {
                System.out.print("| " + (cell == 1 ? "Q" : " ") + " ");
            }
            System.out.println("|");
            System.out.println("+---".repeat(N) + "+");
        }
        System.out.println();
    }
}

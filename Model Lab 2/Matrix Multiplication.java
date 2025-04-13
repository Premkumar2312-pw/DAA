import java.util.*;

public class MatrixMultiplication {
    static int threshold = 128;

    public static int nextPowerOfTwo(int n) {
        int power = 1;
        while (power < n) power <<= 1;
        return power;
    }

    public static int[][] padMatrix(int[][] mat, int size) {
        int[][] padded = new int[size][size];
        for (int i = 0; i < mat.length; i++)
            for (int j = 0; j < mat[0].length; j++)
                padded[i][j] = mat[i][j];
        return padded;
    }

    public static int[][] naiveMultiply(int[][] A, int[][] B) {
        int n = A.length, m = B[0].length, k = A[0].length;
        int[][] C = new int[n][m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                for (int l = 0; l < k; l++)
                    C[i][j] += A[i][l] * B[l][j];
        return C;
    }

    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res[i][j] = A[i][j] + B[i][j];
        return res;
    }

    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] res = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                res[i][j] = A[i][j] - B[i][j];
        return res;
    }

    public static int[][] dacMultiply(int[][] A, int[][] B) {
        int n = A.length;
        if (n <= 64) return naiveMultiply(A, B);

        int newSize = n / 2;
        int[][] A11 = new int[newSize][newSize], A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize], A22 = new int[newSize][newSize];
        int[][] B11 = new int[newSize][newSize], B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize], B22 = new int[newSize][newSize];

        for (int i = 0; i < newSize; i++)
            for (int j = 0; j < newSize; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + newSize];
                A21[i][j] = A[i + newSize][j];
                A22[i][j] = A[i + newSize][j + newSize];
                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + newSize];
                B21[i][j] = B[i + newSize][j];
                B22[i][j] = B[i + newSize][j + newSize];
            }

        int[][] C11 = add(dacMultiply(A11, B11), dacMultiply(A12, B21));
        int[][] C12 = add(dacMultiply(A11, B12), dacMultiply(A12, B22));
        int[][] C21 = add(dacMultiply(A21, B11), dacMultiply(A22, B21));
        int[][] C22 = add(dacMultiply(A21, B12), dacMultiply(A22, B22));

        int[][] C = new int[n][n];
        for (int i = 0; i < newSize; i++)
            for (int j = 0; j < newSize; j++) {
                C[i][j] = C11[i][j];
                C[i][j + newSize] = C12[i][j];
                C[i + newSize][j] = C21[i][j];
                C[i + newSize][j + newSize] = C22[i][j];
            }

        return C;
    }

    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;
        if (n <= threshold) return naiveMultiply(A, B);

        int newSize = n / 2;
        int[][] A11 = new int[newSize][newSize], A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize], A22 = new int[newSize][newSize];
        int[][] B11 = new int[newSize][newSize], B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize], B22 = new int[newSize][newSize];

        for (int i = 0; i < newSize; i++)
            for (int j = 0; j < newSize; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + newSize];
                A21[i][j] = A[i + newSize][j];
                A22[i][j] = A[i + newSize][j + newSize];
                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + newSize];
                B21[i][j] = B[i + newSize][j];
                B22[i][j] = B[i + newSize][j + newSize];
            }

        int[][] M1 = strassenMultiply(add(A11, A22), add(B11, B22));
        int[][] M2 = strassenMultiply(add(A21, A22), B11);
        int[][] M3 = strassenMultiply(A11, subtract(B12, B22));
        int[][] M4 = strassenMultiply(A22, subtract(B21, B11));
        int[][] M5 = strassenMultiply(add(A11, A12), B22);
        int[][] M6 = strassenMultiply(subtract(A21, A11), add(B11, B12));
        int[][] M7 = strassenMultiply(subtract(A12, A22), add(B21, B22));

        int[][] C11 = add(subtract(add(M1, M4), M5), M7);
        int[][] C12 = add(M3, M5);
        int[][] C21 = add(M2, M4);
        int[][] C22 = add(subtract(add(M1, M3), M2), M6);

        int[][] C = new int[n][n];
        for (int i = 0; i < newSize; i++)
            for (int j = 0; j < newSize; j++) {
                C[i][j] = C11[i][j];
                C[i][j + newSize] = C12[i][j];
                C[i + newSize][j] = C21[i][j];
                C[i + newSize][j + newSize] = C22[i][j];
            }

        return C;
    }

    public static void printMatrix(int[][] mat, int r, int c) {
        System.out.println("Result matrix:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter rows and cols of Matrix A: ");
        int r1 = sc.nextInt(), c1 = sc.nextInt();
        int[][] A = new int[r1][c1];
        System.out.println("Enter elements of Matrix A:");
        for (int i = 0; i < r1; i++)
            for (int j = 0; j < c1; j++)
                A[i][j] = sc.nextInt();

        System.out.print("Enter rows and cols of Matrix B: ");
        int r2 = sc.nextInt(), c2 = sc.nextInt();
        int[][] B = new int[r2][c2];
        System.out.println("Enter elements of Matrix B:");
        for (int i = 0; i < r2; i++)
            for (int j = 0; j < c2; j++)
                B[i][j] = sc.nextInt();

        if (c1 != r2) {
            System.out.println("Matrix multiplication not possible.");
            return;
        }

        int maxSize = nextPowerOfTwo(Math.max(Math.max(r1, c1), Math.max(r2, c2)));
        int[][] A_pad = padMatrix(A, maxSize);
        int[][] B_pad = padMatrix(B, maxSize);

        long start, end;

        start = System.nanoTime();
        int[][] naive = naiveMultiply(A, B);
        end = System.nanoTime();
        System.out.println("Naive Time: " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        int[][] dac = dacMultiply(A_pad, B_pad);
        end = System.nanoTime();
        System.out.println("Divide and Conquer Time: " + (end - start) / 1e6 + " ms");

        start = System.nanoTime();
        int[][] strassen = strassenMultiply(A_pad, B_pad);
        end = System.nanoTime();
        System.out.println("Strassen Time: " + (end - start) / 1e6 + " ms");

        printMatrix(strassen, r1, c2);
    }
}

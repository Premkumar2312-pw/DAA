import java.util.*;
public class matrix {

    // Naive Method
    public static int[][] naiveMultiply(int[][] A, int[][] B, int m, int n, int p) {
        int[][] C = new int[m][p];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    // Divide and Conquer (Requires Square Matrices)
    public static int[][] divideAndConquerMultiply(int[][] A, int[][] B) {
        int n = A.length;

        if (n == 1) {
            return new int[][]{{A[0][0] * B[0][0]}};
        }

        int mid = n / 2;
        int[][] A11 = new int[mid][mid], A12 = new int[mid][mid], A21 = new int[mid][mid], A22 = new int[mid][mid];
        int[][] B11 = new int[mid][mid], B12 = new int[mid][mid], B21 = new int[mid][mid], B22 = new int[mid][mid];

        // Splitting matrices
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                A11[i][j] = A[i][j];
                A12[i][j] = A[i][j + mid];
                A21[i][j] = A[i + mid][j];
                A22[i][j] = A[i + mid][j + mid];

                B11[i][j] = B[i][j];
                B12[i][j] = B[i][j + mid];
                B21[i][j] = B[i + mid][j];
                B22[i][j] = B[i + mid][j + mid];
            }
        }

        int[][] C11 = addMatrices(divideAndConquerMultiply(A11, B11), divideAndConquerMultiply(A12, B21));
        int[][] C12 = addMatrices(divideAndConquerMultiply(A11, B12), divideAndConquerMultiply(A12, B22));
        int[][] C21 = addMatrices(divideAndConquerMultiply(A21, B11), divideAndConquerMultiply(A22, B21));
        int[][] C22 = addMatrices(divideAndConquerMultiply(A21, B12), divideAndConquerMultiply(A22, B22));

        int[][] C = new int[n][n];

        // Merging the result
        for (int i = 0; i < mid; i++) {
            for (int j = 0; j < mid; j++) {
                C[i][j] = C11[i][j];
                C[i][j + mid] = C12[i][j];
                C[i + mid][j] = C21[i][j];
                C[i + mid][j + mid] = C22[i][j];
            }
        }

        return C;
    }

    // Function to add two matrices
    public static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        printMatrix(C, C.length, C[0].length );
        return C;
    }

    // Function to print a matrix
    public static void printMatrix(int[][] matrix, int rows, int cols) {
        if(matrix.length!=1){
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    }

    // Function to find next power of 2
    public static int nextPowerOf2(int n) {
        int power = 1;
        while (power < n) {
            power *= 2;
        }
        return power;
    }

    // Function to pad the matrix to nearest power of 2
    public static int[][] padMatrix(int[][] matrix, int newSize) {
        int originalRows = matrix.length;
        int originalCols = matrix[0].length;
        int[][] paddedMatrix = new int[newSize][newSize];

        for (int i = 0; i < originalRows; i++) {
            for (int j = 0; j < originalCols; j++) {
                paddedMatrix[i][j] = matrix[i][j];
            }
        }
        return paddedMatrix;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input dimensions for Matrix A (m x n)
        System.out.print("Enter rows (m) and cols (n) for Matrix A: ");
        int m = sc.nextInt();
        int n = sc.nextInt();

        int[][] A = new int[m][n];

        System.out.println("Enter Matrix A:");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = sc.nextInt();
            }
        }

        // Input dimensions for Matrix B (n x p)
        System.out.print("Enter rows (o) and cols (p) for Matrix B: ");
        int o = sc.nextInt();
        int p = sc.nextInt();
        if(o!=n){
            System.out.print("matrix multiplication not possible!");
            return;
        }
        int[][] B = new int[n][p];

        System.out.println("Enter Matrix B:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                B[i][j] = sc.nextInt();
            }
        }

        // Naive Multiplication
        System.out.println("Naive Multiplication Result:");
        printMatrix(naiveMultiply(A, B, m, n, p), m, p);

        // Convert to square matrices for Divide and Conquer
        int maxDim = nextPowerOf2(Math.max(Math.max(m, n), p));

        int[][] paddedA = padMatrix(A, maxDim);
        int[][] paddedB = padMatrix(B, maxDim);
        System.out.println("A : ");
        printMatrix(paddedA, paddedA.length, paddedA[0].length);
        System.out.println("B : ");
        printMatrix(paddedB, paddedB.length, paddedB[0].length);

        // Multiply using Divide and Conquer
        System.out.println("Divide and Conquer Multiplication Result:");
        int[][] paddedResult = divideAndConquerMultiply(paddedA, paddedB);

        // Print only the actual result (m x p)
        printMatrix(paddedResult, m, p);

        sc.close();
    }
}
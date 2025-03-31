import java.util.Scanner;

public class Strassen {

    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }

        }
        return C;
    }

    public static int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public static int[][] strassen(int[][] A, int[][] B) {
        int n = A.length;
        if (n == 1) {
            return new int[][] { { A[0][0] * B[0][0] } };
        }
        
        int mid = n / 2;
        int[][] A11 = new int[mid][mid], A12 = new int[mid][mid], A21 = new int[mid][mid], A22 = new int[mid][mid];
        int[][] B11 = new int[mid][mid], B12 = new int[mid][mid], B21 = new int[mid][mid], B22 = new int[mid][mid];
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
        int[][] P = strassen(add(A11, A22), add(B11, B22));
        int[][] Q = strassen(add(A21, A22), B11);
        int[][] R = strassen(A11, sub(B12, B22));
        int[][] S = strassen(A22, sub(B21, B11));
        int[][] T = strassen(add(A11, A12), B22);
        int[][] U = strassen(sub(A21, A11), add(B11, B12));
        int[][] V = strassen(sub(A12, A22), add(B21, B22));

        int[][] C11 = add(sub(add(P, S), T), V);
        int[][] C12 = add(R, T);
        int[][] C21 = add(Q, S);
        int[][] C22 = add(sub(add(P, R), Q), U);

        int[][] C = new int[n][n];
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

    public static void printMatrix(int[][] matrix, int rows, int cols) {
        if (matrix.length != 1) {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static int nextPowerOf2(int n) {
        int power = 1;
        while (power < n) {
            power *= 2;
        }
        return power;
    }

    public static int[][] padMatrix(int[][] matrix, int newSize) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] padded = new int[newSize][newSize];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                padded[i][j] = matrix[i][j];
            }
        }
        return padded;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
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
        System.out.print("Enter rows (o) and cols (p) for Matrix B: ");
        int o = sc.nextInt();
        int p = sc.nextInt();
        if (o != n) {
            System.out.println("Matrix multiplication not possible!");
            sc.close();
            return;
        }
        int[][] B = new int[o][p];

        System.out.println("Enter Matrix B:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < p; j++) {
                B[i][j] = sc.nextInt();
            }
        }
        int maxDim = nextPowerOf2(Math.max(Math.max(m, n), p));
        int[][] paddedA = padMatrix(A, maxDim);
        int[][] paddedB = padMatrix(B, maxDim);
        System.out.println("A : ");
        printMatrix(paddedA, paddedA.length, paddedA[0].length);
        System.out.println("B : ");
        printMatrix(paddedB, paddedB.length, paddedB[0].length);
        int[][] paddedResult = strassen(paddedA, paddedB);

        printMatrix(paddedResult, m, p);

        sc.close();
    }
}
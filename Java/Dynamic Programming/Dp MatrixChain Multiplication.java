import java.util.*;

class MatrixChainMultiplication { 
    
    static void printDPTable(int[][] dp, int n) { 
        System.out.println("\nDP Table:"); 
        for (int i = 1; i < n; i++) { 
            for (int j = 1; j < n; j++) { 
                if (i > j) 
                    System.out.print("   -   "); 
                else 
                    System.out.printf("%6d ", dp[i][j]); 
            } 
            System.out.println(); 
        } 
    }

    static void printSplitTable(int[][] split, int n) {     
        System.out.println("\nSplit Table:");     
        for (int i = 1; i < n; i++) {         
            for (int j = 1; j < n; j++) {             
                if (i >= j)                 
                    System.out.print("   -   ");             
                else                 
                    System.out.printf("%6d ", split[i][j]);         
            }         
            System.out.println();     
        } 
    }  

    static void printOptimalParens(int[][] split, int i, int j) {     
        if (i == j) {         
            System.out.print("A" + i);         
            return;     
        }     
        System.out.print("(");     
        printOptimalParens(split, i, split[i][j]);     
        printOptimalParens(split, split[i][j] + 1, j);     
        System.out.print(")"); 
    }  

    static int matrixChainOrder(int[] p, int n) {     
        int[][] dp = new int[n][n];     
        int[][] split = new int[n][n];      

        for (int len = 2; len < n; len++) {         
            System.out.println("\nComputing chain length = " + len);         
            for (int i = 1; i < n - len + 1; i++) {             
                int j = i + len - 1;             
                dp[i][j] = Integer.MAX_VALUE;             
                for (int k = i; k < j; k++) {                 
                    int cost = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];                 
                    if (cost < dp[i][j]) {                     
                        dp[i][j] = cost;                     
                        split[i][j] = k;                 
                    }                 
                    System.out.println("dp[" + i + "][" + j + "] -> Choosing k = " + k + ", cost = " + cost);             
                }         
            }         
            printDPTable(dp, n);     
        }      

        printSplitTable(split, n);     
        System.out.print("\nOptimal Parenthesization: ");     
        printOptimalParens(split, 1, n - 1);     
        System.out.println();      

        return dp[1][n - 1]; 
    }  

    public static void main(String[] args) {     
        Scanner sc = new Scanner(System.in);     
        System.out.print("Enter the number of matrices: ");     
        int n = sc.nextInt();     
        int[] p = new int[n + 1];     

        System.out.println("Enter the dimensions: ");     
        for (int i = 0; i <= n; i++) {         
            p[i] = sc.nextInt();     
        }     

        int minCost = matrixChainOrder(p, n + 1);     
        System.out.println("\nMinimum number of multiplications: " + minCost);     
        sc.close(); 
    } 
}

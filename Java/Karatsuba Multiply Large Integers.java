import java.math.BigInteger;
import java.util.Scanner;

public class KaratsubaMultiplication {
    static int step = 1;

    public static String shortVal(BigInteger num) {
        String s = num.toString();
        return s.length() > 10 ? s.substring(0, 5) + "..." + s.substring(s.length() - 5) : s;
    }

    public static BigInteger karatsuba(BigInteger x, BigInteger y) {
        if (x.toString().length() < 2 || y.toString().length() < 2) 
            return x.multiply(y);

        int n = Math.max(x.toString().length(), y.toString().length());
        int m = n / 2;

        BigInteger tenPowM = BigInteger.TEN.pow(m);
        BigInteger high1 = x.divide(tenPowM);
        BigInteger low1 = x.mod(tenPowM);
        BigInteger high2 = y.divide(tenPowM);
        BigInteger low2 = y.mod(tenPowM);

        BigInteger z0 = karatsuba(low1, low2);
        BigInteger z2 = karatsuba(high1, high2);
        BigInteger z1 = karatsuba(high1.add(low1), high2.add(low2)).subtract(z2).subtract(z0);

        BigInteger result = z2.multiply(tenPowM.pow(2)).add(z1.multiply(tenPowM)).add(z0);

        System.out.printf("| %2d | %-12s | %-12s | %-3d | %-3d | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-10s | %-15s |\n",
                step++, shortVal(x), shortVal(y), n, m, shortVal(high1), shortVal(low1),
                shortVal(high2), shortVal(low2), shortVal(z0), shortVal(z2), shortVal(z1), shortVal(result));

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        BigInteger num1 = new BigInteger(scanner.nextLine());

        System.out.print("Enter second number: ");
        BigInteger num2 = new BigInteger(scanner.nextLine());

        System.out.println("\n|Step|      x       |      y       | n  | m  | high1     | low1      | high2     | low2      | z0         | z2         | z1         | Result         |");
        System.out.println("|----|---------------|---------------|----|----|-----------|-----------|-----------|-----------|-------------|-------------|-------------|----------------|");

        BigInteger result = karatsuba(num1, num2);

        System.out.println("\nFinal Product: " + result);

        scanner.close();
    }
}
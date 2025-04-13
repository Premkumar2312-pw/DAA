import java.util.Scanner;

public class RabinKarpMatcher {
    static final int d = 256; // supports all ASCII characters
    static final int Q = 1000000007; // large prime for hashing

    public static void rabinKarp(String text, String pattern, int mod) {
        int n = text.length();
        int m = pattern.length();

        long h = 1;
        long pHash = 0, tHash = 0;
        int spuriousHits = 0;

        // Precompute h = d^(m-1) % mod
        for (int i = 0; i < m - 1; i++)
            h = (h * d) % mod;

        // Initial hash calculation
        for (int i = 0; i < m; i++) {
            pHash = (d * pHash + pattern.charAt(i)) % mod;
            tHash = (d * tHash + text.charAt(i)) % mod;
        }

        // Slide the pattern
        for (int i = 0; i <= n - m; i++) {
            if (pHash == tHash) {
                if (text.substring(i, i + m).equals(pattern)) {
                    System.out.println("Pattern found at index " + i);
                } else {
                    System.out.println("Spurious hit at index " + i);
                    spuriousHits++;
                }
            }
            // Recalculate hash for next window
            if (i < n - m) {
                tHash = (d * (tHash - text.charAt(i) * h) + text.charAt(i + m)) % mod;
                if (tHash < 0) tHash += mod;
            }
        }
        System.out.println("Total spurious hits: " + spuriousHits);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Text: ");
        String text = sc.nextLine();

        System.out.print("Enter Pattern: ");
        String pattern = sc.nextLine();

        System.out.println("\n--- With Q = 10^9+7 ---");
        rabinKarp(text, pattern, 1000000007);

        System.out.println("\n--- With Q = 13 (to count spurious hits) ---");
        rabinKarp(text, pattern, 13);
    }
}

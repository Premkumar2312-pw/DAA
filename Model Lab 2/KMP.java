import java.util.*;

public class KMPSolver {

    static int[] computeLPS(String pattern) {
        int n = pattern.length();
        int[] lps = new int[n];
        int len = 0, i = 1;

        while (i < n) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i++] = len;
            } else if (len != 0) {
                len = lps[len - 1];
            } else {
                lps[i++] = 0;
            }
        }

        return lps;
    }

    static void KMPMatch(String text, String pattern) {
        int[] lps = computeLPS(pattern);
        System.out.print("LPS Array: ");
        for (int x : lps) System.out.print(x);
        System.out.println();

        int i = 0, j = 0;
        int n = text.length(), m = pattern.length();
        boolean found = false;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++; j++;
            }
            if (j == m) {
                System.out.println("Pattern found at index " + (i - j));
                found = true;
                j = lps[j - 1];
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) j = lps[j - 1];
                else i++;
            }
        }

        if (!found)
            System.out.println("Pattern not found in the text.");
    }

    public static void main(String[] args) {
        String pattern = "abababca";
        String text = "bacbababaabcbab";

        System.out.println("Pattern: " + pattern);
        System.out.println("Text   : " + text);
        KMPMatch(text, pattern);
    }
}

import java.util.Scanner;
public class PatternSearching { 
public static void naiveSearch(String text, String pattern) { 
    int n = text.length(), m = pattern.length();
    for (int i = 0; i <= n - m; i++) { 
      int j;
      for (j = 0; j < m; j++)
        if (text.charAt(i + j) != pattern.charAt(j)) 
          break;
      if (j == m) System.out.println("Pattern found at index " + i);
    }
}
public static void rabinKarp(String text, String pattern, int prime) {
    int n = text.length(), m = pattern.length(), hashText = 0, hashPattern = 0, h = 1, d = 256;
    for (int i = 0; i < m - 1; i++) h = (h * d) % prime;
    for (int i = 0; i < m; i++) {
        hashPattern = (d * hashPattern + pattern.charAt(i)) % prime;
        hashText = (d * hashText + text.charAt(i)) % prime;
    }
    for (int i = 0; i <= n - m; i++) {
        if (hashPattern == hashText) {
            int j;
            for (j = 0; j < m; j++)
                if (text.charAt(i + j) != pattern.charAt(j))
                    break;
            if (j == m) System.out.println("Pattern found at index " + i);
        }
        if (i < n - m) {
            hashText = (d * (hashText - text.charAt(i) * h) + text.charAt(i + m)) % prime;
            if (hashText < 0) hashText += prime;
        }
    }
}

public static void knuthMorrisPratt(String text, String pattern) {
    int n = text.length(), m = pattern.length();
    int[] lps = new int[m];
    computeLPS(pattern, lps);
    int i = 0, j = 0;
    while (i < n) {
        if (pattern.charAt(j) == text.charAt(i)) {
            i++;
            j++;
        }
        if (j == m) {
            System.out.println("Pattern found at index " + (i - j));
            j = lps[j - 1];
        } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
            if (j != 0) j = lps[j - 1];
            else i++;
        }
    }
}

private static void computeLPS(String pattern, int[] lps) {
    int length = 0, i = 1;
    while (i < pattern.length()) {
        if (pattern.charAt(i) == pattern.charAt(length)) {
            lps[i++] = ++length;
        } else {
            if (length != 0) length = lps[length - 1];
            else lps[i++] = 0;
        }
    }
}

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter text: ");
    String text = sc.nextLine();
    System.out.print("Enter pattern: ");
    String pattern = sc.nextLine();
    System.out.println("Naive Search:");
    naiveSearch(text, pattern);
    System.out.println("Rabin-Karp Search:");
    rabinKarp(text, pattern, 101);
    System.out.println("Knuth-Morris-Pratt Search:");
    knuthMorrisPratt(text, pattern);
}
 
}
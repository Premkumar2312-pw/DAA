import java.util.Scanner;

public class StringMultiplication {

public static String multiply(String a, String b) {  
    if (a.equals("0") || b.equals("0")) return "0";  

    int n1 = a.length(), n2 = b.length();  
    int[] result = new int[n1 + n2];  

    for (int i = n1 - 1; i >= 0; i--) {  
        int d1 = a.charAt(i) - '0';  
        for (int j = n2 - 1; j >= 0; j--) {  
            int d2 = b.charAt(j) - '0';  
            int sum = d1 * d2 + result[i + j + 1];  

            result[i + j + 1] = sum % 10;  
            result[i + j] += sum / 10;  
        }  
    }  

    StringBuilder sb = new StringBuilder();  
    for (int val : result) {  
        if (sb.length() == 0 && val == 0) continue;  
        sb.append(val);  
    }  

    return sb.toString();  
}  

public static void main(String[] args) {  
    Scanner sc = new Scanner(System.in);  
    String a = sc.next();  
    String b = sc.next();  

    String product = multiply(a, b);  
    System.out.println("The product is: \"" + product + "\"");  
}

} 

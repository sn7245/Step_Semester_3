package Week1.ClassProblems;
import java.util.*;

public class PalindromeChecker {

    static boolean isPalindromeIterative(String text) {
        int i = 0, j = text.length() - 1;

        while (i < j) {
            if (text.charAt(i) != text.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    static boolean isPalindromeRecursive(String text) {
        return check(text, 0, text.length() - 1);
    }

    static boolean check(String text, int left, int right) {
        if (left >= right)
            return true;

        if (text.charAt(left) != text.charAt(right))
            return false;

        return check(text, left + 1, right - 1);
    }

    static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reverse = text.toCharArray();

        int i = 0, j = reverse.length - 1;

        while (i < j) {
            char temp = reverse[i];
            reverse[i] = reverse[j];
            reverse[j] = temp;
            i++;
            j--;
        }

        return Arrays.equals(original, reverse);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        boolean iterative = isPalindromeIterative(text);
        boolean recursive = isPalindromeRecursive(text);
        boolean array = isPalindromeArrayReversal(text);

        System.out.println("Iterative: " +
                (iterative ? "Palindrome" : "Not Palindrome"));

        System.out.println("Recursive: " +
                (recursive ? "Palindrome" : "Not Palindrome"));

        System.out.println("Array Reversal: " +
                (array ? "Palindrome" : "Not Palindrome"));

        sc.close();
    }
}
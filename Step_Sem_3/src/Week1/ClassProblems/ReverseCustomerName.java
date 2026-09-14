package Week1.ClassProblems;
import java.util.*;

public class ReverseCustomerName {

    static String reverseCustomerName(String customerName) {
        char[] arr = customerName.toCharArray();
        String reversed = "";

        for (int i = arr.length - 1; i >= 0; i--)
            reversed += arr[i];

        return reversed;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter customer name: ");
        String customerName = sc.nextLine();

        String reversed = reverseCustomerName(customerName);

        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reversed);

        sc.close();
    }
}
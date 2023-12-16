import java.util.*;

public class q1 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter a non-negative integer: ");

    int number = scanner.nextInt();
    if (number < 0) {
      System.out.println("Please enter a non-negative integer.");
    } else {

      long factorial = calculateFactorial(number);
      System.out.println("The factorial of " + number + " is: " + factorial);
    }

    scanner.close();
  }

  private static long calculateFactorial(int n) {
    if (n == 0 || n == 1) {
      return 1;
    } else {
      return n * calculateFactorial(n - 1);
    }
  }
}

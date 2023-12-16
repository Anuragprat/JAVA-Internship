import java.util.Scanner;

public class t5q2 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the value of 'n': ");
    int n = scanner.nextInt();

    System.out.println("Fibonacci series up to " + n + ":");
    for (int i = 0; i <= n; i++) {
      System.out.print(fibonacci(i) + " ");
    }

    scanner.close();
  }

  private static int fibonacci(int n) {
    if (n <= 1) {
      return n;
    } else {
      return fibonacci(n - 1) + fibonacci(n - 2);
    }
  }
}

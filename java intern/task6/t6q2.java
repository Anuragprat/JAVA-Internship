import java.util.Scanner;

public class t6q2 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the first number: ");
    double num1 = scanner.nextDouble();

    System.out.print("Enter the second number: ");
    double num2 = scanner.nextDouble();

    System.out.print("Enter the third number: ");
    double num3 = scanner.nextDouble();

    double largestNumber = findLargestNumber(num1, num2, num3);

    System.out.println("The largest number is: " + largestNumber);

    scanner.close();
  }

  private static double findLargestNumber(double num1, double num2, double num3) {
    if (num1 >= num2 && num1 >= num3) {
      return num1;
    } else if (num2 >= num1 && num2 >= num3) {
      return num2;
    } else {
      return num3;
    }
  }
}

import java.util.Scanner;

public class t7q2 {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the length of the rectangle: ");
    double length = scanner.nextDouble();

    System.out.print("Enter the width of the rectangle: ");
    double width = scanner.nextDouble();

    scanner.close();

    double area = calculateArea(length, width);
    double perimeter = calculatePerimeter(length, width);

    System.out.println("The area of the rectangle is: " + area);
    System.out.println("The perimeter of the rectangle is: " + perimeter);
  }

  private static double calculateArea(double length, double width) {
    return length * width;
  }

  private static double calculatePerimeter(double length, double width) {
    return 2 * (length + width);
  }
}

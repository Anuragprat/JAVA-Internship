import java.util.Scanner;

public class t4q2 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the size of the array: ");
    int size = scanner.nextInt();

    int[] array = new int[size];

    System.out.println("Enter the elements of the array:");
    for (int i = 0; i < size; i++) {
      System.out.print("Element " + (i + 1) + ": ");
      array[i] = scanner.nextInt();
    }

    int sum = calculateArraySum(array);

    System.out.println("The sum of the array elements is: " + sum);

    scanner.close();
  }

  private static int calculateArraySum(int[] array) {
    int sum = 0;
    for (int value : array) {
      sum += value;
    }
    return sum;
  }
}

import java.util.Scanner;

public class q2 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter the size of the array: ");
    int size = scanner.nextInt();

    if (size <= 0) {
      System.out.println("Please enter a positive size for the array.");

    }

    int[] array = new int[size];

    System.out.println("Enter the elements of the array:");

    for (int i = 0; i < size; i++) {
      System.out.print("Element " + (i + 1) + ": ");
      array[i] = scanner.nextInt();
    }

    int largestElement = findLargestElement(array);

    System.out.println("The largest element in the array is: " + largestElement);

    scanner.close();
  }

  private static int findLargestElement(int[] array) {
    int largest = array[0]; // Assume the first element is the largest

    for (int i = 1; i < array.length; i++) {
      if (array[i] > largest) {
        largest = array[i];
      }
    }

    return largest;
  }
}

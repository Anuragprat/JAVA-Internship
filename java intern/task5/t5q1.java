
import java.util.Scanner;

public class t5q1 {

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

    bubbleSort(array);

    System.out.println("Sorted array in ascending order:");
    for (int element : array) {
      System.out.print(element + " ");
    }

    scanner.close();
  }

  private static void bubbleSort(int[] array) {
    int n = array.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (array[j] > array[j + 1]) {

          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
        }
      }
    }
  }
}

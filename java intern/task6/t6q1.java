import java.util.Scanner;

public class t6q1 {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter a decimal number: ");
    int decimalNumber = scanner.nextInt();

    String binaryEquivalent = convertDecimalToBinary(decimalNumber);

    System.out.println("Binary equivalent: " + binaryEquivalent);

    scanner.close();
  }

  private static String convertDecimalToBinary(int decimalNumber) {
    StringBuilder binary = new StringBuilder();

    while (decimalNumber > 0) {
      int remainder = decimalNumber % 2;
      binary.insert(0, remainder);
      decimalNumber /= 2;
    }

    if (binary.length() == 0) {
      binary.append(0);
    }

    return binary.toString();
  }
}

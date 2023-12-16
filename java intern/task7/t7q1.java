import java.util.Scanner;

public class t7q1 {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter a sentence: ");
    String sentence = scanner.nextLine();

    System.out.print("Enter a word to count: ");
    String wordToCount = scanner.next();

    scanner.close();

    int count = countOccurrences(sentence, wordToCount);

    System.out.println("The word \"" + wordToCount + "\" occurs " + count + " times in the sentence.");
  }

  private static int countOccurrences(String sentence, String word) {

    String[] words = sentence.split(" ");

    int count = 0;

    for (String currentWord : words) {

      if (currentWord.equals(word)) {

        count++;
      }
    }

    return count;
  }
}

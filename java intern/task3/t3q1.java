package task3;

import java.util.*;

public class t3q1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String inputString = scanner.nextLine();

        scanner.close();

        Map<Character, Integer> charFrequencyMap = countCharacterFrequency(inputString);

        System.out.println("Character frequencies:");
        for (Map.Entry<Character, Integer> entry : charFrequencyMap.entrySet()) {
            System.out.println("'" + entry.getKey() + "': " + entry.getValue() + " times");
        }
    }

    private static Map<Character, Integer> countCharacterFrequency(String str) {
        Map<Character, Integer> charFrequencyMap = new HashMap<>();

        char[] charArray = str.toCharArray();

        for (char ch : charArray) {

            charFrequencyMap.put(ch, charFrequencyMap.getOrDefault(ch, 0) + 1);
        }

        return charFrequencyMap;
    }
}

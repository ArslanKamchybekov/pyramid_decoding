import java.io.*;
import java.util.*;

public class DecodeMessage {

    public static String decode(String messageFile) {
        Map<Integer, String> numberToWordMap = new TreeMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(messageFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int number = Integer.parseInt(parts[0]);
                String word = parts[1];
                numberToWordMap.put(number, word);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        List<Integer> endOfLineNumbers = pyramid(numberToWordMap.size());
        StringBuilder decodedMessage = new StringBuilder();
        for (int num : endOfLineNumbers) {
            if (decodedMessage.length() > 0) {
                decodedMessage.append(" ");
            }
            decodedMessage.append(numberToWordMap.get(num));
        }

        return decodedMessage.toString();
    }

    private static List<Integer> pyramid(int totalNumbers) {
        List<Integer> endOfLineNumbers = new ArrayList<>();
        int line = 1;
        int currentNumber = 1;
        while (currentNumber <= totalNumbers) {
            int endOfLineNumber = currentNumber + line - 1;
            if (endOfLineNumber <= totalNumbers) {
                endOfLineNumbers.add(endOfLineNumber);
            }
            currentNumber += line;
            line++;
        }
        return endOfLineNumbers;
    }

    public static void main(String[] args) {
        String decodedMessage = decode("coding_qual_input.txt");
        System.out.println(decodedMessage);
    }
}
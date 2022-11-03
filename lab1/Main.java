import java.util.*;


public class Main {
    private static final Scanner in = new Scanner(System.in);

    private static Boolean getResultTask1(String cleanupLine) {
        String[] numbers = cleanupLine.replaceAll("[^-. \\d]", "").split(" ");
        double minNumber = -Double.MAX_VALUE;
        boolean result = true;

        if (numbers.length == 1 && Objects.equals(numbers[0], ""))
            throw new EmptyStackException();

        for (int i = 0; i < numbers.length; ++i) {
            long dashNumber = numbers[i].chars().filter(ch -> ch == '-').count();
            long pointNumber = numbers[i].chars().filter(ch -> ch == '.').count();

            if (numbers[i].contains("-")) {
                if (numbers[i].indexOf("-") != 0) {
                    numbers[i] = numbers[i].replaceAll("-", "");
                }
                else if (numbers[i].indexOf("-") == 0 && dashNumber > 1) {
                    numbers[i] = "-" + numbers[i].replaceAll("-", "");
                }
            }
            if (numbers[i].contains(".")) {
                int firstPointIdx = numbers[i].indexOf(".");
                if (pointNumber > 1) {
                    numbers[i] = numbers[i].replaceAll("[.]", "");
                    numbers[i] = numbers[i].substring(0, firstPointIdx) + '.' + numbers[i].substring(firstPointIdx);
                }

                if (firstPointIdx == 0)
                    numbers[i] = '0' + numbers[i];
                else if (firstPointIdx == 1)
                    numbers[i] = "-0" + numbers[i].substring(1);
            }

            if (i < numbers.length - 1)
                System.out.printf("%s, ", numbers[i]);
            else
                System.out.printf("%s.\n", numbers[i]);
        }

        for (String num : numbers) {
            double correctValue = Double.parseDouble(num);
            if (correctValue > minNumber) {
                minNumber = correctValue;
            }
            else {
                result = false;
                break;
            }
        }

        return result;
    }

    private static void swapWordsTask2(String[] result, int firstIdx, int secondIdx) {
        String temp = result[firstIdx];
        result[firstIdx] = result[secondIdx];
        result[secondIdx] = temp;
    }

    private static String[] getResultTask2(String[] userWordArray) {
        String[] result = userWordArray.clone();

        for (int firstIdx = 0; firstIdx < result.length - 1; ++firstIdx) {
            for (int secondIdx = firstIdx + 1; secondIdx < result.length; ++secondIdx) {

                if ((result[firstIdx].length() >= 3) && (result[secondIdx].length() >= 3)) {
                    if (result[firstIdx].charAt(2) > result[secondIdx].charAt(2)) {
                        swapWordsTask2(result, firstIdx, secondIdx);
                    }
                }
                else if ((result[firstIdx].length() < 3) && (result[secondIdx].length() < 3)) {
                    int firstCharAt = result[firstIdx].length() - 1;
                    int secondCharAt = result[secondIdx].length() - 1;
                    if (result[firstIdx].charAt(firstCharAt) > result[secondIdx].charAt(secondCharAt)) {
                        swapWordsTask2(result, firstIdx, secondIdx);
                    }
                }
                else if ((result[firstIdx].length() < 3) && (result[secondIdx].length() >= 3)) {
                    int firstCharAt = result[firstIdx].length() - 1;
                    if (result[firstIdx].charAt(firstCharAt) > result[secondIdx].charAt(2)) {
                        swapWordsTask2(result, firstIdx, secondIdx);
                    }
                }
                else if ((result[firstIdx].length() >= 3) && (result[secondIdx].length() < 3)) {
                    int secondCharAt = result[secondIdx].length() - 1;
                    if (result[firstIdx].charAt(2) > result[secondIdx].charAt(secondCharAt)) {
                        swapWordsTask2(result, firstIdx, secondIdx);
                    }
                }
            }
        }

        return result;
    }

    private static String[] getResultTask3(String inputLine) {
        String[] words = inputLine.replaceAll("[,.]", "").split(" ");

        if (words.length == 1 && Objects.equals(words[0], ""))
            throw new EmptyStackException();

        List<String> resultWords = new ArrayList<String>();
        for (String word : words) {
            if (Objects.equals(word, new StringBuffer(word).reverse().toString()))
                resultWords.add(word);
        }
        return resultWords.toArray(new String[0]);
    }

    public static void main(String[] args) {

        System.out.print("Choose task number (1, 2, 3): ");
        int taskNumber = 0;
        try {
            taskNumber = Integer.parseInt(in.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Exception: The string contains not only a number!");
            System.exit(1);
        }

        switch (taskNumber) {
            case (1) -> {
                System.out.print("Input your string: ");
                String userString = in.nextLine();

                try {
                    if (getResultTask1(userString)) {
                        System.out.println("The set of numbers is sorted : )");
                    } else {
                        System.out.println("The set of numbers is NOT sorted :-<");
                    }
                } catch (EmptyStackException e) {
                    System.out.println("Exception: There are no numbers in the string to check the sequence!");
                    System.exit(1);
                }

            }
            case (2) -> {
                System.out.print("Enter words separated by a space: ");
                String[] wordArray = in.nextLine().split(" ");
                String[] resultTask2 = getResultTask2(wordArray);
                System.out.println("Sort result:");

                for (int i = 0; i < resultTask2.length; ++i) {
                    System.out.printf("%d) %s\n", i, resultTask2[i]);
                }

            }
            case (3) -> {
                System.out.print("Enter words: ");
                String inputLine = in.nextLine();
                String[] resultTask3 = new String[0];
                try {
                    resultTask3 = getResultTask3(inputLine);
                } catch (EmptyStackException e) {
                    System.out.println("Exception: Your line is empty!");
                    System.exit(1);
                }

                System.out.print("Result: ");
                if (resultTask3.length != 0) {
                    for (String word : resultTask3) {
                        System.out.printf("%s ", word);
                    }
                    System.out.print('\n');
                }
                else {
                    System.out.println("no palindromes");
                }
            }
            default -> System.out.print("There is no such task number :(");
        }
    }
}
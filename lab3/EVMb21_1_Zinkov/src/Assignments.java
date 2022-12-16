import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Assignments {

    public static String AssignmentFirst(String userInput) {
        int stringCenter = userInput.length() % 2 == 0 ? userInput.length() / 2 : userInput.length() / 2 + 1;
        stringCenter -= 1;

        if (userInput.length() == 0)
            return "Строка пустая";
        if (userInput.charAt(stringCenter) == ' ')
            return "Центральный символ - пробел.";

        int cursor = stringCenter;
        StringBuilder resultWord = new StringBuilder();
        StringBuilder reverseWord = new StringBuilder();

        while (cursor >= 0 && userInput.charAt(cursor) != ' ') {
            reverseWord.append(userInput.charAt(cursor));
            cursor -= 1;
        }

        resultWord.append(reverseWord.reverse());
        cursor = stringCenter + 1;

        while (userInput.charAt(cursor) != ' ') {
            resultWord.append(userInput.charAt(cursor));
            cursor += 1;
        }

        return resultWord.toString();
    }

    public static String AssignmentSecond(String userInput) {
        char[] chars = userInput.toLowerCase().toCharArray();
        String charsForSearch = new String(chars);
        List<Character> imageChars = new ArrayList<Character>(Arrays.asList('.', ',', '-', '=', '!', '?', ':'));

        for (char c = 'a'; c <= 'z' ; ++c)
            imageChars.add(c);
        for (char c = 'а'; c <= 'я'; ++c)
            imageChars.add(c);
        for (char c = '0'; c <= '9'; ++c)
            imageChars.add(c);

        List<Character> result = new ArrayList<Character>();
        for (char c : imageChars) {
            int n = 0;
            for (char aChar : chars) {
                if (aChar == c)
                    ++n;
            }
            double proc = (double) n / userInput.length() * 100;
            if (proc < 10d && charsForSearch.indexOf(c) != -1) {
                result.add(c);
            }

        }

        StringBuilder stringResult = new StringBuilder();
        for (int i = 0; i < result.size(); ++i) {
            if (i < result.size() - 1)
                stringResult.append(String.format("\"%c\", ", result.get(i)));
            else
                stringResult.append(String.format("\"%c\".", result.get(i)));
        }

        return stringResult.toString();
    }

    public static String AssignmentThird(String userInput) {
        String[] words = userInput.replaceAll("[.,]", "").split(" ");
        List<String> resultWords = new ArrayList<String>();
        String firstWord = words[0];
        for (int i = 1; i < words.length; ++i) {
            if (words[i].length() == firstWord.length())
                resultWords.add(words[i]);
        }

        StringBuilder stringResult = new StringBuilder();
        if (!resultWords.isEmpty()) {
            for (int i = 0; i < resultWords.size(); ++i) {
                if (i < resultWords.size() - 1)
                    stringResult.append(String.format("\"%s\", ", resultWords.get(i)));
                else
                    stringResult.append(String.format("\"%s\".", resultWords.get(i)));
            }
        }
        else {
            stringResult.append("В предложении не было найдено слов, которые были бы равны по длине первому.");
        }

        return stringResult.toString();
    }
}

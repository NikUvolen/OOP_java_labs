import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public final class Utils {
    private static final Scanner in = new Scanner(System.in);


    public static String Input(String textForPrint, Boolean ln) {
        if (ln) {
            System.out.println(textForPrint);
        }
        else {
            System.out.print(textForPrint);
        }
        String userInput;
        userInput = in.nextLine();
        return userInput;
    }

    public static int IntInput(String textForPrint, Boolean ln) {
        String number = Input(textForPrint, ln);
        return Integer.parseInt(number.trim());
    }

    private static String getSymbolsText(char wrappingSymbol, byte numOfChr) {
        StringBuilder result = new StringBuilder();

        for (byte i = 0; i <= numOfChr; ++i) {
            result.append(wrappingSymbol);
        }

        return result.toString();
    }

    public static String wrapText(String textForWrap, char wrappingSymbol, byte numOfChr, int option) {
        // Set option:
        // 0 - wrap left and right
        // 1 - wrap only left
        // 2 - wrap only right

        numOfChr = numOfChr < 0 ? (byte)Math.abs(numOfChr) : numOfChr;
        option = Math.min(option, 2);
        option = Math.max(option, 0);
        String symbolsText = getSymbolsText(wrappingSymbol, numOfChr);


        if (option == 0)
            return symbolsText + textForWrap + symbolsText + '\n';
        else if (option == 1)
            return symbolsText + textForWrap + '\n';
        else
            return textForWrap + symbolsText + '\n';
    }

    private static void writeToFile(String path, String textToWrite) throws IOException {
        File file = new File(path);
        file.createNewFile();

        try (FileWriter fileOut = new FileWriter(path)) {
            fileOut.write(textToWrite);
        }
    }

    public static void printOrSaveToFile(String path, boolean isSaveOn, String text) throws IOException {
        if (isSaveOn) {
            System.out.println("Результат был сохранён в файл.");
            writeToFile(path, text);
        }
        else {
            System.out.print(text);
        }
    }
}

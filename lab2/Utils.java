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
}

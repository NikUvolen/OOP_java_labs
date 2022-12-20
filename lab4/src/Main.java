import AbstractClasses.Home;
import AbstractClasses.Human;
import Humans.*;

import java.awt.event.HierarchyBoundsAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
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
            return symbolsText + textForWrap + symbolsText;
        else if (option == 1)
            return symbolsText + textForWrap;
        else
            return textForWrap + symbolsText;
    }

    private static void printResults(Home home) {
        System.out.println();
        System.out.println(wrapText("Итоги года", '+', (byte) 8, 0));
        System.out.println();
        System.out.printf("Съедено еды людьми: %d\n", home.getEatenFood());
        System.out.printf("Съедено еды котиком: %d\n", home.getEatenByCat());
        System.out.printf("Съедено еды пёселем: %d\n", home.getEatenByDog());
        System.out.printf("Заработано денег: %d\n", home.getEarnedMoney());
        System.out.printf("Куплено шуб: %d\n", home.getFurCoatsBought());
        System.out.printf("Погладили котика: %d\n", home.getPettedCat());
        System.out.printf("Погладили поселя: %d\n", home.getPettedDog());
    }

    private static void printHusbandResult(Husband husband) {
        System.out.printf("Получено премий мужем: %d\n", husband.getBonusesReceived());
        System.out.printf("Сыграно в компьютер мужем: %d\n", husband.getPlayedOnPc());
    }

    private static void printWifeResult(Wife wife) {
        System.out.printf("Убрано в доме женой: %d\n", wife.getTotalCleanings());
        System.out.printf("Походов на шоппинг женой: %d\n", wife.getShoppingTrips());
    }

    public static void main(String[] args) {
        Home home = new Home();
        Husband husband = new Husband("Василий", home);
        Wife wife = new Wife("Маша", home);
        List<Human> humans = new ArrayList<Human>(Arrays.asList(husband, wife));

        for (int i=1; i < 366; ++i) {
            System.out.println(wrapText(String.format("День %d", i), '=', (byte) 8, 0));
            for (Human human : humans) {
                if (human.getFullness() <= 0)
                    // TODO: поправить ошибку с удалением из массива
                    humans.remove(human);
                else
                    human.act();
            }

            System.out.println(wrapText("--", '-', (byte) 8, 0));
            for (Human human : humans)
                System.out.println(human.getStr());
        }

        printResults(home);
        printHusbandResult(husband);
        printWifeResult(wife);
    }
}
import AbstractClasses.Creature;
import AbstractClasses.Home;
import Humans.Child;
import Humans.Husband;
import Humans.Wife;
import Pets.Cat;
import Pets.Dog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

final class MarkChoiceResult {
    private final int markResult;
    private final boolean isValid;
    private final String cmdCommand;


    public MarkChoiceResult(int markResult, boolean isValid, String cmdCommand) {
        this.markResult = markResult;
        this.isValid = isValid;
        this.cmdCommand = cmdCommand;
    }

    public int getMarkResult() {
        return markResult;
    }

    public boolean getValidityCheck() {
        return isValid;
    }

    public String getCmdCommand() {
        return cmdCommand;
    }
}

final public class FamilyLife {
    static int days = 365;
    static boolean child = false;
    static boolean cat = false;
    static boolean dog = false;

    private static MarkChoiceResult isCmdCommandValid(String textForPrint, String exitText) {
        String cmdCommand = Utils.Input(textForPrint, false);
        boolean isValidResult;
        int intResult = -1;

        try {
            if (!cmdCommand.equals(exitText)) {
                intResult = Integer.parseInt(cmdCommand);
                isValidResult = true;
            }
            else {
                isValidResult = false;
            }
        }
        catch (NumberFormatException exc) {
            System.out.println("! Невалидное значение выбора. Введите ещё раз.");
            isValidResult = false;
        }

        return new MarkChoiceResult(intResult, isValidResult, cmdCommand);
    }

    public static void printResults(Home home, Husband husband, Wife wife) {
        System.out.println();
        System.out.println(Utils.wrapText("Итоги года", '+', (byte) 8, 0));
        System.out.println();
        printHusbandResult(husband);
        printWifeResult(wife);
        System.out.printf("Съедено еды людьми: %d\n", home.getEatenFood());
        System.out.printf("Съедено еды животными: %d\n", home.getEatenByPet());
        System.out.printf("Заработано денег: %d\n", home.getEarnedMoney());
        System.out.printf("Куплено шуб: %d\n", home.getFurCoatsBought());
        System.out.printf("Погладили котика: %d\n", home.getPettedCat());
        System.out.printf("Погладили поселя: %d\n", home.getPettedDog());
        System.out.printf("Всего испорчено мебели животными: %d\n", home.getDamagedFurniture());
    }

    private static void printHusbandResult(Husband husband) {
        System.out.printf("Получено премий мужем: %d\n", husband.getBonusesReceived());
        System.out.printf("Сыграно в компьютер мужем: %d\n", husband.getPlayedOnPc());
    }

    private static void printWifeResult(Wife wife) {
        System.out.printf("Убрано в доме женой: %d\n", wife.getTotalCleanings());
        System.out.printf("Походов на шоппинг женой: %d\n", wife.getShoppingTrips());
    }

    private static String isExist(boolean exist) {
        if (exist)
            return "Присутствует";
        else
            return "Отсутствует";
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println(Utils.wrapText("Главное меню", '+', (byte) 8, 0));
        System.out.printf("1) Ребёнок (%s)\n", isExist(child));
        System.out.printf("2) Кот (%s)\n", isExist(cat));
        System.out.printf("3) Собака (%s)\n", isExist(dog));
        System.out.printf("4) Количество дней (сейчас: %d)\n", days);
        System.out.println("5) Запуск!");
        System.out.println(Utils.wrapText("++", '+', (byte) 13, 0));
        System.out.println("Введите \"exit\" для выхода.");
        System.out.println(Utils.wrapText("++", '+', (byte) 13, 0));
        System.out.println();
    }

    public static void run() {
        String cmdCommand = "";
        String exitText = "exit";
        String wrongChoiceAnswer = "!!! %d путкта не существует. Введите ещё раз. !!!\n";

        while (!cmdCommand.equals(exitText)) {
            printMainMenu();

            MarkChoiceResult validResult = isCmdCommandValid("Введите команду: ", exitText);
            cmdCommand = validResult.getCmdCommand();
            if (!validResult.getValidityCheck())
                continue;

            if (validResult.getMarkResult() > 5 || validResult.getMarkResult() < 1) {
                System.out.printf(wrongChoiceAnswer, validResult.getMarkResult());
            }
            if (validResult.getMarkResult() == 1) {
                child = !child;
            }
            else if (validResult.getMarkResult() == 2) {
                cat = !cat;
            }
            else if (validResult.getMarkResult() == 3) {
                dog = !dog;
            }
            else if (validResult.getMarkResult() == 4) {
                try {
                    days = Utils.IntInput("Введите количество дней: ", false);
                } catch (NumberFormatException exc) {
                    System.out.println("Вы введи некорректное значение. Введите ещё раз");
                }
            }
            else if (validResult.getMarkResult() == 5) {
                startLife();
            }
        }
    }

    private static void startLife() {
        Home home = new Home();
        Husband husband = new Husband("Василий", home);
        Wife wife = new Wife("Мария", home);

        List<Creature> family = new ArrayList<Creature>(Arrays.asList(husband, wife));

        if (child)
            family.add(new Child("Никита", home));
        if (cat)
            family.add(new Cat("Мурзик", "котик", home));
        if (dog)
            family.add(new Dog("Джек", "пёсель", home));

        for (int i=1; i < days + 1; ++i) {
            System.out.println(Utils.wrapText(String.format("День %d", i), '=', (byte) 8, 0));
            for (Creature member : family) {
                member.act();
            }

            System.out.println(Utils.wrapText("--", '-', (byte) 8, 0));
            for (Creature member : family)
                System.out.println(member.getStr());
        }

        printResults(home, husband, wife);

        System.out.print("\n" + Utils.wrapText("++", '+', (byte) 8, 0) + "\n");
        Utils.Input("Введите любой символ или enter, чтобы выйти в меню: ", false);
        System.out.print(Utils.wrapText("++", '+', (byte) 8, 0) + "\n");
    }
}

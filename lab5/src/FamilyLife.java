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
}

final public class FamilyLife {
    private static int days = 365;

    private static String getResultsSim(Home home, Husband husband, Wife wife) {
        String resultString = String.format(
                "%s\nПолучено премий мужем: %d\nСыграно в компьютер мужем: %d\n" +
                        "Убрано в доме женой: %d\nПоходов на шоппинг женой: %d\n" +
                        "Съедено еды людьми: %d\nЗаработано денег: %d\nКуплено шуб: %d\n",
                Utils.wrapText("Итоги года", '+', (byte) 8, 0),
                husband.getBonusesReceived(), husband.getPlayedOnPc(),
                wife.getTotalCleanings(), wife.getShoppingTrips(),
                home.getEatenFood(), home.getEarnedMoney(), home.getFurCoatsBought()
        );
        if (home.isHaveCat())
            resultString += String.format("Погладили котика: %d\n", home.getPettedCat());
        if (home.isHaveDog())
            resultString += String.format("Погладили песеля: %d\n", home.getPettedDog());
        if (home.isHaveCat() || home.isHaveDog())
            resultString += String.format("Съедено еды животными: %d\nВсего испорчено мебели животными: %d\n",
                    home.getEatenByPet(), home.getDamagedFurniture());
        return resultString;
    }

    public static void startSimUI(int daysC) {
        days = daysC;
        GUI.logsOutput.setText("");
        GUI.resultOutput.setText(startLife());
    }

    private static String startLife() {
        Home home = new Home(GUI.childCheckBox.isSelected(), GUI.catCheckBox.isSelected(), GUI.dogCheckBox.isSelected());
        Husband husband = new Husband("Василий", home, GUI.logsOutput);
        Wife wife = new Wife("Мария", home, GUI.logsOutput);

        List<Creature> family = new ArrayList<>(Arrays.asList(husband, wife));

        if (home.isHaveChild())
            family.add(new Child("Никита", home, GUI.logsOutput));
        if (home.isHaveCat())
            family.add(new Cat("Мурзик", "котик", home, GUI.logsOutput));
        if (home.isHaveDog())
            family.add(new Dog("Джек", "пёс", home, GUI.logsOutput));

        for (int i=1; i < days + 1; ++i) {
            GUI.logsOutput.append(Utils.wrapText(String.format("День %d", i), '=', (byte) 8, 0));
            for (Creature member : family) {
                member.act();
            }

            GUI.logsOutput.append(Utils.wrapText("--", '-', (byte) 8, 0));
            for (Creature member : family)
                GUI.logsOutput.append(member.getStr() + '\n');
        }

        return getResultsSim(home, husband, wife);

//        System.out.print("\n" + Utils.wrapText("++", '+', (byte) 8, 0) + "\n");
//        Utils.Input("Введите любой символ или enter, чтобы выйти в меню: ", false);
//        System.out.print(Utils.wrapText("++", '+', (byte) 8, 0) + "\n");
    }
}

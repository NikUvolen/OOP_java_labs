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

public class L311 {

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


    // TODO: Перенести блоки из switch в класс с Menu
    // TODO: Реализовать выбор вывода на консоль или в текстовый файл
    // TODO: Написать отчёт и отправить

    public static void main(String[] args) {
        String cmdCommand = "";
        String exitText = "exit";
        String wrongChoiceAnswer = "!!! %d путкта не существует. Введите ещё раз. !!!\n";
        String textForPrint = "Сделайте выбор дальнейших действий: ";
        String resultTemplate = "!!! Резултат выполнения: %s\n";
        String cmdCommandExs;

        while (!cmdCommand.equals(exitText)) {
            cmdCommandExs = "";
            Menu.printMainMenu(exitText);
            int markNumber;

            MarkChoiceResult validResult = isCmdCommandValid("Введите команду: ", exitText);
            cmdCommand = validResult.getCmdCommand();
            if (validResult.getValidityCheck())
                markNumber = validResult.getMarkResult();
            else
                continue;

            if (validResult.getMarkResult() > 3 || validResult.getMarkResult() < 1) {
                System.out.printf(wrongChoiceAnswer, validResult.getMarkResult());
                continue;
            }

            while (!cmdCommandExs.equals(exitText)) {
                int userChoice;

                switch (markNumber) {
                    case (1):
                        int numberOfPointMenu = Menu.printEx1Menu();

                        MarkChoiceResult exResult = isCmdCommandValid(textForPrint, exitText);
                        cmdCommandExs = exResult.getCmdCommand();
                        if (!exResult.getValidityCheck())
                            continue;

                        if (exResult.getMarkResult() > numberOfPointMenu || exResult.getMarkResult() < 1) {
                            System.out.printf(wrongChoiceAnswer, exResult.getMarkResult());
                        }
                        else if (exResult.getMarkResult() == 1) {
                            String userInput = Utils.Input("Введите предложение: ", false);
                            System.out.printf(resultTemplate, Assignments.AssignmentFirst(userInput));
                        }
                        else {
                            System.out.printf(resultTemplate, Assignments.AssignmentFirst("Привет, мир!"));
                        }

                        break;
                    case(2):
                        numberOfPointMenu = Menu.printEx2Menu();

                        exResult = isCmdCommandValid(textForPrint, exitText);
                        cmdCommandExs = exResult.getCmdCommand();
                        if (!exResult.getValidityCheck())
                            continue;

                        if (exResult.getMarkResult() > numberOfPointMenu || exResult.getMarkResult() < 1) {
                            System.out.printf(wrongChoiceAnswer, exResult.getMarkResult());
                            continue;
                        }
                        else if (exResult.getMarkResult() == 1) {
                            String userInput = Utils.Input("Введите предложение: ", false);

                            System.out.printf(resultTemplate, Assignments.AssignmentSecond(userInput));
                        }
                        else {
                            System.out.printf(resultTemplate, Assignments.AssignmentSecond("Привет, мир!"));
                        }

                        break;
                    case(3):
                        numberOfPointMenu = Menu.printEx3Menu();

                        exResult = isCmdCommandValid(textForPrint, exitText);
                        cmdCommandExs = exResult.getCmdCommand();
                        if (!exResult.getValidityCheck())
                            continue;

                        if (exResult.getMarkResult() > numberOfPointMenu || exResult.getMarkResult() < 1) {
                            System.out.printf(wrongChoiceAnswer, exResult.getMarkResult());
                        }
                        else if (exResult.getMarkResult() == 1) {
                            String userInput = Utils.Input("Введите предложение: ", false);
                            System.out.printf(resultTemplate, Assignments.AssignmentThird(userInput));
                        }
                        else {
                            System.out.printf(resultTemplate, Assignments.AssignmentThird("Привет, мир!"));
                        }

                        break;
                    default:
                        break;
                }
            }
        }
    }
}
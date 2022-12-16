import java.io.*;

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


    public static void main(String[] args) throws IOException {
        String cmdCommand = "";
        String exitText = "exit";
        String wrongChoiceAnswer = "!!! %d путкта не существует. Введите ещё раз. !!!\n";
        String textForPrint = "Сделайте выбор дальнейших действий: ";
        String resultTemplate = "!!! Результат выполнения %d задания: %s\n";
        String cmdCommandExs;
        String pathToSave = "result.txt";
        boolean isSaveToFile = false;

        while (!cmdCommand.equals(exitText)) {
            cmdCommandExs = "";
            Menu.printMainMenu(exitText, isSaveToFile);
            int markNumber;

            MarkChoiceResult validResult = isCmdCommandValid("Введите команду: ", exitText);
            cmdCommand = validResult.getCmdCommand();
            if (validResult.getValidityCheck())
                markNumber = validResult.getMarkResult();
            else
                continue;

            if (validResult.getMarkResult() > 4 || validResult.getMarkResult() < 1) {
                System.out.printf(wrongChoiceAnswer, validResult.getMarkResult());
                continue;
            }
            if (validResult.getMarkResult() == 1) {
                isSaveToFile = !isSaveToFile;
                continue;
            }

            while (!cmdCommandExs.equals(exitText)) {
                int userChoice;
                String result;

                switch (markNumber) {
                    case (2):
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
                            result = String.format(
                                    resultTemplate,
                                    markNumber,
                                    Assignments.AssignmentFirst(userInput)
                            );
                            Utils.printFileOrSave(pathToSave, isSaveToFile, result);
                        }
                        else {
                            result = String.format(
                                    resultTemplate,
                                    markNumber,
                                    Assignments.AssignmentFirst("Привет, мир!")
                            );
                            Utils.printFileOrSave(pathToSave, isSaveToFile, result);
                        }

                        break;
                    case(3):
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

                            result = String.format(
                                    resultTemplate,
                                    markNumber,
                                    Assignments.AssignmentSecond(userInput)
                            );
                            Utils.printFileOrSave(pathToSave, isSaveToFile, result);
                        }
                        else {
                            result = String.format(
                                    resultTemplate,
                                    markNumber,
                                    Assignments.AssignmentSecond("Привет, мир!")
                            );
                            Utils.printFileOrSave(pathToSave, isSaveToFile, result);
                        }

                        break;
                    case(4):
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
                            result = String.format(
                                    resultTemplate,
                                    markNumber,
                                    Assignments.AssignmentThird(userInput)
                            );
                            Utils.printFileOrSave(pathToSave, isSaveToFile, result);
                        }
                        else {
                            result = String.format(
                                    resultTemplate,
                                    markNumber,
                                    Assignments.AssignmentThird("Привет, мир!")
                            );
                            Utils.printFileOrSave(pathToSave, isSaveToFile, result);
                        }

                        break;
                    default:
                        break;
                }
            }
        }
    }
}
import java.util.List;

class Main {

    public static void main(String[] args) {
        int assignmentChoice = 0;
        try {
            assignmentChoice = Utils.IntInput("Введите номер задания: ", false) - 1;
        }
        catch (NumberFormatException exception) {
            System.out.println("EXCEPTION: Номер задания был введен некорректно.\n" +
                    "! Было выбрано 1 задание по умолчанию !");
        }

        if (assignmentChoice == 0) {
            String userInput = Utils.Input("Введите предложение: ", false);
            String result = Assignments.AssignmentFirst(userInput);
            System.out.printf("Результат: %s\n", result);
        }
        else if (assignmentChoice == 1) {
            String userInput = Utils.Input("Введите предложение: ", false);
            List<Character> result = Assignments.AssignmentSecond(userInput);
            if (!result.isEmpty()) {
                System.out.print("Символы, встречающиеся в тексте менее 10%: ");
                for (int idx = 0; idx < result.size(); ++idx) {
                    System.out.printf("\"%c\"", result.get(idx));
                    if (idx == result.size() - 1)
                        System.out.print(".");
                    else
                        System.out.print(", ");
                }
            }
            else {
                System.out.println("Результат: Строка пустая или слишком мало символов.");
            }
        }
        else if (assignmentChoice == 2) {
            String userInput = Utils.Input("Введите предложение: ", false);
            List<String> result = Assignments.AssignmentThird(userInput);
            if (!result.isEmpty()) {
                System.out.print("Результат выполнения 3 задания:\n");
                for (String word : result) {
                    System.out.println(word);
                }
            }
            else {
                System.out.println("Результат: Не было найдено подходящих слов");
            }

        }
        else {
            System.out.printf("! Такого задания под номером %d не было найдено...", assignmentChoice + 1);
        }
    }

}
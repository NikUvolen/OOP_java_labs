public final class Menu {
    private static void basePrintMenu(String title, String[] menuMarks) {
        System.out.print("\n");
        System.out.println(Utils.wrapText(title, '=', (byte) 8, 0));

        for (int i = 0; i < menuMarks.length; ++i) {
            System.out.printf("%d) %s\n", i + 1, menuMarks[i]);
        }


        System.out.println(Utils.wrapText("==", '=', (byte) 9, 0));
    }

    public static void printMainMenu(String exitText, boolean isSaveToFile) {
        String[] mainMenuMarks = new String[] {
                "Включить/выключить вывод в файл (сейчас: %s)", "Задание 1", "Задание 2", "Задание 3"
        };

        if (isSaveToFile)
            mainMenuMarks[0] = String.format(mainMenuMarks[0], "включено");
        else
            mainMenuMarks[0] = String.format(mainMenuMarks[0], "выключено");

        basePrintMenu("Main menu", mainMenuMarks);

        System.out.printf("Ввод \"%s\" возвращает на пункт выше или завершает выполнение программы.\n", exitText);
        System.out.println(Utils.wrapText("==", '=', (byte) 9, 0));
        System.out.print("\n");
    }

    public static int printEx1Menu() {
        String[] ex1MenuMarks = new String[] {
                "Ввести предложение",
                "Оставить базовое предложение: Привет, мир!"
        };

        basePrintMenu("EX 1", ex1MenuMarks);
        System.out.print("\n");
        return ex1MenuMarks.length;
    }

    public static int printEx2Menu() {
        String[] ex2MenuMarks = new String[] {
                "Ввести предложение",
                "Оставить базовое предложение: Привет, мир!"
        };

        basePrintMenu("EX 2", ex2MenuMarks);
        System.out.print("\n");
        return ex2MenuMarks.length;
    }

    public static int printEx3Menu() {
        String[] ex3MenuMarks = new String[] {
                "Ввести предложение",
                "Оставить базовое предложение: Привет, мир!"
        };

        basePrintMenu("EX 3", ex3MenuMarks);
        System.out.print("\n");
        return ex3MenuMarks.length;
    }
}

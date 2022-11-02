import java.util.Scanner;

public class MenuCaloriesCounter {
    private static Scanner scanner = new Scanner(System.in);
    private static final String MENU = System.lineSeparator() + "Выберите пункт меню: " + System.lineSeparator()
            + "1. Ввести количество шагов за определённый день;" + System.lineSeparator()
            + "2. Напечатать статистику за определённый месяц;" + System.lineSeparator()
            + "3. Изменить цель по количеству шагов в день;" + System.lineSeparator()
            + "4. Выйти из приложения." + System.lineSeparator();
    private StepTracker stepTracker = new StepTracker();


    public void start() {
        stepTracker.setScanner(scanner);

        while (true) {
            System.out.println(MENU);
            int userInput = scanner.nextInt();
            //обработка разных случаев
            switch (userInput) {
                case 1 ->
                    //ввод количества шагов за определенный день;
                        stepTracker.enterCountSteps();
                case 2 ->
                    //получение статистики за определенный месяц
                        stepTracker.printStatistic();
                case 3 ->
                    //изменить цель по количеству шагов в день
                        stepTracker.changeGoalSteps();
                case 4 -> {
                    System.out.println("Программа завершена");
                    return;
                }
                default -> System.out.println("Ввод некорректен. Повторите попытку.");
            }
        }
    }
}

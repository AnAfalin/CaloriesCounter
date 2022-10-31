import java.util.Scanner;

public class StepTracker {
    private int goalSteps = 10_000;     //цель по умолчанию
    private MonthData[] monthToData;
    private static Scanner scanner = new Scanner(System.in);

    private class MonthData {
        String title;
        int[] days;

        public MonthData(String title) {
            this.title = title;
            days = new int[30];
        }
    }

    //конструктор класса
    public StepTracker() {
        monthToData = new MonthData[12];
        //monthToData[0] - январь
        //monthToData[1] - февраль ...
        fillMonthData();
    }

    //метод изменения цели шагов
    public void changeGoalSteps() {
        System.out.println("Введите новое значение цели количества шагов");
        int newValueGoalSteps = scanner.nextInt();
        if ((newValueGoalSteps < 0)) {
            System.out.println("Вы ввели отрицательное значение. Новая цель шагов не установлена.");
            return;
        }
        goalSteps = newValueGoalSteps;
        System.out.println("Новое значение количества шагов в день " + goalSteps + " установлено");
    }

    //ввод количества шагов за определенный день
    public void enterCountSteps() {

        int indexMonth;
        int indexDay;
        int countSteps;

        System.out.println("Введите номер месяца");
        while (true) {
            int numberMonth = scanner.nextInt();
            if (numberMonth >= 1 && numberMonth <= 12) {
                indexMonth = numberMonth - 1;
                break;
            }
            System.out.println("Вы ввели некорректный номер месяца. Повторите ввод");
        }

        System.out.println("Введите день");
        while (true) {
            int numberDay = scanner.nextInt();
            if (numberDay >=1 && numberDay <= 30) {
                indexDay = numberDay - 1;
                break;
            }
            System.out.println("Вы ввели некорректный день месяца. Повторите ввод");
        }

        System.out.println("Введите количество шагов");
        while (true) {
            countSteps = scanner.nextInt();
            if(countSteps > 0){
                break;
            }
            System.out.println("Вы ввели некорректное число шагов. Повторите ввод");
        }

        saveData(indexMonth, indexDay, countSteps);
    }

    public void printStatistic(){
        System.out.println("Введите номер месяца");
        while (true){
            int month = scanner.nextInt();
            if(month >= 1 && month <= 12){
                printStatisticMonth(month - 1);
                break;
            }
            System.out.println("Вы ввели некорректный номер месяца. Повторите ввод");
        }
    }

    private void fillMonthData() {
        String[] titleMonth = new String[]{
                "Январь", "Февраль", "Март", "Апрель", "Май", "Июнь", "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData(titleMonth[i]);
        }
    }

    private void printStatisticMonth(int indexMonth){
        int[] daysMonth = monthToData[indexMonth].days;
        int countStepMonth = 0;         //6000
        int maxStepMonth = daysMonth[0];   //3000

        for (int day : daysMonth) {
            countStepMonth = countStepMonth + day;  //countMonthStep +=day
            if(day > maxStepMonth) {
                maxStepMonth = day;
            }
        }

        long averageNumberStep = Math.round((double) countStepMonth / daysMonth.length);
        long distance = Converter.stepToKilometers(countStepMonth);
        long kilocalories = Converter.stepToKilocalories(countStepMonth);
        int countBestDays = bestSeriesDays(indexMonth);

        System.out.println(monthToData[indexMonth].title);
        System.out.println("Количество пройденных шагов по дням: ");
        printMonthCountSteps(indexMonth);
        System.out.println("Общее количество шагов за месяц: "  + countStepMonth);
        System.out.println("Максимальное пройденное количество шагов в месяце: " + maxStepMonth);
        System.out.println("Среднее количество шагов: " + averageNumberStep);
        System.out.println("Пройденная дистанция (в км): " + distance);
        System.out.println("Количество сожжённых килокалорий: " + kilocalories);
        System.out.println("Лучшая серия: максимальное количество подряд идущих дней, " +
                "в течение которых количество шагов за день было равно или выше целевого : " + countBestDays);

    }

    private void saveData(int numberMonth, int numberDay, int countSteps){
        MonthData month = monthToData[numberMonth]; //получили месяц по номеру
        int[] days = month.days; //получили массив дней месяца
        days[numberDay] = countSteps;   //сохраняем количество шагов в указанном дне
    }

    private int bestSeriesDays(int indexMonth){
        int[] daysMonth = monthToData[indexMonth].days;
        int bestDaysSeries = 0; //переменная лучшая серии дней за месяц
        int currentBestSeries = 0; //текущая серия дней за месяц
        for (int i = 0; i < daysMonth.length; i++) {
            if(daysMonth[i] >= goalSteps){
                currentBestSeries ++;
            }else {
                if(currentBestSeries > bestDaysSeries){
                    bestDaysSeries = currentBestSeries;
                }
                currentBestSeries = 0;
            }
        }
        return bestDaysSeries;
    }

    private void printMonthCountSteps(int indexMonth){
        int[] daysMonth = monthToData[indexMonth].days;
        for (int i = 0; i < daysMonth.length; i++) {
            System.out.print(i + 1 + " день: " + daysMonth[i]);
            if(i != daysMonth.length - 1){
                System.out.print(", ");
            }else {
                System.out.println();
            }
        }
    }
    
}




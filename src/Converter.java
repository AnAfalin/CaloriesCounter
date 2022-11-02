/*
В этом классе осуществляется преобразование шагов в километры и калории.
•	Для подсчёта дистанции можно считать, что один шаг равен 75 см.
•	Для подсчёта количества сожжённых килокалорий можно считать, что 1 шаг = 50 калорий, 1 килокалория = 1 000 калорий.
 */
public class Converter {
    private static final int CENTIMETER_ONE_STEP = 75;
    private static final int KILOMETERS_ONE_METRE = 1000;
    private static final int METERS_ONE_CENTIMETRE = 100;
    private static final int CALORIE_ONE_STEP = 50;
    private static final int KILOCALORIES = 1000;

    public static long getKilometers(int countStep){
        return Math.round((double) countStep * CENTIMETER_ONE_STEP/ METERS_ONE_CENTIMETRE / KILOMETERS_ONE_METRE);
    }

    public static long getCalories(int countStep){
        return Math.round((double) countStep * CALORIE_ONE_STEP / KILOCALORIES);
    }

}

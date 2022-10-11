package UI;

import Controllers.SortByConsecutiveYearController;
import Domain.*;
import Utils.*;

import java.util.*;

public class SortByConsecutiveYearUI implements Runnable{

    private final SortByConsecutiveYearController controller = new SortByConsecutiveYearController();


    @Override
    public void run() {
        System.out.printf("-------------------------------%n   Sort by Consecutive Years         %n--------------------------------%n%n");
        Map<Fruit, Map<Country, Map<Year, Quantity>>> fruitHarvest = controller.getFruitHarvest();
        List<Fruit> fruits = new ArrayList<>(fruitHarvest.keySet());
        int index = Utils.showAndSelectIndex(fruits, "Available Fruits");

        Map<Country, Integer> consecutiveYearsMap = controller.sortByConsecutiveYears(fruits.get(index));

        System.out.printf("----------                                               ----------%n");
        System.out.printf("%-60s%s%n", " Country", "Years");
        System.out.printf("----------                                               ----------%n");

        int line = 1;
        for (Map.Entry<Country, Integer> entry : consecutiveYearsMap.entrySet()) {
            System.out.printf("%d - %-58s%d%n", line, entry.getKey(), entry.getValue());
            line++;
        }
    }
}

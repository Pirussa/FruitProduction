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

        System.out.printf("%-60s%s%n", "Country", "Years");
        for (Map.Entry<Country, Integer> entry : consecutiveYearsMap.entrySet()) {
            System.out.printf("%-60s%d%n", entry.getKey(), entry.getValue());
        }
    }
}

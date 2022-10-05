package Controllers;

import Domain.*;
import Stores.DataStore;

import java.util.LinkedHashMap;
import java.util.Map;

public class SortByConsecutiveYearController {

    DataStore fruitStore = App.getInstance().getStore();

    public Map<Country, Integer> sortByConsecutiveYears(Fruit f) {
        Map<Country, Map<Year, Quantity>> map = fruitStore.getFruitHarvest().get(f);
        Map<Country, Integer> countryMap = new LinkedHashMap<>();
        int consecutiveYears;

        for (Map.Entry<Country, Map<Year, Quantity>> entry : map.entrySet()) {
            Country country = entry.getKey();
            consecutiveYears = 0;
            Quantity quantity = new Quantity(0);
            int max = 0;
            for (Map.Entry<Year, Quantity> yearEntry : entry.getValue().entrySet()) {
                if (quantity.getQuantity() < yearEntry.getValue().getQuantity())
                    consecutiveYears++;
                else {
                    consecutiveYears = 0;
                }

                if (max < consecutiveYears)
                    max = consecutiveYears;

                quantity = yearEntry.getValue();
            }
            countryMap.put(country, max);
        }
        return countryMap;
    }

    public Map<Fruit, Map<Country, Map<Year, Quantity>>> getFruitHarvest() {
        return fruitStore.getFruitHarvest();
    }
}